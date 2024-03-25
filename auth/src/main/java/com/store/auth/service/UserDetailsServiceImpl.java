package com.store.auth.service;

import com.store.auth.dto.model.CustomUserDetails;
import com.store.auth.persistence.entity.User;
import com.store.auth.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email)
        );

        logger.info("Usuário autenticado com sucesso!");
        return new CustomUserDetails(user);
    }
}
