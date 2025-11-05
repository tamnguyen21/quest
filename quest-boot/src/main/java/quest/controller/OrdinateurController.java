package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.model.Ordinateur;
import quest.service.OrdinateurService;

@Controller
@RequestMapping("/ordinateur")
public class OrdinateurController {
	
	@Autowired
	private OrdinateurService ordinateurSrv;
	
	@GetMapping
	public String allOrdinateurs(Model model) {
		model.addAttribute("ordinateurs",ordinateurSrv.getAll());
		model.addAttribute("ordinateur",new Ordinateur());
		return "ordinateurs/ordinateurs";
	}
	
	@GetMapping("/{id}")
	public String ficheOrdinateur(@PathVariable Integer id, Model model ) {
		Ordinateur ordinateur = ordinateurSrv.getById(id);
		model.addAttribute("ordinateur",ordinateur);
		return "ordinateurs/updateOrdinateur";
	}
	
	@GetMapping("/delete/{id}")
	public String supprimerOrdinateur(@PathVariable Integer id) {
		ordinateurSrv.deleteById(id);
		return "redirect:/ordinateur";
	}
	
	@PostMapping
	public String ajoutOrdinateur(@ModelAttribute Ordinateur ordinateur) {
		ordinateurSrv.create(ordinateur);
		return "redirect:/ordinateur";
		
	}
	
	@PostMapping("/{id}")
	public String modifierOrdinateur(@ModelAttribute Ordinateur ordinateur) {
		ordinateurSrv.update(ordinateur);
		return "redirect:/ordinateur";
		
	}
}
	
	