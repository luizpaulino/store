package com.store.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank
    @NotNull
    @Email(message = "email is a required field and must be a valid email address")
    private String email;

    @NotNull
    @Size(min = 4, max = 12, message = "password must be between 4 and 12 characters")
    private String password;
}

