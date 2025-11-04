package eshop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import eshop.dao.IDAOProduit;
import eshop.model.Produit;
import eshop.view.Views;

@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {
	@Autowired
	IDAOProduit daoProduit;
	
	
	@GetMapping
	@JsonView(Views.Produit.class)
	public List<Produit> allProduits() 
	{
		return daoProduit.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.Produit.class)
	public Produit ficheProduit(@PathVariable Integer id) 
	{
		return daoProduit.findById(id).get();
	}
	
	@GetMapping("/ventes/{id}")
	@JsonView(Views.ProduitWithVentes.class)
	public Produit ficheProduitWithVentes(@PathVariable Integer id) 
	{
		return daoProduit.findByIdWithVentes(id);
	}
	
	@PostMapping
	public Produit ajouterProduit(@RequestBody Produit produit) 
	{
		return daoProduit.save(produit);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.Produit.class)
	public Produit modifierProduit(@PathVariable Integer id,@RequestBody Produit produit) 
	{
		produit.setId(id);
		return daoProduit.save(produit);
	}
	
	@DeleteMapping("/{id}")
	@JsonView(Views.Produit.class)
	public void deleteProduit(@PathVariable Integer id)
	 {
		daoProduit.deleteById(id);
	 }
	
	

}
