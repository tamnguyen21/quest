package quest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import quest.model.Filiere;

@Repository
@Transactional
public class DAOFiliere implements IDAOFiliere {

	 @PersistenceContext
	 private EntityManager em;

	@Override
	public List<Filiere> findAll() {
		return em.createQuery("from Filiere").getResultList();
	}

	@Override
	public Filiere findById(Integer id) {
		return em.find(Filiere.class, id);
	}

	@Override
	public Filiere save(Filiere filiere) {
		return em.merge(filiere);
	}

	@Override
	public void deleteById(Integer id) {
		Filiere filiere = em.find(Filiere.class, id);
		em.remove(filiere);
	}

	@Override
	public void delete(Filiere filiere) {
		filiere = em.merge(filiere);
		em.remove(filiere);
	}

}
