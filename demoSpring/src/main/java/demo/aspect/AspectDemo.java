package demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class AspectDemo {

	
	public void seLanceAvantLeJeu() 
	{
		System.out.println("On pourrait gerer un log.txt quand le jeu s'ouvre");
	}
	
	public void seLanceApresLeJeu() 
	{
		System.out.println("On pourrait gerer un log.txt quand le jeu se ferme");
	}
	
	
	
	public void seLanceAroundLeJeu(ProceedingJoinPoint pj) throws Throwable
	{
		System.out.println("----Version Around-------");
		System.out.println("On pourrait gerer un log.txt quand le jeu s'ouvre");
		/////LE VRAI JEU
		pj.proceed();
		System.out.println("On pourrait gerer un log.txt quand le jeu se ferme");
		System.out.println("----Version Around-------");
	}
	
	
	
	public void seLanceApresReturn(String leMessage) 
	{
		System.out.println("----------");
		System.out.println(leMessage);
		System.out.println("Ca se lance apres la fonction 'uneFonctionAvecReturn'");
		System.out.println("----------");
	}
	
	public void seLanceSiException() {System.out.println("Ca a planté à cause de");}
	
	
}
