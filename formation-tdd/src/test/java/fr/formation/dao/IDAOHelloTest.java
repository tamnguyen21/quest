package fr.formation.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import fr.formation.model.Hello;

// @SpringBootTest
@DataJpaTest
public class IDAOHelloTest {
    @Autowired
    private IDAOHello dao;

    @Test
    @Sql(scripts = "classpath:/scripts/create-hello.sql")
    @Sql(scripts = "classpath:/scripts/remove-hello.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void shouldFindAllByMessageReturnAllValues() {
        // given

        // when
        List<Hello> result = this.dao.findAllByMessageContains("Bonjour");

        // then
        Assertions.assertEquals(3, result.size());
    }
}
