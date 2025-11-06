package fr.formation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOUtilisateur;
import fr.formation.model.Utilisateur;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurRestController {
    @Autowired
    private IDAOUtilisateur dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public int subscribe(@RequestBody Utilisateur utilisateur) {
        String encodedPassword = this.passwordEncoder.encode(utilisateur.getPassword());

        utilisateur.setPassword(encodedPassword);

        this.dao.save(utilisateur);

        return utilisateur.getId();
    }
}
