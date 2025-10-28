package orchestre.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import orchestre.config.AppConfig;
import orchestre.model.Flutiste;
import orchestre.model.Guitariste;
import orchestre.model.IMusicien;
import orchestre.model.Pianiste;

public class Test {

	public static void main(String[] args) {
	
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:application-context.xml");

		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		//Guitariste guitariste=(Guitariste) ctx.getBean(Guitariste.class);
		
		//IMusicien pianiste=(Pianiste) ctx.getBean("pianiste");;
		//IMusicien flutiste=(Flutiste) ctx.getBean("flutiste");;
		
		
		guitariste.jouer();
		pianiste.jouer();
		flutiste.jouer();
		
		
	}

}
