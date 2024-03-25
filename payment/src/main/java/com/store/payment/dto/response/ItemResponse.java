package com.store.payment.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemResponse {

    private String id;
    private String description;
    private int quantity;
    private BigDecimal price;
}
