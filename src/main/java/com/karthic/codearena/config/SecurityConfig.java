package com.karthic.codearena.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Disable CSRF for testing
            .csrf().disable()

            // 🔓 Allow all requests
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )

            // 🔥 THIS FIXES YOUR ISSUE
            // Allow H2 console to run in a frame
            .headers(headers -> headers
                .frameOptions().disable()
            );

        return http.build();
    }
}