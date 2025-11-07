package fr.formation.rest;

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
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.dao.IDAOProduit;
import fr.formation.dto.response.request.CreateProduitRequest;
import fr.formation.model.Produit;

@WebMvcTest(ProduitRestController.class)
@EnableMethodSecurity(prePostEnabled = true)
public class ProduitRestControllerTest {
    private static final int PRODUIT_ID = 1;
    private static final String PRODUIT_NAME = "Parachute";
    private static final String PRODUIT_CODE = "PF001";
    private static final String API_URL = "/api/produit";
    private static final String API_URL_BY_ID = API_URL + "/" + PRODUIT_ID;
    private static final String API_URL_BY_NAME = API_URL + "/by-name/" + PRODUIT_NAME;

    @MockitoBean
    private IDAOProduit dao;

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
        Produit p1 = new Produit();

        p1.setId(1);
        p1.setNom(PRODUIT_NAME);
        p1.setCode(PRODUIT_CODE);

        Mockito.when(this.dao.findAll()).thenReturn(List.of(p1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].nom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].code").doesNotExist());
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
        Mockito.when(this.dao.findById(PRODUIT_ID)).thenReturn(Optional.of(new Produit()));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldFindByIdUseDaoFindById() throws Exception {
        // given
        Mockito.when(this.dao.findById(PRODUIT_ID)).thenReturn(Optional.of(new Produit()));

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        Mockito.verify(this.dao).findById(PRODUIT_ID);
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
        Produit p1 = new Produit();

        p1.setId(PRODUIT_ID);
        p1.setNom(PRODUIT_NAME);
        p1.setCode(PRODUIT_CODE);
        Mockito.when(this.dao.findById(PRODUIT_ID)).thenReturn(Optional.of(p1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.nom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.code").doesNotExist());
    }

    @Test
    void shouldFindByNameStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_NAME));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldFindByNameStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_NAME));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldFindByNameUseDaoFindByNom() throws Exception {
        // given

        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_NAME));

        // then
        Mockito.verify(this.dao).findByNom(PRODUIT_NAME);
    }

    @Test
    @WithMockUser
    void shouldFindByNameReturnAttributes() throws Exception {
        // given
        Produit p1 = new Produit();

        p1.setId(PRODUIT_ID);
        p1.setNom(PRODUIT_NAME);
        p1.setCode(PRODUIT_CODE);
        Mockito.when(this.dao.findByNom(PRODUIT_NAME)).thenReturn(List.of(p1));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_NAME));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].nom").exists());
        result.andExpect(MockMvcResultMatchers.jsonPath("$[*].code").doesNotExist());
    }

    @Test
    void shouldCreateStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldCreateStatusForbidden() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(PRODUIT_NAME, PRODUIT_CODE);

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateUseDaoSave() throws Exception {
        // given
        ArgumentCaptor<Produit> produitCaptor = ArgumentCaptor.captor();

        // when
        this.createAndPost(PRODUIT_NAME, PRODUIT_CODE);

        // then
        Mockito.verify(this.dao).save(produitCaptor.capture());

        Produit produit = produitCaptor.getValue();

        Assertions.assertEquals(PRODUIT_NAME, produit.getNom());
        Assertions.assertEquals(PRODUIT_CODE, produit.getCode());
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
    void shouldCreateStatusBadRequest(String nom, String code) throws Exception {
        // given

        // when
        ResultActions result = this.createAndPost(nom, code);

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(this.dao, Mockito.never()).save(Mockito.any());
    }

    private ResultActions createAndPost(String nom, String code) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateProduitRequest request = new CreateProduitRequest();

        request.setNom(nom);
        request.setCode(code);

        return this.mockMvc.perform(MockMvcRequestBuilders
            .post(API_URL)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(mapper.writeValueAsString(request))
        );
    }
}
