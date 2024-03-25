package com.store.auth.service;

import com.store.auth.dto.request.UserRequest;
import com.store.auth.dto.response.UserResponse;
import com.store.auth.persistence.entity.User;
import com.store.auth.persistence.entity.UserRole;
import com.store.auth.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserResponse addUser(UserRequest userRequest) {

        userRepository.findByEmail(userRequest.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + userRequest.getEmail() + " already exists");
        });

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = User.fromUserRequest(userRequest);

        user.setRoles(Set.of(UserRole.USER.getRoleName()));
        userRepository.save(user);

        return UserResponse.fromUser(user);
    }

    public Set<String> listRolesByUser(String idUser) {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isPresent()) {
            return userOptional.get().getRoles();
        } else {
            throw new IllegalArgumentException("User not found with id: " + idUser);
        }
    }
}
