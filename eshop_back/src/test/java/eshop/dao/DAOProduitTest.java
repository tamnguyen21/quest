package eshop.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eshop.config.AppConfig;
import eshop.model.Fournisseur;
import eshop.model.Produit;
import jakarta.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ AppConfig.class })
@Transactional
@Rollback(true)
public class DAOProduitTest {
	
	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	
	@Test
	public void beanProduit() 
	{
		assertNotNull(daoProduit);
	}
	
	@Test
	public void ajoutProduit() 
	{
		Fournisseur fournisseur = new Fournisseur("nom", "prenom", "societe");
		fournisseur = (Fournisseur) daoPersonne.save(fournisseur);
		Produit produit = new Produit("libelle",19.99,fournisseur);
		
		Produit produitTest = daoProduit.save(produit);
		
		assertNotNull(produitTest);
		assertNotNull(produitTest.getId());
		assertNotNull(produitTest.getLibelle());
		assertNotNull(produitTest.getFournisseur());
		assertFalse(0==produit.getPrix());
		assertEquals("libelle",produitTest.getLibelle());
		assertEquals(fournisseur.getId(),produitTest.getFournisseur().getId());
		assertTrue(19.99==produit.getPrix());
	}	
	
	@Test
	public void chercherProduit() 
	{
		Fournisseur fournisseur = new Fournisseur("nom", "prenom", "societe");
		fournisseur = (Fournisseur) daoPersonne.save(fournisseur);
		Produit produit = new Produit("libelle",19.99,fournisseur);
		produit = daoProduit.save(produit);
		Integer idTest = produit.getId();
		
		Produit produitTest = daoProduit.findById(idTest).orElse(null);
		
		assertNotNull(produitTest);
		assertEquals(fournisseur.getId(),produitTest.getFournisseur().getId());
	
	}
	
	@Test
	public void supprimerProduit() 
	{
		Fournisseur fournisseur = new Fournisseur("nom", "prenom", "societe");
		fournisseur = (Fournisseur) daoPersonne.save(fournisseur);
		Produit produit = new Produit("libelle",19.99,fournisseur);
		produit = daoProduit.save(produit);
		Integer idTest = produit.getId();
		daoProduit.delete(produit);
		
		
		Produit produitTest= daoProduit.findById(idTest).orElse(null);
		assertNull(produitTest);
	}

}
