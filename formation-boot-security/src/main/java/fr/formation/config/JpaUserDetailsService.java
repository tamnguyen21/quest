package fr.formation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.dao.IDAOUtilisateur;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IDAOUtilisateur dao;

    // Récupérer l'utilisateur en base de données
    // > Si l'utilisateur existe pas, lever une exception UsernameNotFoundException
    // Fabriquer un UserDetails avec le bon username et le bon mot de passe

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Optional<Utilisateur> optUtilisateur = this.dao.findByUsername(username);

        // if (optUtilisateur.isEmpty()) {
        //     throw new UsernameNotFoundException("User not found");
        // }

        // Utilisateur utilisateur = optUtilisateur.get();

        // Utilisateur utilisateur = this.dao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // return User
        //     .withUsername(username)
        //     .password(utilisateur.getPassword())
        //     .roles("USER")
        //     .build()
        // ;

        return this.dao.findByUsername(username)
            .map(user -> User
                    .withUsername(username)
                    .password(user.getPassword())
                    // .authorities("ROLE_USER") // c'est pareil que roles("USER")
                    .roles("USER")
                    .build()
            )
            .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        ;
    }
}
