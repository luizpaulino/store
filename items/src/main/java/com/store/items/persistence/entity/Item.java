package com.store.items.persistence.entity;

import java.math.BigDecimal;

import com.store.items.dto.request.ItemRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Getter
@Setter
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String description;
    private BigDecimal price;
    private int stock;

    public static Item fromItemRequest(ItemRequest itemRequest) {
        Item item = new Item();
        item.setDescription(itemRequest.getDescription());
        item.setPrice(itemRequest.getPrice());
        item.setStock(itemRequest.getStock());
        return item;
    }
}

