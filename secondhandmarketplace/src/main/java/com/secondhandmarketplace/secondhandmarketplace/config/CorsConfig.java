package com.secondhandmarketplace.secondhandmarketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow requests from your React application's origin
        config.addAllowedOrigin("http://localhost:3000");

        // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");

        // Allow all headers
        config.addAllowedHeader("*");

        // This is crucial for passing the X-Auth-Token header
        config.setAllowCredentials(true);

        // Apply the CORS configuration to all API paths
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}