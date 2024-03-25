package com.store.payment.service;

import com.store.payment.dto.errors.PaymentException;
import com.store.payment.dto.external.AuthService;
import com.store.payment.dto.external.CartService;
import com.store.payment.dto.external.request.CreateSession;
import com.store.payment.dto.external.response.AccessToken;
import com.store.payment.dto.external.response.CartValidResponse;
import com.store.payment.dto.request.PaymentRequest;
import com.store.payment.dto.response.CartResponse;
import com.store.payment.dto.response.ItemResponse;
import com.store.payment.dto.response.PaymentResponse;
import com.store.payment.persistence.entity.Payment;
import com.store.payment.persistence.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class PaymentService {

    @Value("${authentication-service.user.username}")
    private String username;

    @Value("${authentication-service.user.password}")
    private String password;

    @Value("${cart-service.server.host}")
    String cartServiceHost;

    @Value("${cart-service.server.port}")
    String cartServicePort;

    @Autowired
    PaymentRepository paymentRepository;

    private Retrofit restClient;

    public PaymentService(Retrofit retrofit) {
        this.restClient = retrofit;
    }

    public PaymentResponse execute(PaymentRequest paymentRequest) throws IOException {
        AuthService authService = restClient.create(AuthService.class);

        Response<AccessToken> accessTokenResponse =
                authService.createSession(new CreateSession(username, password)).execute();

        if (!accessTokenResponse.isSuccessful() || accessTokenResponse.body() == null) {
            throw new PaymentException("Error on creation token ");
        }

        Response<Set<String>> userRoles = authService.rolesByUser(
                paymentRequest.getUserId(),
                "Bearer " + accessTokenResponse.body().getAccessToken()
        ).execute();

        if (!userRoles.isSuccessful() || userRoles.body() == null) {
            throw new PaymentException("Error on getting user roles ");
        }

        CartService cartService = restClient.create(CartService.class);

        Response<CartValidResponse> cartValid = cartService.getCart(
                "http://" + cartServiceHost + ":" + cartServicePort + "/v1/cart/" + paymentRequest.getCartId(),
                "Bearer " + accessTokenResponse.body().getAccessToken()
        ).execute();

        if (!cartValid.isSuccessful() || cartValid.body() == null) {
            throw new PaymentException("Error on getting cart ");
        }

        Payment paymentSaved = paymentRepository.save(
                Payment.builder()
                        .cartId(paymentRequest.getCartId())
                        .userId(paymentRequest.getUserId())
                        .build()
        );

        BigDecimal totalValue =
                cartValid.body().getItems().stream()
                        .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return PaymentResponse
                .builder()
                .id(paymentSaved.getId())
                .userId(paymentRequest.getUserId())
                .totalValue(totalValue)
                .cart(CartResponse
                        .builder()
                        .id(cartValid.body().getId())
                        .items(cartValid.body().getItems().stream().map(item -> ItemResponse.builder()
                                .id(item.getId())
                                .description(item.getDescription())
                                .quantity(item.getQuantity())
                                .price(item.getPrice())
                                .build()
                        ).toList())
                        .build()
                ).build();
    }
}
