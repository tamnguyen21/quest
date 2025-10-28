package demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import demo.composant.Audio;
import demo.composant.Game;
import demo.composant.IConfig;

public class Test {
	
	@Autowired
	Game g;
	
	@Autowired
	Audio a;
	
	@Autowired
	@Qualifier("graphisme")
	IConfig graph;

	public void run(String[] args) {

		//Game g = Singleton.getInstance().getGame();
		
		//Si la config principale est en XML : 
		// ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	
		//Si la config principale est en JAVA : 
		
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		 //Game g = (Game) ctx.getBean("game");
		// Audio a = (Audio) ctx.getBean("audio");
		 System.out.println(a);
		 System.out.println(g);
		
		 // ctx.close();
	
	}

}
