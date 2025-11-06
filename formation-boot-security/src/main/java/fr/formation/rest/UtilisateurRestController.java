package fr.formation.rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOUtilisateur;
import fr.formation.dto.request.SubscribeUserRequest;
import fr.formation.dto.response.UtilisateurProjection2Response;
import fr.formation.dto.response.UtilisateurProjectionResponse;
import fr.formation.dto.response.UtilisateurResponse;
import fr.formation.model.Utilisateur;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurRestController {
    @Autowired
    private IDAOUtilisateur dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<UtilisateurResponse> findAll() {
        // List<Utilisateur> users = this.dao.findAll();

        // System.out.println(users.size());

        // List<Utilisateur> users2 = users.stream()
        //     .filter(user -> user.getId() == 1)
        //     .toList()
        // ;

        // System.out.println(users2.size());
        // System.out.println(users.size());

        return this.dao.findAll().stream()
            .map(user -> UtilisateurResponse.convert(user))
            // .map(UtilisateurResponse::convert)
            .toList()
        ;
    }

    @GetMapping("projected-by")
    public List<UtilisateurProjectionResponse> findAllProjectedBy() {
        return this.dao.findAllProjectedBy(UtilisateurProjectionResponse.class);
        // return this.dao.findAllProjectedBy();
    }

    @GetMapping("projected-by-2")
    public List<UtilisateurProjection2Response> findAllProjectedBy2() {
        return this.dao.findAllProjectedBy(UtilisateurProjection2Response.class);
    }

    @PostMapping
    public int subscribe(@RequestBody SubscribeUserRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        String encodedPassword = this.passwordEncoder.encode(request.getPassword());

        BeanUtils.copyProperties(request, utilisateur);

        // utilisateur.setNom(request.getNom());
        // utilisateur.setUsername(request.getUsername());
        utilisateur.setPassword(encodedPassword);

        this.dao.save(utilisateur);

        return utilisateur.getId();
    }
}
