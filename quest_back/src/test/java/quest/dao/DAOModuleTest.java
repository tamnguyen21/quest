/*package quest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import quest.config.AppConfig;
import quest.model.Filiere;
import quest.model.Matiere;
import quest.model.Module;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ AppConfig.class })
@Transactional
@Rollback(true)
public class DAOModuleTest {

	
	@Autowired
	IDAOModule daoModule;
	
	@Autowired
	IDAOFiliere daoFiliere;
	
	@Autowired 
	IDAOMatiere daoMatiere;
	
	
	@Test
	public void injectDAOModule() 
	{
		//Arrange
		//Act
		//Assert 
		assertNotNull(daoModule);
	}
	
	@Test
	public void injectDAOMatiere() 
	{
		//Arrange
		//Act
		//Assert 
		assertNotNull(daoMatiere);
	}
	
	@Test
	public void injectDAOFiliere() 
	{
		//Arrange
		//Act
		//Assert 
		assertNotNull(daoFiliere);
	}
	
	@Test
	public void ajouterModule() 
	{
		Filiere filiere = new Filiere("Test libelle",LocalDate.now(),LocalDate.now().plusDays(58));
		filiere=daoFiliere.save(filiere);
		Matiere matiere = new Matiere("lib matiere");
		matiere=daoMatiere.save(matiere);
		Module module= new Module(LocalDate.parse("2025-10-25"),LocalDate.parse("2025-10-29"),7125,filiere,matiere,null);
		
		module=daoModule.save(module);
		
		assertNotNull(module);
		assertNotNull(module.getId());
		
		assertNotNull(module.getDebut());
		assertEquals(LocalDate.parse("2025-10-25"),module.getDebut());
		
		
		assertNotNull(module.getFin());
		assertEquals(LocalDate.parse("2025-10-29"),module.getFin());
		
		assertFalse(0==module.getQuest());
		assertTrue(7125==module.getQuest());
		
		assertNotNull(module.getFiliere());
		assertTrue(filiere.getId()==module.getFiliere().getId());
	
		assertNotNull(module.getMatiere());
		assertTrue(matiere.getId()==module.getMatiere().getId());
		
	}
}*/
