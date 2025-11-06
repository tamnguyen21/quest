package fr.formation.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    // Récupérer l'utilisateur en base de données
    // > Si l'utilisateur existe pas, lever une exception UsernameNotFoundException
    // Fabriquer un UserDetails avec le bon username et le bon mot de passe

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User
            .withUsername(username)
            .password("$2a$10$zFeTn0rQKrsMXIT2I2NAl.70YWXs05/XyJsnSsznDjB4C.T0yv8hC")
            .roles("USER")
            .build()
        ;
    }
}
