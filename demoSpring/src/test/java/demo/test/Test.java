package demo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.composant.Audio;
import demo.composant.Game;

public class Test {

	public static void main(String[] args) {

		//Game g = Singleton.getInstance().getGame();
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
	
		 Game g = (Game) ctx.getBean("game");
		 Game g2 = (Game) ctx.getBean(Game.class);
		 Audio a = (Audio) ctx.getBean("audio");
		 System.out.println(a);
		  System.out.println(g);
		  System.out.println(g2);
	}

}
