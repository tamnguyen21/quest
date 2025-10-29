package eshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import eshop.model.Produit;

@Repository
@Transactional
public class DAOProduit implements IDAOProduit {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Produit> findAll() {
		return em.createQuery("from Produit").getResultList();
	}

	@Override
	public Produit findById(Integer id) {
		return em.find(Produit.class, id);
	}

	@Override
	public Produit save(Produit produit) {
		return em.merge(produit);
	}

	@Override
	public void deleteById(Integer id) {
		Produit produit = em.find(Produit.class, id);
		em.remove(produit);
	}

	@Override
	public void delete(Produit produit) {
		produit = em.merge(produit);
		em.remove(produit);
	}

	@Override
	public List<Produit> findByLibLike(String libelle) {
		Query query = em.createQuery("SELECT p from Produit p where p.libelle like :lib");
		query.setParameter("lib", "%"+libelle+"%");
		return query.getResultList();
	}

	@Override
	public Produit findByIdWithVentes(Integer idProduit) {
		Produit produit = null;
		try {
		produit  = em.createQuery("SELECT p from Produit p LEFT JOIN FETCH p.ventes where p.id=:id",Produit.class).setParameter("id", idProduit).getSingleResult();
		}catch(Exception e) {e.printStackTrace();}
		return produit;
	}

	@Override
	public List<Produit> findByFournisseur(Integer idFournisseur) {
		return em.createQuery("SELECT p from Produit p where p.fournisseur.id=:id").setParameter("id", idFournisseur).getResultList();
	}

}
