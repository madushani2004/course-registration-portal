package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for web-related settings.
 * Implements WebMvcConfigurer to customize Spring MVC configuration.
 * Primarily used for configuring CORS (Cross-Origin Resource Sharing) settings.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings for the application.
     * Allows cross-origin requests from the frontend application running on localhost:5173.
     * 
     * @param registry The CORS registry to configure
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all endpoints
                .allowedOrigins("http://localhost:5173")  // Allow requests from frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow common HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials (cookies, authorization headers)
    }
} 