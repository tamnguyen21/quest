package quest.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import quest.dao.IDAOFiliere;
import quest.dto.request.CreateFiliereRequest;
import quest.model.Filiere;
import quest.rest.FiliereRestController;

@WebMvcTest(FiliereRestController.class)
public class FiliereRestControllerTest {

    private static final int FILIERE_ID = 1;
    private static final String FILIERE_LIBELLE = "Test";
    private static final LocalDate FILIERE_DEBUT = LocalDate.of(2025, 1, 1);
    private static final LocalDate FILIERE_FIN = LocalDate.of(2025, 11, 18);
    private static final String API_URL = "/api/filiere";
    private static final String API_URL_BY_ID = API_URL + "/" + FILIERE_ID;
    private static final String API_URL_BY_LIBELLE = API_URL + "/by-libelle/" + FILIERE_LIBELLE;

    @MockitoBean
    private IDAOFiliere dao;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldFindAllStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldFindAllUseDaoFindAll() throws Exception {
        // given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        Mockito.verify(this.dao).findAll();
    }

    @Test
    @WithMockUser
    void shouldFindAllReturnAttributes() throws Exception {
        // given
        Filiere f1 = new Filiere();
        f1.setId(FILIERE_ID);
        f1.setLibelle(FILIERE_LIBELLE);
        f1.setDebut(LocalDate.of(2025, 1, 1));
        f1.setFin(LocalDate.of(2025, 11, 18));

        Mockito.when(this.dao.findAll()).thenReturn(List.of(f1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].libelle").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].debut").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].fin").exists());
    }

    @Test
    void shouldFindByIdStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    
    @Test
    @WithMockUser
    void shouldFindByIdStatusOk() throws Exception {
        // given
        Mockito.when(this.dao.findById(FILIERE_ID)).thenReturn(Optional.of(new Filiere()));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldFindByIdUseDaoFindById() throws Exception {
        // given
        Mockito.when(this.dao.findById(FILIERE_ID)).thenReturn(Optional.of(new Filiere()));

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        Mockito.verify(this.dao).findById(FILIERE_ID);
    }

    @Test
    @WithMockUser
    void shouldFindByIdStatusNotFoundWhenIdNotFound() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldFindByIdReturnAttributes() throws Exception {
        // given
        Filiere f1 = new Filiere();
        f1.setId(FILIERE_ID);
        f1.setLibelle(FILIERE_LIBELLE);
        f1.setDebut(LocalDate.of(2025, 1, 1));
        f1.setFin(LocalDate.of(2025, 11, 18));

        Mockito.when(this.dao.findById(FILIERE_ID)).thenReturn(Optional.of(f1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(FILIERE_ID));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.libelle").value(FILIERE_LIBELLE));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.debut").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.fin").exists());
    }

    @Test
    @WithMockUser
    void shouldCreateStatusForbidden() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(FILIERE_LIBELLE, FILIERE_DEBUT, FILIERE_FIN);

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    
    @Test
    @WithMockUser
    void shouldCreateStatusOk() throws Exception {
    	// given

        // when
        ResultActions result = this.createAndPost(FILIERE_LIBELLE, FILIERE_DEBUT, FILIERE_FIN);

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateUseDaoSave() throws Exception {
        // given
        ArgumentCaptor<Filiere> filiereCaptor = ArgumentCaptor.captor();

        // when
        this.createAndPost(FILIERE_LIBELLE, FILIERE_DEBUT, FILIERE_FIN);

        // then
        Mockito.verify(this.dao).save(filiereCaptor.capture());

        Filiere filiere = filiereCaptor.getValue();

        Assertions.assertEquals(FILIERE_LIBELLE, filiere.getLibelle());
        Assertions.assertEquals(FILIERE_DEBUT, filiere.getDebut());
        Assertions.assertEquals(FILIERE_FIN, filiere.getFin());
    }

    @ParameterizedTest
    @CsvSource({
        "'',code",
        "'  ',code",
        ",code",
        "nom,",
        "nom,''",
        "nom,'     '",
    })
    @WithMockUser(roles = "ADMIN")
    void shouldCreateStatusBadRequest(String libelle, LocalDate debut, LocalDate fin) throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(libelle, debut, fin);

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.dao, Mockito.never()).save(Mockito.any());
    }

    private ResultActions createAndPost(String libelle, LocalDate debut, LocalDate fin) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateFiliereRequest request = new CreateFiliereRequest();

        request.setLibelle(libelle);
        request.setDebut(debut);
        request.setFin(fin);

        return this.mockMvc.perform(MockMvcRequestBuilders
            .post(API_URL)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapper.writeValueAsString(request))
        );
    }
}
