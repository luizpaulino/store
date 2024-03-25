package com.store.payment.dto.external.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class CartValidResponse {

    @Id
    private String id;
    private List<ItemValidResponse> items;
}
