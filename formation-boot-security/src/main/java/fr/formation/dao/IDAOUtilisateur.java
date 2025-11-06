package fr.formation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.dto.response.UtilisateurProjectionResponse;
import fr.formation.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
    public Optional<Utilisateur> findByUsername(String username);

    // @Query("select u from Utilisateur u")
    public List<UtilisateurProjectionResponse> findAllProjectedBy();

    public <T> List<T> findAllProjectedBy(Class<T> clz);
}
