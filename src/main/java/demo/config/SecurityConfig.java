package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Modern way to disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow all requests (for now)
                );
        return http.build();
    }
}
// This configuration class sets up a basic security filter chain that disables
// CSRF protection and allows all requests.
// In a real application, you would want to configure more specific security
// settings based on your requirements.
// This is a basic example and should not be used in production without proper
// security measures.
// The `SecurityConfig` class is annotated with `@Configuration`, indicating
// that it provides Spring configuration.
// The `securityFilterChain` method is annotated with `@Bean`, indicating that
// it returns a Spring bean.
// The `HttpSecurity` object is used to configure web-based security for
// specific HTTP requests.
// The `csrf` method is used to configure CSRF protection, and the
// `authorizeHttpRequests` method is used to configure authorization for HTTP
// requests.
// The `anyRequest().permitAll()` method allows all requests to be permitted
// without authentication.
