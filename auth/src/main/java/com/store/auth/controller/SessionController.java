package com.store.auth.controller;

import com.store.auth.dto.request.TokenRequest;
import com.store.auth.dto.request.UserRequest;
import com.store.auth.dto.response.JwtResponseDTO;
import com.store.auth.dto.response.JwtValidDTO;
import com.store.auth.service.JwtService;
import com.store.auth.service.UserDetailsServiceImpl;
import com.store.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/session")
public class SessionController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public JwtResponseDTO authenticateAndGetToken(@RequestBody UserRequest userRequest){
        return getJwtResponseDTO(userRequest);
    }

    private JwtResponseDTO getJwtResponseDTO(UserRequest userRequest) {
        return jwtService.getJwtResponseDTO(userRequest);
    }

    @PostMapping("/validate")
    public JwtValidDTO validateToken(@RequestBody TokenRequest tokenRequest){
        return jwtService.validateServiceToken(tokenRequest.getToken());
    }
}

