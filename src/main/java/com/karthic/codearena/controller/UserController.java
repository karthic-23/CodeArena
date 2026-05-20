package com.karthic.codearena.controller;

import com.karthic.codearena.dto.*;
import com.karthic.codearena.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ REGISTER
    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    // ✅ GET USERS
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }
}