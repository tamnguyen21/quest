package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Hello;

public interface IDAOHello extends JpaRepository<Hello, Integer> {
    // @Query("select h from Hello h where h.message like ?1")
    public List<Hello> findAllByMessageContains(String message);
}
