package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application-wide beans and settings.
 * This class defines beans that are used throughout the application.
 */
@Configuration
public class Config {
    
    /**
     * Creates and configures a ModelMapper bean.
     * ModelMapper is used for object-to-object mapping between different model objects.
     * 
     * Configuration settings:
     * - Ambiguity is ignored to prevent mapping conflicts
     * - Strict matching strategy is used to ensure exact property name matches
     * 
     * @return Configured ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Ignore ambiguous mappings to prevent conflicts
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        // Use strict matching to ensure exact property name matches
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

}
