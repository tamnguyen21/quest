package quest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import quest.dao.IDAOPersonne;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

@Service
public class PersonneService {

	@Autowired
	IDAOPersonne daoPersonne;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Personne getById(Integer id)
	{
		Optional<Personne> opt = daoPersonne.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public Stagiaire getStagiaireById(Integer id)
	{
		Optional <Personne> opt = daoPersonne.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return (Stagiaire)opt.get();}
	}

	public Formateur getFormateurById(Integer id)
	{
		Optional <Personne> opt = daoPersonne.findById(id);
		if(opt.isEmpty()) {return null;}
		else {

			if(opt.get() instanceof Formateur)
			{
				return (Formateur)opt.get();
			}
			else
			{
				throw new RuntimeException("L'id recu n'est pas celui d'un Formateur...");
			}
		}


	}
	
	public Formateur getFormateurWithModules(Integer id) {
        Optional <Formateur> opt = daoPersonne.formateurWithModules(id);
        if(opt.isEmpty()) {return null;}
        else {

            if(opt.get() instanceof Formateur) 
            {
                return (Formateur)opt.get();
            }
            else 
            {
                throw new RuntimeException("L'id recu n'est pas celui d'un Formateur...");
            }
        }
    }

	public Personne getByLoginAndPassword(String login,String password)
	{
		return daoPersonne.findByLoginAndPassword(login,password);
	}

	public List<Formateur> getAllFormateurs()
	{
		return daoPersonne.findAllFormateur();
	}

	public List<Stagiaire> getAllStagiaires()
	{
		return daoPersonne.findAllStagiaire();
	}

	public List<Personne> getAll()
	{
		return daoPersonne.findAll();
	}

	public Personne create(Personne personne)
	{
		if(personne.getId()!=null)
		{
			throw new RuntimeException("Comment ca une personne en insert a deja un id ?!");
		}

		personne.setPassword(this.passwordEncoder.encode(personne.getPassword()));
		return daoPersonne.save(personne);
	}

	public Personne update(Personne personne)
	{
		if(personne.getId()==null)
		{
			throw new RuntimeException("Comment ca une personne en update a sans un id ?!");
		}

		if (personne.getPassword() != null) {
			personne.setPassword(this.passwordEncoder.encode(personne.getPassword()));
		}

		return daoPersonne.save(personne);
	}

	public Personne updateInfosConnect(Integer id,String login,String password)
	{
		Personne personne = daoPersonne.findById(id).get();
		personne.setLogin(login);
		personne.setPassword(password);
		return daoPersonne.save(personne);
	}

	public void deleteById(Integer id)
	{
		daoPersonne.deleteById(id);
	}

	public void delete(Personne personne)
	{
		daoPersonne.delete(personne);
	}

}
