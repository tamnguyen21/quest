package orchestre.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import orchestre.model.Flutiste;
import orchestre.model.Guitariste;
import orchestre.model.IMusicien;
import orchestre.model.Pianiste;

public class Test {

	public static void main(String[] args) {
	
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");

		Guitariste guitariste=(Guitariste) ctx.getBean("guitariste");
		IMusicien pianiste=(Pianiste) ctx.getBean("pianiste");;
		IMusicien flutiste=(Flutiste) ctx.getBean("flutiste");;
		
		
		guitariste.jouer();
		pianiste.jouer();
		flutiste.jouer();
		
		
	}

}
