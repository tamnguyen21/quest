package eshop.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import eshop.dao.IDAOAchat;
import eshop.dao.IDAOPersonne;
import eshop.dao.IDAOProduit;
import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Produit;

public class Test {
	
	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired 
	IDAOAchat daoAchat;
	
	
	public void run(String args[]) 
	{
		Fournisseur fournisseur1 = new Fournisseur("Abid","Charly","AJC");
		fournisseur1 = (Fournisseur) daoPersonne.save(fournisseur1);
		
		System.out.println(fournisseur1);
		
		Produit produit1 = new Produit("Formation JPA",1500,fournisseur1);
		produit1=daoProduit.save(produit1);
		
		Client client1 = new Client("Abid","Jordan",32,LocalDate.parse("1993-05-01"),"1 bis","rue de paris","75009","Paris");
		
		client1=(Client) daoPersonne.save(client1);
		
		for(Produit p : daoProduit.findAll()) 
		{
			System.out.println(p);
		}
		
		
		System.out.println("----Prix between [2000,3000] -----");
		System.out.println(daoProduit.findByPrixBetween(2000, 3000));
		System.out.println("----Prix like QL-----");
		System.out.println(daoProduit.findByLibelleContaining("QL"));
		
		System.out.println(daoProduit.findByLibLike("QL"));
		System.out.println("----First Formation JPA-----");
		System.out.println(daoProduit.findTop1ByLibelle("Formation JPA"));
	}
}
