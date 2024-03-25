package com.store.cart.dto.response;

import com.store.cart.persistence.entity.Item;
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

    public static ItemResponse fromItem(Item item) {
        return ItemResponse.builder()
            .id(item.getId())
            .description(item.getDescription())
            .quantity(item.getQuantity())
            .price(item.getPrice())
            .build();
    }
}
