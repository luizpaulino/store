package com.store.payment.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class PaymentResponse {

    private String id;
    private String userId;
    private CartResponse cart;
    private BigDecimal totalValue;
}

