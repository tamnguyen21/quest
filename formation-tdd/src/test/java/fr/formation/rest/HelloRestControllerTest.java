package fr.formation.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @SpringBootTest // Tests unitaires qui lancent le contexte de Spring
// @AutoConfigureMockMvc // Cette annotation permet d'injecter un MockMvc

@WebMvcTest(HelloRestController.class)
public class HelloRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
}
