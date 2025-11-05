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
import quest.model.Formateur;
import quest.service.PersonneService;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

	@Autowired
	PersonneService personneSrv;
	
	//allFormateur
	@GetMapping
	public String allFormateurs(Model model)
	{
		model.addAttribute("formateurs",personneSrv.getAllFormateurs());
		model.addAttribute("formateur",new Formateur());
		model.addAttribute("civilites", Civilite.values());
		return "formateurs/formateurs";
	}
	
	//ficheFormateur
	@GetMapping("/{id}")
	public String ficheFormateur(@PathVariable Integer id,Model model)
	{
		Formateur formateur = (Formateur)personneSrv.getById(id);
		model.addAttribute("formateur",formateur);
		model.addAttribute("civilites", Civilite.values());
		return "formateurs/updateFormateur";
	}
	
	//supprimerFormateur
	@GetMapping("/delete/{id}")
	public String supprimerFormateur(@PathVariable Integer id)
	{ 
		personneSrv.deleteById(id);
		return "redirect:/formateur";
	}
	
	//ajoutFormateur
	@PostMapping
	public String ajoutFormateur(@ModelAttribute Formateur formateur)
	{
		personneSrv.create(formateur);
		return "redirect:/formateur";
	}
	
	//modifierFormateur
	@PostMapping("/{id}")
	public String modifierFormateur(@ModelAttribute Formateur formateur)
	{
		personneSrv.update(formateur);
		return "redirect:/formateur";
	}
	
}
