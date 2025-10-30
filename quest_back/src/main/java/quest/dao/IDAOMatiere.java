package quest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quest.model.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere,Integer> {

	
	public List<Matiere> findByLibelleContaining(String recherche);
	
	@Query("SELECT m from Matiere m where m.libelle like :recherche")
	public List<Matiere> findByContientLeLibelle(@Param("recherche") String recherche);
}
