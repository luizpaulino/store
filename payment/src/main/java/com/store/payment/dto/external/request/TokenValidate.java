package com.store.payment.dto.external.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidate {
    private String token;
}
