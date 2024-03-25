package com.store.cart.service;

//import com.store.cart.configuration.ItemsClient;
import com.store.cart.dto.errors.CartException;
import com.store.cart.dto.external.AuthService;
import com.store.cart.dto.external.ItemsService;
import com.store.cart.dto.external.request.CreateSession;
import com.store.cart.dto.external.response.AccessToken;
import com.store.cart.dto.external.response.ItemValid;
import com.store.cart.dto.request.ItemRequest;
import com.store.cart.dto.response.CartResponse;
import com.store.cart.dto.response.ItemResponse;
import com.store.cart.persistence.entity.Cart;
import com.store.cart.persistence.entity.Item;
import com.store.cart.persistence.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {

    @Value("${authentication-service.user.username}")
    private String username;

    @Value("${authentication-service.user.password}")
    private String password;

    @Value("${items-service.server.host}")
    String itemsServiceHost;

    @Value("${items-service.server.port}")
    String itemsServicePort;

    private Retrofit restClient;

    @Autowired
    private CartRepository cartRepository;

    public CartService(Retrofit retrofit) {
        this.restClient = retrofit;
    }

    public CartResponse addItemToCart(ItemRequest itemRequest, String userId) throws IOException {

        AuthService authService = restClient.create(AuthService.class);

        Response<AccessToken> accessTokenResponse =
                authService.createSession(new CreateSession(username, password)).execute();

        if (!accessTokenResponse.isSuccessful() || accessTokenResponse.body() == null) {
            throw new CartException("Error on creation token ");
        }

        Response<Set<String>> userRoles = authService.rolesByUser(
                userId,
                "Bearer " + accessTokenResponse.body().getAccessToken()
        ).execute();

        if (!userRoles.isSuccessful() || userRoles.body() == null) {
            throw new CartException("Error on getting user roles ");
        }

        ItemsService itemsService = restClient.create(ItemsService.class);

        Response<ItemValid> itemValid = itemsService.getItem(
                "http://" + itemsServiceHost + ":" + itemsServicePort + "/v1/items/" + itemRequest.getId(),
                "Bearer " + accessTokenResponse.body().getAccessToken()
        ).execute();

        if (!itemValid.isSuccessful() || itemValid.body() == null) {
            throw new CartException("Error on getting item ");
        }

        Item item = new Item(
                itemValid.body().getId(),
                itemValid.body().getDescription(),
                itemRequest.getQuantity(),
                itemValid.body().getPrice()
        );

        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        Cart cart;

        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
            cart.getItems().add(item);
        } else {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setItems(List.of(item));
        }
        cartRepository.save(cart);

        return CartResponse
                .builder()
                .id(cart.getId())
                .items(cart.getItems().stream().map(ItemResponse::fromItem).toList())
                .build();
    }

    public CartResponse getById(String cartId) {
        Cart byId = cartRepository.findById(cartId).orElseThrow(() -> new CartException("Cart not found"));

        return CartResponse
                .builder()
                .id(byId.getId())
                .items(byId.getItems().stream().map(ItemResponse::fromItem).toList())
                .build();
    }
}
