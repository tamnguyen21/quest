package eshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eshop.model.Produit;

public interface IDAOProduit extends JpaRepository<Produit,Integer> {

	@Query("SELECT p from Produit p where p.libelle like %:lib%")
	public List<Produit> findByLibLike(@Param("lib") String libelle);
	
	public Produit findByLibelleContaining(String libelle);
	
	public Produit findTop1ByLibelle(String libelle);
	
	public List<Produit> findByPrixBetween(double min,double max);
	
	@Query("SELECT p from Produit p LEFT JOIN FETCH p.ventes")
	public List<Produit> findAllIdWithVentes();
	
	@Query("SELECT p from Produit p LEFT JOIN FETCH p.ventes where p.id=:id")
	public Produit findByIdWithVentes(@Param("id") Integer idProduit);

	@Query("SELECT p from Produit p where p.fournisseur.id=:id")
	public List<Produit> findByFournisseur(@Param("id") Integer idFournisseur);
	
}
