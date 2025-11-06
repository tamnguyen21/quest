package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.model.Filiere;
import quest.service.FiliereService;


@Controller
@RequestMapping("/filiere")

public class FiliereController {

	@Autowired
	FiliereService filiereSrv;

	@GetMapping
	public String allFilieres(Model model)
	{
		model.addAttribute("filieres",filiereSrv.getAll());
		model.addAttribute("filiere",new Filiere());
		return "filieres/filieres";
	}

	@GetMapping("/{id}")
	public String ficheFiliere(@PathVariable Integer id,Model model)
	{
		Filiere filiere = (Filiere)filiereSrv.getById(id);
		model.addAttribute("filiere",filiere);
		return "filieres/updateFiliere";
	}

	@GetMapping("/delete/{id}")
	public String supprimerFiliere(@PathVariable Integer id)
	{
		filiereSrv.deleteById(id);
		return "redirect:/filiere";
	}

	@PostMapping
	public String ajoutFiliere(@ModelAttribute Filiere filiere)
	{
		filiereSrv.create(filiere);
		return "redirect:/filiere";
	}

	@PostMapping("/{id}")
	public String modifierFiliere(@ModelAttribute Filiere filiere)
	{

		filiereSrv.update(filiere);
		return "redirect:/filiere";
	}
}