package fr.formation.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.model.Hello;

// @SpringBootTest
@DataJpaTest
public class IDAOHelloTest {
    @Autowired
    private IDAOHello dao;

    @Test
    void shouldFindAllByMessageReturnAllValues() {
        // given

        // when
        List<Hello> result = this.dao.findAllByMessageContains("Bonjour");

        // then
        Assertions.assertEquals(3, result.size());
    }
}
