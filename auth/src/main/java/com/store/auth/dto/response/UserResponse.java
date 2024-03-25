package com.store.auth.dto.response;

import com.store.auth.persistence.entity.User;
import lombok.Data;

@Data
public class UserResponse {

    private String id;
    private String email;
    private String password;

    public static UserResponse fromUser(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        return userResponse;
    }
}

