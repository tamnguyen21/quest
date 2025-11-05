package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.dao.IDAOMatiere;
import fr.formation.model.Matiere;

@Controller
@RequestMapping("/matiere")
public class MatiereController {
    @Autowired
    private IDAOMatiere dao;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("matieres", this.dao.findAll());
		model.addAttribute("matiere",new Matiere());

        return "matieres";
    }
}
