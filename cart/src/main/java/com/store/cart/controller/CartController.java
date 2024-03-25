package com.store.cart.controller;

import com.store.cart.dto.request.ItemRequest;
import com.store.cart.dto.response.CartResponse;
import com.store.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> addItem(
            @RequestBody ItemRequest itemRequest,
            @RequestHeader(name = "userId") String userId
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addItemToCart(itemRequest, userId));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> byId(
            @PathVariable String cartId
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.getById(cartId));
    }
}

