package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Define in-memory users with roles (for now, simple)
    @Bean
    public UserDetailsService userDetailsService() {
        var user1 = User.withUsername("admin")
                .password("{noop}admin123") // {noop} = No encoder
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("customer")
                .password("{noop}cust123")
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    // Configure the security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/customers/**").hasRole("CUSTOMER")
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults()) // Enables basic auth
                .csrf(AbstractHttpConfigurer::disable); // Disable CSRF for development

        return http.build();
    }
}
