package com.store.cart.dto.external.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemValid {

    private String id;
    private String description;
    private BigDecimal price;
    private int stock;
}

