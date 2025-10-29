package demo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VoitureTest {

	/*@BeforeClass
	public static void beforeAll() 
	{
		System.out.println("Se lance une seule fois avant TOUS les test");
	}

	@Before
	public void demoBefore() 
	{
		System.out.println("Se lance avant chaque test");
	}

	@After
	public void demoAfter() 
	{
		System.out.println("Se lance apres chaque test");
	}*/

	@Test
	public void creationVoitureNotNull() 
	{	
		//Arrange
		Voiture voiture;
		Voiture voiture2;
		//Act
		voiture = new Voiture("BMW");
		//Assert
		assertNotNull(voiture);
	}

	@Test
	public void initVoiture() 
	{
		//Arrange
		Voiture voiture;
		//Act
		voiture  = new Voiture("BMW");
		//Assert
		assertNotNull(voiture);
		assertTrue(0 == voiture.getVitesse());
		assertTrue(true == voiture.isArret());
		assertEquals("BMW",voiture.getMarque());
	}

	@Test
	public void startVoiture() 
	{
		//Arrange
		Voiture voiture=new Voiture("BMW");
		int nouvelleVitesse= 100;
		//Act
		voiture.start(nouvelleVitesse);
		//Assert
		assertTrue(nouvelleVitesse==voiture.getVitesse());
		assertTrue(false==voiture.isArret());
	}


	@Test
	public void stopVoiture() 
	{
		//Arrange
		Voiture voiture=new Voiture("BMW");
		voiture.start(100);
		//Act
		voiture.stop();
		//Assert
		assertTrue(0==voiture.getVitesse());
		assertTrue(true==voiture.isArret());
	}

}
