package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class that serves as the entry point for the Spring Boot application.
 * This class is responsible for bootstrapping the application and starting the Spring context.
 */
@SpringBootApplication
public class DemoApplication {

    /**
     * Main method that serves as the entry point for the application.
     * Initializes the Spring Boot application context and starts the application.
     * 
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
