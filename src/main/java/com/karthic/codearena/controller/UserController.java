package com.karthic.codearena.controller;

import com.karthic.codearena.model.User;
import com.karthic.codearena.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.karthic.codearena.dto.UserResponse;
import com.karthic.codearena.dto.LoginResponse;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register API
    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔹 LOGIN API
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }
}