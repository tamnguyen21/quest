package quest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import quest.model.Module;

@Repository
@Transactional
public class DAOModule implements IDAOModule {

	 @PersistenceContext
	 private EntityManager em;

	@Override
	public List<Module> findAll() {
		return em.createQuery("from Module").getResultList();
	}

	@Override
	public Module findById(Integer id) {
		return em.find(Module.class, id);
	}

	@Override
	public Module save(Module filiere) {
		return em.merge(filiere);
	}

	@Override
	public void deleteById(Integer id) {
		Module filiere = em.find(Module.class, id);
		em.remove(filiere);
	}

	@Override
	public void delete(Module filiere) {
		filiere = em.merge(filiere);
		em.remove(filiere);
	}

}
