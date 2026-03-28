package com.busbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main Spring Boot Application Class for Bus Booking System
 * Entry point for the backend API server
 */
@SpringBootApplication
public class BusBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusBookingApplication.class, args);
        System.out.println("✓ Bus Booking System API started on http://localhost:8080/api");
    }

    /**
     * Configure CORS to allow frontend requests from localhost:3000
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost", "http://127.0.0.1:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
