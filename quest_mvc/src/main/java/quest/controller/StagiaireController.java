package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.model.Civilite;
import quest.model.Stagiaire;
import quest.service.FiliereService;
import quest.service.PersonneService;

@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {
	
	@Autowired
	PersonneService personneServ;
	
	@Autowired
	FiliereService filiereService;

	@GetMapping
	public String allStagiaires(Model model) {
		model.addAttribute("stagiaires", personneServ.getAllStagiaires());
		model.addAttribute("stagiaire",new Stagiaire());
		model.addAttribute("civilites",Civilite.values());
		model.addAttribute("filieres",filiereService.getAll());
		return "stagiaires/stagiaires";
		
	}
	
	@GetMapping("/{id}")
	public String ficheStagiaire(@PathVariable Integer id, Model model) {
		Stagiaire stagiaire = personneServ.getStagiaireById(id);
		model.addAttribute("stagiaire", stagiaire);
		model.addAttribute("civilites",Civilite.values());
		model.addAttribute("filieres",filiereService.getAll());
		return "stagiaires/updateStagiaire";
	}
	
	@GetMapping("/delete/{id}")
	public String supprimerStagiaire(@PathVariable Integer id) {
		personneServ.deleteById(id);
		return "redirect:/stagiaire";
	}

	@PostMapping
	public String ajoutStagiaire(@ModelAttribute Stagiaire stagiaire) {
		personneServ.create(stagiaire);
		return "redirect:/stagiaire";
	}

	@PostMapping("/{id}")
	public String modifierStagiaire(@ModelAttribute Stagiaire stagiaire) {
		personneServ.update(stagiaire);
		return "redirect:/stagiaire";
	}

}
