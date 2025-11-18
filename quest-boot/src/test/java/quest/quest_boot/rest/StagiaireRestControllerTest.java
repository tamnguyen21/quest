package quest.quest_boot.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import quest.dao.IDAOPersonne;
import quest.model.*;
import quest.rest.StagiaireRestController;
import quest.service.PersonneService;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.List;

@WebMvcTest(StagiaireRestController.class)
@EnableMethodSecurity(prePostEnabled = true)
public class StagiaireRestControllerTest {


    private static final Integer STAGIAIRE_ID = 1;
    private static final String STAGIAIRE_NAME = "Michel";
    private static final String STAGIAIRE_EMAIL = "machin@mail.com";
    private static final Civilite STAGIAIRE_CIVILITE = Civilite.Homme;
    private static final String STAGIAIRE_LOGIN = "michel";
    private static final String STAGIAIRE_PASSWORD = "123456";
    private static final String STAGIAIRE_SURNAME = "Michel";
    private static final Adresse STAGIAIRE_ADRESSE = new Adresse("8", "rue de la paix", "Paris", "75000");
    private static final Ordinateur STAGIAIRE_ORDINATEUR = new Ordinateur("DELL", 12);
    private static final Filiere STAGIAIRE_FILIERE = new Filiere("INFO", LocalDate.of(1988, 8, 22), LocalDate.of(2025, 8, 22));
    private static final String API_URL = "/api/stagiaire";
    private static final String API_URL_BY_ID = API_URL + "/" + STAGIAIRE_ID;
    private static final String API_URL_BY_NAME = API_URL + "/by-name/" + STAGIAIRE_NAME;

