package quest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.dao.IDAOFiliere;
import quest.model.Filiere;

@Service
public class FiliereService {

	@Autowired
	IDAOFiliere daoFiliere;

	public Filiere getById(Integer id)
	{
		Optional <Filiere> opt = daoFiliere.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Filiere> getAll()
	{
		return daoFiliere.findAll();
	}

	public Filiere create(Filiere filiere) 
	{
		return daoFiliere.save(filiere);
	}

	public Filiere update(Filiere filiere) 
	{
		return daoFiliere.save(filiere);
	}

	public void deleteById(Integer id) 
	{
		daoFiliere.deleteById(id);
	}

	public void delete(Filiere filiere)
	{
		daoFiliere.delete(filiere);
	}
	
}
