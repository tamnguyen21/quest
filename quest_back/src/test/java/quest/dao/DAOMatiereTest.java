package quest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import quest.config.AppConfig;
import quest.model.Matiere;
import quest.service.MatiereService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ AppConfig.class })
@Transactional
@Rollback(true)
public class DAOMatiereTest {

	@Autowired
	IDAOMatiere daoMatiere;
	
	@Test
	public void injectDAOMatiere() 
	{
		//Arrange
		//Act
		//Assert 
		assertNotNull(daoMatiere);
	}
	
	@Test
	public void ajouterMatiere() 
	{
		//Arrange
		Matiere matiere = new Matiere("Formation Spring Boot");
		//Act
		matiere=daoMatiere.save(matiere);
		//Assert 
		assertNotNull(matiere);
		assertNotNull(matiere.getId());
		assertNotNull(matiere.getLibelle());
		assertEquals("Formation Spring Boot",matiere.getLibelle());	
	}
	
	@Test
	public void supprimerMatiere() 
	{
		//Arrange
		Matiere matiere = new Matiere("libelle");
		matiere=daoMatiere.save(matiere);
		//Act
		daoMatiere.delete(matiere);
		//Assert 
		Matiere matiereBis = daoMatiere.findById(matiere.getId()).get();
		assertNull(matiereBis);
	}
	
	
}
