package com.karthic.codearena.service;

import com.karthic.codearena.dto.UserResponse;
import com.karthic.codearena.model.User;
import com.karthic.codearena.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.karthic.codearena.dto.LoginResponse;

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
    public UserResponse registerUser(User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        // 🔥 Convert User → UserResponse (hide password)
        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
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
    public LoginResponse loginUser(User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            return new LoginResponse("User not found", null);
        }

        boolean isMatch = encoder.matches(
                user.getPassword(),
                existingUser.get().getPassword()
        );

        if (isMatch) {
            return new LoginResponse(
                    "Login successful",
                    existingUser.get().getEmail()
            );
        } else {
            return new LoginResponse("Invalid password", null);
        }
    }
    
}