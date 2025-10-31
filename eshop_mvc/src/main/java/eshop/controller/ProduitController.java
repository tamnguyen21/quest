package eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eshop.dao.IDAOPersonne;
import eshop.dao.IDAOProduit;
import eshop.model.Fournisseur;
import eshop.model.Produit;

@Controller
@RequestMapping("/produit")
public class ProduitController {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired
	IDAOProduit daoProduit;

	@GetMapping
	public String allProduits(Model model) {
		model.addAttribute("produits", daoProduit.findAll());
		model.addAttribute("fournisseurs", daoPersonne.findAllFournisseur());
		return "produits/produits";
	}

	@GetMapping("/{id}")
	public String ficheProduit(@PathVariable Integer id, Model model) {
		Produit produit = daoProduit.findById(id).get();
		model.addAttribute("produit", produit);
		model.addAttribute("fournisseurs", daoPersonne.findAllFournisseur());
		return "produits/updateProduit";
	}

	@GetMapping("/delete/{id}")
	public String supprimerProduit(@PathVariable Integer id) {
		daoProduit.deleteById(id);
		return "redirect:/produit";
	}

	@PostMapping
	public String ajoutProduit(String libelle, double prix,@RequestParam(name = "fournisseur.id" ) Integer idFournisseur ) {
		Fournisseur fournisseur = (Fournisseur) daoPersonne.findById(idFournisseur).get();
		Produit produit = new Produit(libelle, prix, fournisseur);
		daoProduit.save(produit);
		return "redirect:/produit";
	}

	@PostMapping("/{id}")
	public String modifierProduit(@PathVariable Integer id, String libelle, double prix,@RequestParam(name = "fournisseur.id" ) Integer idFournisseur ) {
		Fournisseur fournisseur = (Fournisseur) daoPersonne.findById(idFournisseur).get();
		Produit produit = new Produit(libelle, prix, fournisseur);
		produit.setId(id);
		daoProduit.save(produit);
		return "redirect:/produit";
	}

}
