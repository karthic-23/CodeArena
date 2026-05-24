package com.karthic.codearena.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 🔥 Spring Security Imports
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

// 🔥 Your Service
import com.karthic.codearena.service.UserService;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 🔹 1. Get Authorization header
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        // 🔹 2. Check if header contains Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // remove "Bearer "
            email = JwtUtil.extractEmail(token);
        }

        // 🔹 3. If email exists and no authentication yet
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 🔹 4. Load user from DB
            UserDetails userDetails = userService.loadUserByUsername(email);

            // 🔹 5. Validate token
            if (JwtUtil.validateToken(token)) {

                // 🔹 6. Create authentication object
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 🔹 7. Set authentication in Spring context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 🔹 8. Continue request
        filterChain.doFilter(request, response);
    }
}