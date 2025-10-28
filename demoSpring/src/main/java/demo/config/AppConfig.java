package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import demo.composant.Audio;
import demo.composant.Graphisme;

@Configuration
@ComponentScan("demo.composant")
//@ImportResource("classpath:application-context.xml")
public class AppConfig {
	
	
	@Bean
	public Graphisme graphisme() 
	{
		Graphisme g = new Graphisme();
		g.setFenetre(true);
		return g;
	}
	
	@Bean
	public Audio audio() 
	{
		Audio a = new Audio();
		a.setTypeSortie("Stereo");
		a.setVolume(50);
		return a;
	}
	


}
