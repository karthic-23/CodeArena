package com.karthic.codearena.service;

import com.karthic.codearena.model.User;
import com.karthic.codearena.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

// 🔐 Import for encryption
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service // Marks this as business logic layer
public class UserService {

    @Autowired
    private UserRepository userRepository; // talks to DB

    // 🔐 Object to encrypt passwords
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ============================
    // 🔹 REGISTER USER
    // ============================
    public User registerUser(User user) {

        // 🔍 Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // 🔥 VERY IMPORTANT STEP
        // Encrypt password before saving
        user.setPassword(encoder.encode(user.getPassword()));

        // 💾 Save user in DB
        return userRepository.save(user);
    }

    // ============================
    // 🔹 GET ALL USERS
    // ============================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // ============================
    // 🔹 LOGIN USER
    // ============================
    public String loginUser(User user) {

        // 🔍 Step 1: Find user by email
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            return "User not found";
        }

        // 🔐 Step 2: Compare passwords (USE SAME encoder)
        boolean isMatch = encoder.matches(
                user.getPassword(),                  // plain password from request
                existingUser.get().getPassword()     // encrypted password from DB
        );

        if (isMatch) {
            return "Login successful";
        } else {
            return "Invalid password";
        }
    }
}