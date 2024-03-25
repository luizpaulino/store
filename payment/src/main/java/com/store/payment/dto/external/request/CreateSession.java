package com.store.payment.dto.external.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSession {
    private String email;

    private String password;
}
