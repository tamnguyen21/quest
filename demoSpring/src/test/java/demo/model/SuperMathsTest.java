package demo.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class SuperMathsTest {

	@BeforeClass
	public static void debutDesTest() 
	{
		System.out.println("debut des test");
	}
	
	
	@Test
	public void creationSuperMaths() 
	{
		SuperMaths s;
		
		s=new SuperMaths();
		
		assertNotNull(s);
	}
	
	@Test
	public void addition() 
	{
		int a=1;
		int b=5;
		SuperMaths s=new SuperMaths();
		int resultat;
		
		resultat=s.additionner(a, b);
		
		assertTrue(resultat==6);
	}
	
	@Test
	public void soustraction() 
	{
		int a=6;
		int b=5;
		SuperMaths s=new SuperMaths();
		int resultat;
		
		resultat=s.soustraire(a, b);
		
		assertFalse(resultat==0);
	}
	
}
