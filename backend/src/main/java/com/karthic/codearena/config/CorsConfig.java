package com.karthic.codearena.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // 🔥 Allow frontend
        config.setAllowedOrigins(
            java.util.List.of(
                "http://localhost:5173",
                "http://localhost:3000"
            )
        );

        // 🔥 Allow all HTTP methods
        config.addAllowedMethod("*");

        // 🔥 Allow all headers
        config.addAllowedHeader("*");

        // 🔥 Allow credentials (JWT)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}