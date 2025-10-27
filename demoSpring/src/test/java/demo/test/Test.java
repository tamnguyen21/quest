package demo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.composant.Game;
import demo.composant.Graphisme;

public class Test {

	public static void main(String[] args) {

		//Game g = Singleton.getInstance().getGame();
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	
		 Game g = (Game) ctx.getBean("ConfigJeu");
		 Game g2 = (Game) ctx.getBean(Game.class);
		  System.out.println(g);
		  System.out.println(g2);
	}

}
