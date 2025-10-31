package eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eshop.dao.IDAOPersonne;
import eshop.model.Fournisseur;

@Controller	
@RequestMapping("/fournisseur")
public class FournisseurController {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@GetMapping
	public String allFournisseurs(Model model) 
	{
		model.addAttribute("fournisseurs",daoPersonne.findAllFournisseurWithStock());
		model.addAttribute("fournisseur",new Fournisseur());
		return "fournisseurs/fournisseurs";
	}
	
	@GetMapping("/{id}")
	public String ficheFournisseur(@PathVariable Integer id,Model model) 
	{
		Fournisseur fournisseur = (Fournisseur)daoPersonne.findById(id).get();
		model.addAttribute("fournisseur",fournisseur);
		return "fournisseurs/updateFournisseur";
	}
	
	@GetMapping("/delete/{id}")
	public String supprimerFournisseur(@PathVariable Integer id) 
	{
		daoPersonne.deleteById(id);
		return "redirect:/fournisseur";
	}
	
	/*@PostMapping
	public String ajoutFournisseur(String nom,String prenom,String societe) 
	{
		Fournisseur fournisseur = new Fournisseur(nom,prenom,societe);
		daoPersonne.save(fournisseur);
		return "redirect:/fournisseur";
	}*/
	
	@PostMapping
	public String ajoutFournisseur(@ModelAttribute Fournisseur fournisseur) 
	{
		daoPersonne.save(fournisseur);
		return "redirect:/fournisseur";
	}
	
	@PostMapping("/{id}")
	public String modifierFournisseur(@ModelAttribute Fournisseur fournisseur) 
	{
		daoPersonne.save(fournisseur);
		return "redirect:/fournisseur";
	}
	
	
	
}
