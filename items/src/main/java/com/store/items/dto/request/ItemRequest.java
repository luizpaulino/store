package com.store.items.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ItemRequest {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 50, message = "Description must be between 3 and 50 characters")
    private String description;

    @NotNull
    @Min(value = 0, message = "price must be positive")
    private BigDecimal price;

    @Min(value = 0, message = "Stock must be positive")
    private int stock;
}

