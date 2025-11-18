package quest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.dao.IDAOMatiere;
import quest.model.Matiere;

@Service
public class MatiereService {

	@Autowired
	IDAOMatiere daoMatiere;

	public Matiere getById(Integer id) throws RuntimeException
	{
		if(id==null) 
		{
			throw new RuntimeException("L'id d'une matiere ne peut pas etre null");	
		}
	return daoMatiere.findById(id).orElse(null);
	}

	public List<Matiere> getAll()
	{
		return daoMatiere.findAll();
	}
	
	public List<Matiere> getAllByLibelleLike(String recherche)
	{
		return daoMatiere.findByLibelleContaining(recherche);
	}

	public Matiere create(Matiere matiere) 
	{
		return daoMatiere.save(matiere);
	}

	public Matiere update(Matiere matiere) 
	{
		return daoMatiere.save(matiere);
	}

	public void deleteById(Integer id) 
	{
		daoMatiere.deleteById(id);
	}

	public void delete(Matiere matiere)
	{
		daoMatiere.delete(matiere);
	}
	
}
