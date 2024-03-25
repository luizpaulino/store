package com.store.cart.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "cart")
public class Cart {
    @Id
    private String id;
    private String userId;
    private List<Item> items = List.of();
}

