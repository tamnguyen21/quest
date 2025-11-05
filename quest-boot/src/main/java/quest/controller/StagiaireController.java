package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import quest.model.Civilite;
import quest.model.Personne;
import quest.model.Stagiaire;
import quest.service.FiliereService;
import quest.service.OrdinateurService;
import quest.service.PersonneService;

@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {
	
	@Autowired
	PersonneService personneServ;
	
	@Autowired
	FiliereService filiereService;
	
	@Autowired
	OrdinateurService ordinateurService;

	@GetMapping
	public String allStagiaires(Model model) {
		model.addAttribute("stagiaires", personneServ.getAllStagiaires());
		model.addAttribute("stagiaire",new Stagiaire());
		model.addAttribute("civilites",Civilite.values());
		model.addAttribute("filieres",filiereService.getAll());
		model.addAttribute("ordinateurs",ordinateurService.getAll());
		return "stagiaires/stagiaires";
		
	}
	
	@GetMapping("/{id}")
	public String ficheStagiaire(@PathVariable Integer id, Model model) {
		Stagiaire stagiaire = personneServ.getStagiaireById(id);
		model.addAttribute("stagiaire", stagiaire);
		model.addAttribute("civilites",Civilite.values());
		model.addAttribute("filieres",filiereService.getAll());
		model.addAttribute("ordinateurs",ordinateurService.getAll());
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

	@PostMapping("/changeConnect")
	public String modifierInfosConnect(String login,String password,HttpSession session) 
	{
		Integer id = ((Personne) session.getAttribute("connected")).getId();
		session.setAttribute("connected", personneServ.updateInfosConnect(id, login, password)); 
		return "redirect:/home";
	}
	
	@PostMapping("/{id}")
	public String modifierStagiaire(@ModelAttribute Stagiaire stagiaire) {
		personneServ.update(stagiaire);
		return "redirect:/stagiaire";
	}

}
