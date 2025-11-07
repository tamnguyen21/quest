package fr.formation.rest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.dao.IDAOHello;

// @SpringBootTest // Tests unitaires qui lancent le contexte de Spring
// @AutoConfigureMockMvc // Cette annotation permet d'injecter un MockMvc

// WebMvcTest ne charge QUE la partie WEB
@WebMvcTest(HelloRestController.class)
public class HelloRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Simuler un Bean Spring avec Mockito
    private IDAOHello dao;

    @Test
    // @WithMockUser(roles = "ADMIN")
    @WithMockUser
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
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc
            .perform(MockMvcRequestBuilders.get("/api/hello/all"));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldFindAllUseDaoFindAll() throws Exception {
        // given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/hello/all"));

        // then
        // Vérifier si la méthode findAll de la DAO a été appelée UNE ET UNE SEULE FOIS
        Mockito.verify(this.dao).findAll();
    }
}
