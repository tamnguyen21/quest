package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    // UserDetailsService -> Utilisé par l'AuthenticationProvider pour charger un utilisateur (username, password, roles, etc.)
    // @Bean
    UserDetailsService inMemory(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User
            .withUsername("user")
            // .password("{noop}123456") // noop == NoOperator => pas de hashage
            // .password("123456")
            // .password("$2a$10$zFeTn0rQKrsMXIT2I2NAl.70YWXs05/XyJsnSsznDjB4C.T0yv8hC")
            .password(passwordEncoder.encode("123456"))
            .roles("USER")
            .build()
        );

        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance(); // PAS BIEN

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println("\r\nMot de passe ===> " + passwordEncoder.encode("123456") + "\r\n");

        return passwordEncoder;
    }
}
