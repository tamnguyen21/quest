package fr.formation.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Activer les annotations @PreAuthorize / @PostAuthorize
public class SecurityConfig {
    // Le SecurityFilterChain va nous permettre de configurer les accès, éventuellement le CSRF, politiques CORS générales, etc.
    @Bean // On bypass la config auto-configuration
    SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderFilter jwtFilter) throws Exception {
        // Configurer ici les accès généraux
        http.authorizeHttpRequests(auth -> {
            // auth.requestMatchers("/api/matiere").hasRole("USER");
            // auth.requestMatchers("/api/matiere").hasAuthority("ROLE_USER");

            auth.requestMatchers(HttpMethod.POST, "/api/utilisateur", "/api/utilisateur/connexion").permitAll();

            auth.requestMatchers("/**").authenticated();
        });

        // Activer le formulaire de connexion
        http.formLogin(Customizer.withDefaults());

        // Activer l'authentification par HTTP Basic
        http.httpBasic(Customizer.withDefaults());

        // Désactiver la protection CSRF
        // http.csrf(csrf -> csrf.disable());
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        // Configuration de la politique CORS
        http.cors(cors -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();

                // On autorise toutes les en-têtes HTTP, toutes les méthodes HTTP de tous les domaines
                config.setAllowedHeaders(List.of("*"));
                config.setAllowedMethods(List.of("*"));
                config.setAllowedOrigins(List.of("*"));

                return config;
            };

            cors.configurationSource(source);
        });

        // Positionner le filter JwtHeaderFilter AVANT AuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

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

    // Permet d'injecter dans le contexte de Spring l'AuthenticationManager actuellement utilisé par Spring Security
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
