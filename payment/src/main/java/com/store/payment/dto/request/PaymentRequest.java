package com.store.payment.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PaymentRequest {
    @NotBlank
    @NotNull
    private String cartId;
    @NotBlank
    @NotNull
    private String userId;
}

