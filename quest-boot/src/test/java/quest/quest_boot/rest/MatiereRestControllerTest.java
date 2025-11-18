package quest.quest_boot.rest;

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

import quest.dao.IDAOMatiere;
import quest.dao.IDAOPersonne;
import quest.model.Matiere;
import quest.rest.MatiereRestController;
import quest.service.MatiereService;

@WebMvcTest(MatiereRestController.class)
public class MatiereRestControllerTest {
	private static final int MATIERE_ID = 1;
	private static final String MATIERE_LIBELLE = "Java";
    private static final String API_URL = "/api/matiere";
    private static final String API_URL_BY_ID = API_URL + "/" + MATIERE_ID;

	@MockitoBean
	private MatiereService srv;

	@MockitoBean
	private IDAOPersonne daoPersonne;

	@MockitoBean
	private IDAOMatiere daoMatiere;

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
	    void shouldFindByIdUseDaoFindById() throws Exception {
	        // given
	        Mockito.when(this.srv.getById(MATIERE_ID)).thenReturn(new Matiere());

	        // when
	        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));


	        // then
	        Mockito.verify(this.srv).getById(MATIERE_ID);

	    }

	    @Test
	    @WithMockUser
	    void shouldFindByIdStatusNotFoundWhenIdNotFound() throws Exception {
	        // given
	    	// Mockito.when(srv.getById(0)).thenThrow(new RuntimeException(""));
	        // when
	        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

	        // then
	        result.andExpect(MockMvcResultMatchers.status().isNotFound());
	    }

	    @Test
	    @WithMockUser
	    void shouldFindByIdReturnAttributes() throws Exception {
	        // given
	        Matiere m1 = new Matiere();

	        m1.setId(MATIERE_ID);
	        m1.setLibelle(MATIERE_LIBELLE);

	        Mockito.when(this.srv.getById(MATIERE_ID)).thenReturn((m1));

	        // when
	        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL_BY_ID));

	        // then
	        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	        result.andExpect(MockMvcResultMatchers.jsonPath("$.libelle").exists());

	    }


	    @Test
	    @WithMockUser
	    void shouldFindAllUseDaoFindAll() throws Exception {
	        // given

	        // when
	        this.mockMvc.perform(MockMvcRequestBuilders.get(API_URL));

	        // then
	        Mockito.verify(this.srv).getAll();
	    }
}
