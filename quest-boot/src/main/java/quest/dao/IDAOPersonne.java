package quest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

public interface IDAOPersonne extends JpaRepository<Personne,Integer> {

	@Query("from Stagiaire")
	public List<Stagiaire> findAllStagiaire();
	
	@Query("from Formateur")
	public List<Formateur> findAllFormateur();
	
	
	public Personne findByLoginAndPassword(String login,String password);
}
