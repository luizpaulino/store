package com.store.cart.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class CartResponse {

    @Id
    private String id;
    private List<ItemResponse> items;
}
