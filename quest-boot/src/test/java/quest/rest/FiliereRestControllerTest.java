package quest.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import quest.dao.IDAOFiliere;
import quest.dao.IDAOPersonne;
import quest.dto.request.CreateFiliereRequest;
import quest.model.Filiere;
import quest.service.FiliereService;

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

    @MockitoBean
    private IDAOPersonne daoPers;

    @MockitoBean
    private FiliereService filiereSrv;

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
    void shouldFindAllUseServiceFindAll() throws Exception {
        // given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        Mockito.verify(this.filiereSrv).getAll();
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

        Mockito.when(this.filiereSrv.getAll()).thenReturn(List.of(f1));

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
        Mockito.when(this.filiereSrv.getById(FILIERE_ID)).thenReturn(new Filiere());

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldFindByIdUseServiceFindById() throws Exception {
        // given
        Mockito.when(this.filiereSrv.getById(FILIERE_ID)).thenReturn(new Filiere());

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        Mockito.verify(this.filiereSrv).getById(FILIERE_ID);
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

        Mockito.when(this.filiereSrv.getById(FILIERE_ID)).thenReturn(f1);

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(FILIERE_ID));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.libelle").value(FILIERE_LIBELLE));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.debut").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.fin").exists());
    }

    @Test
    // @WithMockUser
    void shouldCreateStatusForbidden() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(FILIERE_LIBELLE, FILIERE_DEBUT, FILIERE_FIN);

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
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

    // A MODIF
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateUseSrvSave() throws Exception {
        // given
        ArgumentCaptor<Filiere> filiereCaptor = ArgumentCaptor.captor();

        // when
        this.createAndPost(FILIERE_LIBELLE, FILIERE_DEBUT, FILIERE_FIN);

        // then
        Mockito.verify(this.filiereSrv).create(filiereCaptor.capture());

        Filiere filiere = filiereCaptor.getValue();

        Assertions.assertEquals(FILIERE_LIBELLE, filiere.getLibelle());
        Assertions.assertEquals(FILIERE_DEBUT, filiere.getDebut());
        Assertions.assertEquals(FILIERE_FIN, filiere.getFin());
    }

    //"'libelle', '2025-01-01', '2025-11-18'",

    @ParameterizedTest
    @CsvSource({
        ",,",
        "'libelle', '2025-01-01', ''",
        "'libelle', '', '2025-11-18'",
        "'', '2025-01-01', '2025-11-18'",
        "'libelle', '', ''",
        "'  ', '2025-01-01', ''",
        "'', '', '2025-11-18'",
        "'', '', ''"
    })
    @WithMockUser(roles = "ADMIN")
    void shouldCreateStatusBadRequest(String libelle, String debut, String fin) throws Exception {
        // given
        LocalDate dateDebut = null;
        LocalDate dateFin = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (debut != null && !debut.isBlank()) {
            dateDebut = LocalDate.parse(debut, formatter);
        }

        if (fin != null && !fin.isBlank()) {
            dateFin = LocalDate.parse(fin, formatter);
        }

        // when
        ResultActions result = this.createAndPost(libelle, dateDebut, dateFin);

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.filiereSrv, Mockito.never()).create(Mockito.any());
    }

    private ResultActions createAndPost(String libelle, LocalDate debut, LocalDate fin) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

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
