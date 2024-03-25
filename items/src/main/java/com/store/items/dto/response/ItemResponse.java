package com.store.items.dto.response;

import com.store.items.persistence.entity.Item;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemResponse {

    private String id;
    private String description;
    private BigDecimal price;
    private int stock;

    public static ItemResponse fromItemResponse(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setPrice(item.getPrice());
        itemResponse.setStock(item.getStock());
        return itemResponse;
    }
}

