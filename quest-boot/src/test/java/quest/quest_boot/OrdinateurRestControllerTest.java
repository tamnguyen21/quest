package quest.quest_boot;

import static org.mockito.Mockito.verify;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import quest.dao.IDAOPersonne;
import quest.model.Ordinateur;
import quest.rest.OrdinateurRestController;
import quest.service.OrdinateurService;

@WebMvcTest({ OrdinateurRestController.class })
public class OrdinateurRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrdinateurService os;

    @MockitoBean
    private IDAOPersonne dao;

    public OrdinateurRestControllerTest() {
    }

    @Test
    @WithMockUser
    void getAllTest() throws Exception {
        Ordinateur o1 = new Ordinateur();
        o1.setId(1);
        o1.setMarque("Dell");
        o1.setRam(8);

        List<Ordinateur> values = List.of(o1);
        Mockito.when(this.os.getAll()).thenReturn(values);

        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/ordinateur"));

        result.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(values.size()));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].marque").value("Dell"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$[0].ram").value(8));
    }

    @Test
    @WithMockUser
    void getByIdTest() throws Exception {
        Ordinateur o1 = new Ordinateur();
        o1.setId(1);
        o1.setMarque("Dell");
        o1.setRam(8);

        Mockito.when(this.os.getById(1)).thenReturn(o1);

        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/ordinateur/1"));

        result.andExpect(MockMvcResultMatchers.jsonPath("$").isMap());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        result.andExpect(MockMvcResultMatchers.jsonPath("$.marque").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.marque").value("Dell"));

        result.andExpect(MockMvcResultMatchers.jsonPath("$.ram").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.ram").value(8));
    }

    @Test
    @WithMockUser
    void PostTest() throws Exception {

        ArgumentCaptor<Ordinateur> ordinateurCaptor = ArgumentCaptor.captor();
        this.createAndPost("Dell", 8);

        Mockito.verify(this.os).create(ordinateurCaptor.capture());

        Ordinateur ordinateur = ordinateurCaptor.getValue();

        Assertions.assertEquals("Dell", ordinateur.getMarque());
        Assertions.assertEquals(8, ordinateur.getRam());
    }

    @Test
    @WithMockUser
    void PutTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArgumentCaptor<Ordinateur> ordinateurCaptor = ArgumentCaptor.captor();

        Ordinateur o1 = new Ordinateur(1, "Asus", 16);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/api/ordinateur/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(o1)));

        verify(this.os).update(ordinateurCaptor.capture());
        Ordinateur ordinateur = ordinateurCaptor.getValue();

        Assertions.assertEquals("Asus", ordinateur.getMarque());
        Assertions.assertEquals(16, ordinateur.getRam());

    }

    private ResultActions createAndPost(String marque, int ram) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Ordinateur request = new Ordinateur();

        request.setMarque(marque);
        request.setRam(ram);

        return this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/ordinateur")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(request)));
    }
}
