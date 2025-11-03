package quest.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.model.Matiere;
import quest.service.MatiereService;

@Controller
@RequestMapping("/matiere")
public class MatiereController {
	
	@Autowired
	MatiereService matserv;
	
	@GetMapping
	public String allMatiere(Model model) 
	{
		model.addAttribute("matieres",matserv.getAll());
		model.addAttribute("matiere",new Matiere());
		return "matieres/matieres";
	}
	
	@GetMapping("/{id}")
	public String ficheMatiere(@PathVariable Integer id,Model model) 
	{
		Matiere matiere = matserv.getById(id);
		model.addAttribute("matiere",matiere);
		return "matieres/updateMatiere";
	}
	
	@GetMapping("/delete/{id}")
	public String supprimerMatiere(@PathVariable Integer id) 
	{
		matserv.deleteById(id);
		return "redirect:/matiere";
	}
	
	@PostMapping
	public String ajoutMatiere(@ModelAttribute Matiere matiere) 
	{
		
		matserv.create(matiere);
		return "redirect:/matiere";
	}
	
	@PostMapping("/{id}")
	public String modifierMatiere(@ModelAttribute Matiere matiere) 
	{
		
		matserv.update(matiere);
		return "redirect:/matiere";
	}
	

}
