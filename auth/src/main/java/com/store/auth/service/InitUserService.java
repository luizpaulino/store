package com.store.auth.service;

import com.store.auth.persistence.entity.User;
import com.store.auth.persistence.entity.UserRole;
import com.store.auth.persistence.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class InitUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initUsers();
    }

    private void initUsers() {
        if (userRepository.findByEmail("items@service.com").isEmpty()) {
            addServiceUser("items@service.com", "itemsService");
        }

        if (userRepository.findByEmail("cart@service.com").isEmpty()) {
            addServiceUser("cart@service.com", "cartService");
        }

        if (userRepository.findByEmail("payment@service.com").isEmpty()) {
            addServiceUser("payment@service.com", "paymentService");
        }
    }

    private void addServiceUser(String email, String password) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        });

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(UserRole.SERVICE.getRoleName()));

        userRepository.save(user);
    }
}
