package com.store.cart.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ItemRequest {
    @NotBlank
    @NotNull
    private String id;

    @NotNull
    @Positive
    private int quantity;
}
