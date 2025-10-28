package demo.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import demo.composant.Audio;
import demo.composant.Game;
import demo.config.AppConfig;

public class Test {

	public static void main(String[] args) {

		//Game g = Singleton.getInstance().getGame();
		
		//Si la config principale est en XML : 
		// ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	
		//Si la config principale est en JAVA : 
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		 Game g = (Game) ctx.getBean("game");
		 Audio a = (Audio) ctx.getBean("audio");
		 System.out.println(a);
		 System.out.println(g);
	
	}

}
