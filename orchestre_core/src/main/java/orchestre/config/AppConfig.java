package orchestre.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import orchestre.model.Flutiste;
import orchestre.model.Guitariste;
import orchestre.model.IMusicien;
import orchestre.model.Pianiste;

@Configuration
@ComponentScan("orchestre.model")
//@ImportResource("classpath:application-context.xml")
public class AppConfig {

	@Bean
	public IMusicien guitariste() 
	{
		Guitariste guitariste = new Guitariste();
		guitariste.setPrenom("Jordan");
		return guitariste;
	}
	
	
	@Bean
	public IMusicien pianiste() 
	{
		Pianiste pianiste = new Pianiste();
		pianiste.setPrenom("Eric");
		return pianiste;
	}
	
	
	@Bean
	public IMusicien flutiste() 
	{
		Flutiste flutiste = new Flutiste();
		flutiste.setPrenom("Olivier");
		return flutiste;
	}
	
	
}
