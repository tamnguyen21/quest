package quest.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import quest.dao.IDAOPersonne;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IDAOPersonne dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Function<Personne, String> roleMapper = (person) -> {
            return switch (person) {
                case Stagiaire s    -> "STAGIAIRE";
                case Formateur f    -> "FORMATEUR";
                default             -> "NONE";
            };
        };

        return this.dao.findByLogin(username)
            .map(person -> User
                    .withUsername(username)
                    .password(person.getPassword())
                    .roles(roleMapper.apply(person))
                    .build()
            )
            .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        ;
    }
}
