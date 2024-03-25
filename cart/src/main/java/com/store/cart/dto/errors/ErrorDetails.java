package com.store.cart.dto.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {
    private String message;

    public ErrorDetails(String message) {
        this.message = message;
    }
}
