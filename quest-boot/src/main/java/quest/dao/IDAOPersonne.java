package quest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

public interface IDAOPersonne extends JpaRepository<Personne, Integer> {

	@Query("from Stagiaire")
	public List<Stagiaire> findAllStagiaire();

	@Query("from Formateur")
	public List<Formateur> findAllFormateur();

	public Personne findByLoginAndPassword(String login, String password);

	public Optional<Personne> findByLogin(String login);
	
	@Query("SELECT f from Formateur f LEFT JOIN FETCH f.formations where f.id=:id")
    public Optional<Formateur> formateurWithModules(@Param("id") Integer idFormateur);
}
