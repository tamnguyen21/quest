package eshop.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eshop.model.Fournisseur;
import eshop.model.Produit;

@RestController
@RequestMapping("/api/demo")
@CrossOrigin("*")
public class DemoRestController {

	@GetMapping("/{nb}")
	public String demo(@PathVariable Integer nb, @RequestParam String login) 
	{
		return "Requête avec les valeurs "+nb+" et "+login;
	}
	
	@GetMapping
	public Produit produit() 
	{
		return new Produit();
	}
	
	
	
	@GetMapping("/fournisseur")
	public Fournisseur showFournisseu() 
	{
		return new Fournisseur();
	}
	
	
	@PostMapping("/fournisseur")
	public Fournisseur insertFournisseur(@RequestBody Fournisseur fournisseur) 
	{
		System.out.println(fournisseur);
		return fournisseur;
	}
	
	@PostMapping
	public void ajoutProduit(@RequestBody Produit produit) 
	{
		System.out.println(produit);
	}
}
