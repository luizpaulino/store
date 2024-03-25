package com.store.auth.controller;

import com.store.auth.dto.request.UserRequest;
import com.store.auth.dto.response.UserResponse;
import com.store.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserResponse addUser(@RequestBody UserRequest itemRequest) {
        return userService.addUser(itemRequest);
    }

    @GetMapping("{idUser}/roles")
    public Set<String> listRoleByUserSession(@PathVariable String idUser){
        return userService.listRolesByUser(idUser);
    }
}

