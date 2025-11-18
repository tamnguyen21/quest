package quest.rest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import quest.config.JwtHeaderFilter;
import quest.model.Civilite;
import quest.model.Formateur;
import quest.service.PersonneService;

@WebMvcTest(controllers = FormateurRestController.class, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtHeaderFilter.class)})
public class FormateurRestControllerTest {


	private static final int FORMATEUR_ID = 1;
	private static final String FORMATEUR_NOM = "Toto";
	private static final String FORMATEUR_PRENOM = "Tata";
	private static final String FORMATEUR_LOGIN = "login";
	private static final String FORMATEUR_PASSWORD = "password";
	private static final String FORMATEUR_CIVLITE = "Homme";
	private static final String API_URL = "/api/formateur";
	private static final String API_URL_BY_ID = API_URL + "/" + FORMATEUR_ID;
	private static final String API_URL_WITH_MODULE = API_URL + "/" + "modules" + "/" + FORMATEUR_ID;

	@MockitoBean
	private PersonneService srv;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldGetAllUnauthorized() throws Exception {
		// given

		// when
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

		// then
		result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	@WithMockUser
	void shouldGetAllStatusOk() throws Exception {
		// given

		// when
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

		// then
		result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser
	void shouldGetAllUseServiceGetAll() throws Exception {
		// given

		// when
		this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

		// then
		Mockito.verify(this.srv).getAllFormateurs();
	}

	@Test
	@WithMockUser
	void shouldGetAllReturnAllFormateurs() throws Exception {

		// --- GIVEN ---
		Formateur f1 = new Formateur();
		f1.setId(FORMATEUR_ID);
		f1.setNom(FORMATEUR_NOM);
		f1.setPrenom(FORMATEUR_PRENOM);
		f1.setLogin(FORMATEUR_LOGIN);
		f1.setPassword(FORMATEUR_PASSWORD);
		f1.setCivilite(Civilite.valueOf(FORMATEUR_CIVLITE));
		f1.setAdmin(true);
		

		// On retourne la liste de FORMATEUR
		Mockito.when(srv.getAllFormateurs()).thenReturn(List.of(f1));

		// --- WHEN ---
		ResultActions result = this.mockMvc.perform(
				MockMvcRequestBuilders.get(API_URL)
				);

		// --- THEN ---

		result.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(FORMATEUR_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value(FORMATEUR_NOM))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom").value(FORMATEUR_PRENOM))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].login").value(FORMATEUR_LOGIN))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value(FORMATEUR_PASSWORD))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].civilite").value(FORMATEUR_CIVLITE))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].admin").value(true));

	}
	
	@Test
	void shouldGetByIdStatusUnauthorized() throws Exception {
		// given

		// when
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

		// then
		result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	@WithMockUser
	void shouldGetByIdStatusOk() throws Exception {
		// given
		Mockito.when(this.srv.getFormateurById(FORMATEUR_ID)).thenReturn(new Formateur());
		// when
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

		// then
		result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser
	void shouldGetByIdUseServiceGetById() throws Exception {
		// given
		Mockito.when(this.srv.getFormateurById(FORMATEUR_ID)).thenReturn(new Formateur());

		// when
		this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

		// then
		Mockito.verify(this.srv).getFormateurById(FORMATEUR_ID);
	}

	@Test
	@WithMockUser
	void shouldGetByIdReturnAttributes() throws Exception {
		// given

		Formateur f1 = new Formateur();
		f1.setId(FORMATEUR_ID);
		f1.setNom("Toto");
		f1.setPrenom("Tata");
		f1.setLogin("login");
		f1.setPassword("password");
		f1.setCivilite(Civilite.valueOf(FORMATEUR_CIVLITE));
		f1.setAdmin(true);

		Mockito.when(this.srv.getFormateurById(FORMATEUR_ID)).thenReturn(f1);

		// when
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

		// then
		result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(FORMATEUR_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(FORMATEUR_NOM))
		.andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value(FORMATEUR_PRENOM))
		.andExpect(MockMvcResultMatchers.jsonPath("$.login").value(FORMATEUR_LOGIN))
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").value(FORMATEUR_PASSWORD))
		.andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(true));
	}


	@Test
	void shouldGetWithModulesStatusUnauthorized() throws Exception {
		// given

		// when
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_WITH_MODULE));

		// then
		result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	@WithMockUser
	void shouldGetWithModulesStatusOk() throws Exception {
		// given
		Mockito.when(this.srv.getFormateurWithModules(FORMATEUR_ID)).thenReturn(new Formateur());
		// when
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_WITH_MODULE));

		// then
		result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser
	void shouldGetWithModulesUseServiceGetWithModules() throws Exception {
		// given
		Mockito.when(this.srv.getFormateurWithModules(FORMATEUR_ID)).thenReturn(new Formateur());

		// when
		this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_WITH_MODULE));

		// then
		Mockito.verify(this.srv).getFormateurWithModules(FORMATEUR_ID);
	}

	@Test
	@WithMockUser
	void shouldGetWithModulesReturnAttributes() throws Exception {
		// given

		Formateur f1 = new Formateur();
		f1.setId(1);
		f1.setNom("Toto");
		f1.setPrenom("Tata");
		f1.setLogin("login");
		f1.setPassword("password");
		f1.setCivilite(Civilite.valueOf(FORMATEUR_CIVLITE));
		f1.setAdmin(true);

		Mockito.when(this.srv.getFormateurWithModules(FORMATEUR_ID)).thenReturn(f1);

		// when
		ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_WITH_MODULE));

		// then
		result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(FORMATEUR_ID))
		.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value(FORMATEUR_NOM))
		.andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value(FORMATEUR_PRENOM))
		.andExpect(MockMvcResultMatchers.jsonPath("$.login").value(FORMATEUR_LOGIN))
		.andExpect(MockMvcResultMatchers.jsonPath("$.password").value(FORMATEUR_PASSWORD))
		.andExpect(MockMvcResultMatchers.jsonPath("$.civilite").value(FORMATEUR_CIVLITE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(true));
	}

	

	@Test
	void shouldCreateStatusUnauthorized() throws Exception {
		// given

		// when
		ResultActions result = this.createAndPost(FORMATEUR_NOM, FORMATEUR_PRENOM, FORMATEUR_LOGIN, FORMATEUR_PASSWORD, FORMATEUR_CIVLITE, true);

		// then
		result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	@WithMockUser
	void shouldCreateStatusOk() throws Exception {
		// given

		// when
		ResultActions result = this.createAndPost(FORMATEUR_NOM, FORMATEUR_PRENOM, FORMATEUR_LOGIN, FORMATEUR_PASSWORD, FORMATEUR_CIVLITE, true);

		// then
		result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser
	void shouldCreateUseDaoSave() throws Exception {
		// given
		ArgumentCaptor<Formateur> formateurCaptor = ArgumentCaptor.captor();

		// when
		this.createAndPost(FORMATEUR_NOM, FORMATEUR_PRENOM, FORMATEUR_LOGIN, FORMATEUR_PASSWORD, FORMATEUR_CIVLITE, true);

		// then
		Mockito.verify(this.srv).create(formateurCaptor.capture());

		Formateur formateur = formateurCaptor.getValue();

		Assertions.assertEquals(FORMATEUR_NOM, formateur.getNom());
		Assertions.assertEquals(FORMATEUR_PRENOM, formateur.getPrenom());
		Assertions.assertEquals(FORMATEUR_LOGIN, formateur.getLogin());
		Assertions.assertEquals(FORMATEUR_PASSWORD, formateur.getPassword());
		Assertions.assertEquals(Civilite.valueOf(FORMATEUR_CIVLITE), formateur.getCivilite());
		Assertions.assertEquals(true, formateur.isAdmin());
		
	}

	private ResultActions createAndPost(String nom, String prenom,String login, String password,String civilite, boolean admin) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Formateur f = new Formateur();

		f.setNom(nom);
		f.setPrenom(prenom);
		f.setLogin(login);
		f.setPassword(password);
		f.setCivilite(Civilite.valueOf(civilite));
		f.setAdmin(admin);
		

		return this.mockMvc.perform(MockMvcRequestBuilders
				.post(API_URL)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(f))
				);
	}

	@Test
	void shouldUpdateStatusUnauthorized() throws Exception {
		// given

		// when
		ResultActions result = this.createAndPut(FORMATEUR_NOM, FORMATEUR_PRENOM, FORMATEUR_LOGIN, FORMATEUR_PASSWORD, FORMATEUR_CIVLITE, true);

		// then
		result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}


	@Test
	@WithMockUser
	void shouldUpdateStatusOk() throws Exception {
		// given
		Mockito.when(srv.getFormateurById(FORMATEUR_ID)).thenReturn(new Formateur());

		// when
		ResultActions result = this.createAndPut(FORMATEUR_NOM, FORMATEUR_PRENOM, FORMATEUR_LOGIN, FORMATEUR_PASSWORD, FORMATEUR_CIVLITE, true);

		// then
		result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser
	void shouldUpdateUseSrvUpdate() throws Exception {
		// given
		Formateur f = new Formateur();
		f.setId(FORMATEUR_ID);
		f.setNom(FORMATEUR_NOM);
		f.setPrenom(FORMATEUR_PRENOM);
		f.setLogin(FORMATEUR_LOGIN);
		f.setPassword(FORMATEUR_PASSWORD);
		f.setCivilite(Civilite.valueOf(FORMATEUR_CIVLITE));
		f.setAdmin(true);
		
		Mockito.when(srv.getFormateurById(FORMATEUR_ID)).thenReturn(f);
		ArgumentCaptor<Formateur> formateurCaptor = ArgumentCaptor.captor();

		// when
		this.createAndPut(FORMATEUR_NOM, FORMATEUR_PRENOM, FORMATEUR_LOGIN, FORMATEUR_PASSWORD, FORMATEUR_CIVLITE, true);

		// then
		Mockito.verify(this.srv).update(formateurCaptor.capture());

		Formateur formateur = formateurCaptor.getValue();

		Assertions.assertEquals(FORMATEUR_NOM, formateur.getNom());
		Assertions.assertEquals(FORMATEUR_PRENOM, formateur.getPrenom());
		Assertions.assertEquals(FORMATEUR_LOGIN, formateur.getLogin());
		Assertions.assertEquals(FORMATEUR_PASSWORD, formateur.getPassword());
		Assertions.assertEquals(Civilite.valueOf(FORMATEUR_CIVLITE), formateur.getCivilite());
		Assertions.assertEquals(true, formateur.isAdmin());
	}


	private ResultActions createAndPut(String nom, String prenom,String login, String password,String civilite, boolean admin) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Formateur f = new Formateur();

		f.setNom(nom);
		f.setPrenom(prenom);
		f.setLogin(login);
		f.setPassword(password);
		f.setCivilite(Civilite.valueOf(civilite));
		f.setAdmin(admin);


		return this.mockMvc.perform(MockMvcRequestBuilders
				.put(API_URL_BY_ID)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(f))
				);
	}

	@Test
	void shouldDeleteStatusUnauthorized() throws Exception {
		ResultActions result = this.mockMvc.perform(
				MockMvcRequestBuilders.delete(API_URL_BY_ID)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				);

		result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	@WithMockUser
	void shouldDeleteStatusOk() throws Exception {

		ResultActions result = this.mockMvc.perform(
				MockMvcRequestBuilders.delete(API_URL_BY_ID)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				);

		result.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser
	void shouldDeleteUseServiceDeleteById() throws Exception {

		this.mockMvc.perform(
				MockMvcRequestBuilders.delete(API_URL_BY_ID)
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				);

		Mockito.verify(this.srv).deleteById(FORMATEUR_ID);
	}

}
