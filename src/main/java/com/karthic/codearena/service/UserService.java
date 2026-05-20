package com.karthic.codearena.service;

import com.karthic.codearena.dto.UserResponse;
import com.karthic.codearena.model.User;
import com.karthic.codearena.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthic.codearena.dto.LoginRequest;
import com.karthic.codearena.dto.LoginResponse;
import com.karthic.codearena.dto.RegisterRequest;
import com.karthic.codearena.config.JwtUtil;

import java.util.Optional;
import java.util.List;

// 🔐 Import for encryption
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 🔥 NEW IMPORTS (IMPORTANT)
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ============================
    // 🔹 REGISTER
    // ============================
    public UserResponse registerUser(RegisterRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User(
                request.getName(),
                request.getEmail(),
                encoder.encode(request.getPassword())
        );

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );
    }

    // ============================
    // 🔹 LOGIN
    // ============================
    public LoginResponse loginUser(LoginRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isEmpty()) {
            return new LoginResponse("User not found", null);
        }

        boolean isMatch = encoder.matches(
                request.getPassword(),
                existingUser.get().getPassword()
        );

        if (isMatch) {
            return new LoginResponse(
                    "Login successful",
                    JwtUtil.generateToken(existingUser.get().getEmail())
            );
        } else {
            return new LoginResponse("Invalid password", null);
        }
    }

    // ============================
    // 🔹 GET USERS
    // ============================
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ============================
    // 🔥 SPRING SECURITY
    // ============================
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}