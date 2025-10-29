package quest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import quest.model.Matiere;

@Repository
@Transactional
public class DAOMatiere implements IDAOMatiere {

	 @PersistenceContext
	 private EntityManager em;

	@Override
	public List<Matiere> findAll() {
		return em.createQuery("from Matiere").getResultList();
	}

	@Override
	public Matiere findById(Integer id) {
		return em.find(Matiere.class, id);
	}

	@Override
	public Matiere save(Matiere matiere) {
		return em.merge(matiere);
	}

	@Override
	public void deleteById(Integer id) {
		Matiere matiere = em.find(Matiere.class, id);
		em.remove(matiere);
	}

	@Override
	public void delete(Matiere matiere) {
		matiere = em.merge(matiere);
		em.remove(matiere);
	}
	
	@Override
	public List<Matiere> findByLibelleLike(String recherche) {
		return em.createQuery("SELECT m from Matiere m where m.libelle like :recherche")
		.setParameter("recherche","%"+recherche+"%")
		.getResultList();
	}

}
