package quest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.dao.IDAOFiliere;
import quest.model.Filiere;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FiliereService {

	private static final Logger log = LoggerFactory.getLogger(FiliereService.class);

	@Autowired
	IDAOFiliere daoFiliere;

	public Filiere getById(Integer id)
	{
		log.info("FiliereService.getById() called with id: {}", id);

		Optional <Filiere> opt = daoFiliere.findById(id);
		if(opt.isEmpty()) {
			log.info("FiliereService.getById() - filière not found for id: {}", id);
			return null;}
		else {
			log.info("FiliereService.getById() - filière found for id: {}", id);			
			return opt.get();}
	}

	public List<Filiere> getAll()
	{
		log.info("FiliereService.getAll() called");
		return daoFiliere.findAll();
	}

	public Filiere create(Filiere filiere) 
	{
		log.info("FiliereService.create() called with filière: {}", filiere);
		return daoFiliere.save(filiere);
	}

	public Filiere update(Filiere filiere) 
	{
		log.info("FiliereService.update() called with filière: {}", filiere);
		return daoFiliere.save(filiere);
	}

	public void deleteById(Integer id) 
	{
		log.info("FiliereService.deleteById() called with id: {}", id);
		daoFiliere.deleteById(id);
	}

	public void delete(Filiere filiere)
	{
		log.info("FiliereService.delete() called with filière: {}", filiere);
		daoFiliere.delete(filiere);
	}
	
}