    @MockitoBean
    private PersonneService personneService;
    @MockitoBean
    private IDAOPersonne daoPersonne;


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
    @WithMockUser(roles = "ADMIN")
    void shouldFindAllStatusOkWhenAdmin() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "FORMATEUR")
    void shouldFindAllStatusOkWhenFormateur() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "STAGIAIRE")
    void shouldFindAllIsForbiddenWhenStagiaire() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldGetAllStagiairesUsePersonneService() throws Exception {
        // given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        Mockito.verify(this.personneService).getAllStagiaires();
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldFindAllReturnAttributes() throws Exception {
        // given
        Stagiaire s1 = new Stagiaire();

        s1.setId(1);
        s1.setNom(STAGIAIRE_NAME);
        s1.setCivilite(STAGIAIRE_CIVILITE);
        s1.setLogin(STAGIAIRE_LOGIN);
        s1.setPassword(STAGIAIRE_PASSWORD);
        s1.setPrenom(STAGIAIRE_SURNAME);
        s1.setEmail(STAGIAIRE_EMAIL);
        s1.setAdresse(STAGIAIRE_ADRESSE);
        s1.setOrdinateur(STAGIAIRE_ORDINATEUR);
        s1.setFiliere(STAGIAIRE_FILIERE);

        Mockito.when(this.personneService.getAllStagiaires()).thenReturn(List.of(s1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].nom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].civilite").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].login").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].password").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].prenom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].email").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].adresse").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].ordinateur").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].filiere").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].admin").doesNotExist());
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
    @WithMockUser(roles = "ADMIN")
    void shouldGetFicheStagiaireStatusOk() throws Exception {
        // given
        Mockito.when(this.personneService.getStagiaireById(STAGIAIRE_ID)).thenReturn(new Stagiaire());

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldFindByIdUseServiceGetStagiaireById() throws Exception {
        // given
        Mockito.when(this.personneService.getStagiaireById(STAGIAIRE_ID)).thenReturn(new Stagiaire());

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        Mockito.verify(this.personneService).getStagiaireById(STAGIAIRE_ID);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldFicheStagiaireReturnAttributes() throws Exception {
        // given
        Stagiaire s1 = new Stagiaire();

        s1.setId(STAGIAIRE_ID);
        s1.setNom(STAGIAIRE_NAME);
        s1.setCivilite(STAGIAIRE_CIVILITE);
        s1.setLogin(STAGIAIRE_LOGIN);
        s1.setPassword(STAGIAIRE_PASSWORD);
        s1.setPrenom(STAGIAIRE_SURNAME);
        s1.setEmail(STAGIAIRE_EMAIL);
        s1.setAdresse(STAGIAIRE_ADRESSE);
        s1.setOrdinateur(STAGIAIRE_ORDINATEUR);
        s1.setFiliere(STAGIAIRE_FILIERE);

        Mockito.when(this.personneService.getStagiaireById(STAGIAIRE_ID)).thenReturn(s1);
        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.civilite").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.login").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.password").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.prenom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.email").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.adresse").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.ordinateur").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.filiere").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.admin").doesNotExist());
    }

    @Test
    void shouldCreateStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost();

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "FORMATEUR")
    void shouldCreateStatusForbiddenWithFormateur() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost();

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost();

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateUsePersonneService() throws Exception {
        // given
        ArgumentCaptor<Stagiaire> stagiaireCaptor = ArgumentCaptor.captor();

        // when
        this.createAndPost();

        // then
        Mockito.verify(this.personneService).create(stagiaireCaptor.capture());

        Stagiaire stagiaire = stagiaireCaptor.getValue();

        Assertions.assertEquals(STAGIAIRE_NAME, stagiaire.getNom());
        Assertions.assertEquals(STAGIAIRE_CIVILITE, stagiaire.getCivilite());
        Assertions.assertEquals(STAGIAIRE_LOGIN, stagiaire.getLogin());
        Assertions.assertEquals(STAGIAIRE_PASSWORD, stagiaire.getPassword());
        Assertions.assertEquals(STAGIAIRE_SURNAME, stagiaire.getPrenom());
        Assertions.assertEquals(STAGIAIRE_EMAIL, stagiaire.getEmail());
        Assertions.assertEquals(STAGIAIRE_ADRESSE.getNumero(), stagiaire.getAdresse().getNumero());
        Assertions.assertEquals(STAGIAIRE_ADRESSE.getNumero(), stagiaire.getAdresse().getNumero());
        Assertions.assertEquals(STAGIAIRE_ADRESSE.getVoie(), stagiaire.getAdresse().getVoie());
        Assertions.assertEquals(STAGIAIRE_ADRESSE.getVille(), stagiaire.getAdresse().getVille());
        Assertions.assertEquals(STAGIAIRE_ADRESSE.getCp(), stagiaire.getAdresse().getCp());
        Assertions.assertEquals(STAGIAIRE_ORDINATEUR.getMarque(), stagiaire.getOrdinateur().getMarque());
        Assertions.assertEquals(STAGIAIRE_ORDINATEUR.getRam(), stagiaire.getOrdinateur().getRam());
        Assertions.assertEquals(STAGIAIRE_FILIERE.getLibelle(), stagiaire.getFiliere().getLibelle());
        Assertions.assertEquals(STAGIAIRE_FILIERE.getDebut(), stagiaire.getFiliere().getDebut());
        Assertions.assertEquals(STAGIAIRE_FILIERE.getFin(), stagiaire.getFiliere().getFin());
    }



    private ResultActions createAndPost() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Stagiaire s1 = new Stagiaire();

        s1.setNom(STAGIAIRE_NAME);
        s1.setCivilite(STAGIAIRE_CIVILITE);
        s1.setLogin(STAGIAIRE_LOGIN);
        s1.setPassword(STAGIAIRE_PASSWORD);
        s1.setPrenom(STAGIAIRE_SURNAME);
        s1.setEmail(STAGIAIRE_EMAIL);
        s1.setAdresse(STAGIAIRE_ADRESSE);
        s1.setOrdinateur(STAGIAIRE_ORDINATEUR);
        s1.setFiliere(STAGIAIRE_FILIERE);

        mapper.registerModule(new JavaTimeModule());

        return this.mockMvc.perform(MockMvcRequestBuilders
                .post(API_URL)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(s1))
        );
    }



}