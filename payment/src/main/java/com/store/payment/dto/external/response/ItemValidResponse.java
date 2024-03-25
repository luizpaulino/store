package com.store.payment.dto.external.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemValidResponse {

    private String id;
    private String description;
    private int quantity;
    private BigDecimal price;
}
