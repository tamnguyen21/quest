package fr.formation.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.formation.dao.IDAOHello;

@ExtendWith(MockitoExtension.class)
public class HelloRestControllerMockitoTest {
    private MockMvc mockMvc;

    @Mock // Demande à Mockito de créer une instance, qu'il va simuler
    private IDAOHello dao;

    @InjectMocks // Demande à Mockito de créer une instance, dont il va injecter toutes les dépendances mockées qu'il connait
    private HelloRestController ctrl;

    @BeforeEach
    public void beforeEach() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.ctrl).build();
    }

    @Test
    void shouldHelloStatusOkAndContentHelloWorld() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/hello"));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.content().string("Hello World!"));
    }

    @Test
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/hello/all"));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldFindAllUseDaoFindAll() throws Exception {
        // given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/all"));

        // then
        // Vérifier si la méthode findAll de la DAO a été appelée UNE ET UNE SEULE FOIS
        Mockito.verify(this.dao).findAll();
    }
}
