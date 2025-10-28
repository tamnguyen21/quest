package demo.composant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Game {

	@Autowired
	private Graphisme graphisme;
	
	@Autowired
	@Qualifier("audio")
	private IConfig configAudio;
	
	public Game(Graphisme graphisme) 
	{
		System.out.println("Le game est vient d'etre fait");
		System.out.println(graphisme.isFenetre()); //le graphisme est autowired des la creation de l'objet en l'ayant mentionné dans les parentheses  du constructeur
	}

	public Graphisme getGraphisme() {
		return graphisme;
	}

	public void setGraphisme(Graphisme graphisme) {
		this.graphisme = graphisme;
	}

	public IConfig getConfigAudio() {
		return configAudio;
	}

	public void setConfigAudio(IConfig configAudio) {
		this.configAudio = configAudio;
	}
	
	public void lancerLeJeu() 
	{
		System.out.println("On vient de lancer le jeu");
	}
	
	
	public int diviser(int a) throws Exception 
	{
		int resultat=0;
		
		if(a==0) {throw new Exception();}
		resultat= 10/a;
		return resultat;
	}
	
	public String uneFonctionAvecReturn() 
	{
		return "on retourne un String";
	}

	@Override
	public String toString() {
		return "Game [graphisme=" + graphisme + ", configAudio=" + configAudio + "]";
	}
	
	
}
