package fr.formation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOMatiere;
import fr.formation.model.Matiere;

@RestController
@RequestMapping("/api/matiere")
public class MatiereRestController {
    @Autowired
    private IDAOMatiere dao;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Matiere> findAll() {
        return this.dao.findAll();
    }
}
