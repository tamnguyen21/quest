package quest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.dao.IDAOOrdinateur;
import quest.model.Ordinateur;

@Service
public class OrdinateurService {

	@Autowired
	IDAOOrdinateur daoOrdinateur;

	public Ordinateur getById(Integer id)
	{
		/*Optional <Ordinateur> opt = daoOrdinateur.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}*/
		return daoOrdinateur.findById(id).orElse(null);
	}

	public List<Ordinateur> getAll()
	{
		return daoOrdinateur.findAll();
	}

	public Ordinateur create(Ordinateur ordinateur) 
	{
		return daoOrdinateur.save(ordinateur);
	}

	public Ordinateur update(Ordinateur ordinateur) 
	{
		return daoOrdinateur.save(ordinateur);
	}

	public void deleteById(Integer id) 
	{
		daoOrdinateur.deleteById(id);
	}

	public void delete(Ordinateur ordinateur)
	{
		daoOrdinateur.delete(ordinateur);
	}
	
}
