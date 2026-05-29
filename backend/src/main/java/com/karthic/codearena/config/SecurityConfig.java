package com.karthic.codearena.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Disable CSRF (for REST APIs)
            .csrf(csrf -> csrf.disable())

            // 🔥 ENABLE CORS (IMPORTANT)
            .cors(cors -> {})

            // 🔐 Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                .anyRequest().authenticated()
            )

            // 🔐 Stateless session (JWT)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🔥 JWT filter before Spring auth
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

            // (optional for H2 console)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}