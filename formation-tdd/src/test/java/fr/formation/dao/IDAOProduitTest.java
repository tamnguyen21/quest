package fr.formation.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import fr.formation.model.Produit;

@DataJpaTest
public class IDAOProduitTest {
    @Autowired
    private IDAOProduit dao;

    @Test
    @Sql(scripts = "classpath:/scripts/create-produit.sql")
    void shouldFindByNomReturnAllValues() {
        // given

        // when
        List<Produit> result = this.dao.findByNom("Parachute");

        // then
        Assertions.assertEquals(3, result.size());
    }
}
