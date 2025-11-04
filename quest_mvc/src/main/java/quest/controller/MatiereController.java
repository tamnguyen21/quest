package quest.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	@GetMapping("/filter")
	@ResponseBody
	public String allMatiereFilter(String searchLike) 
	{
		String reponse ="";
		List<Matiere> matieres = matserv.getAllByLibelleLike(searchLike);
		if(matieres.isEmpty()) 
		{
			reponse+="<tr><td align='center' colspan='3'>Aucun resultat</td></tr>";
		}
		for(Matiere matiere : matieres) 
		{
			reponse+="<tr>";
			reponse+="<td>"+matiere.getId()+"</td>";
			reponse+="<td>"+matiere.getLibelle()+"</td>";
			reponse+="<td>";
			reponse+="<a href='matiere/id="+matiere.getId()+"'><input type='button' value='Modifier'></a>";
			reponse+="<a href='matiere/delete/id="+matiere.getId()+"'><input type='button' value='Supprimer'></a>";
			reponse+="</td>";
			reponse+="</tr>";
		}
		return reponse;
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
