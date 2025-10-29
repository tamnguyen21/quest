package eshop.test;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import eshop.dao.IDAOAchat;
import eshop.dao.IDAOPersonne;
import eshop.dao.IDAOProduit;
import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Produit;

public class TestDAO {

	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired 
	IDAOAchat daoAchat;
	
	public static String saisieString(String message) 
	{
		Scanner monScanner = new Scanner(System.in);
		System.out.println(message);
		return monScanner.nextLine();
	}
	
	public void run(String[] args) {
		
		String saisie = saisieString("Saisir un libelle");
		List<Produit> produits = daoProduit.findByLibLike(saisie);
		
		if(produits.isEmpty()) 
		{
			System.out.println("Aucun produit ne contient "+saisie);
		}
		for(Produit p : produits) {
			System.out.println(p);
		}
		
		System.out.println("-----------");
		
		Produit p2 = daoProduit.findByIdWithVentes(1);
		System.out.println("Liste des ventes du produit "+p2.getLibelle()+" :");
		if(p2.getVentes().isEmpty()) 
		{
			System.out.println("Aucune vente");
		}
		else 
		{
			System.out.println("Ventes total : "+p2.getVentes().size());
		}
		
		System.out.println("-----------");
		
		
		Fournisseur fournisseur = daoPersonne.findByIdWithStock(1);
		System.out.println("----Fournisseur + le stock-----");
		System.out.println("Voici le stock du fournisseur "+fournisseur.getPrenom()+" "+fournisseur.getNom()+" :");
		for(Produit p : fournisseur.getStock())
		{
			System.out.println(p.getLibelle());
		}
		
		//Comment recuperer uniquement les produit du stock d'un fournisseur : 
		System.out.println("----Uniquement le stock-----");
		for(Produit p : daoProduit.findByFournisseur(1))
		{
			System.out.println(p.getLibelle());
		}
		
		System.out.println("-----------");
		
		Client client = daoPersonne.findByIdWithAchats(2);
		System.out.println("Le client "+client.getPrenom()+" "+client.getNom()+" a effectue "+client.getAchats().size()+" achat(s)");
		
		
		
		System.out.println(daoProduit.findByIdWithVentes(1).getVentes());
	
	}

}
