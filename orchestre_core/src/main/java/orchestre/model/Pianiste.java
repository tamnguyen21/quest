package orchestre.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pianiste implements IMusicien {

	private String prenom;
	
	@Autowired
	private Piano instrument;

	public Pianiste() {}
	
	

	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public Piano getInstrument() {
		return instrument;
	}



	public void setInstrument(Piano instrument) {
		this.instrument = instrument;
	}



	public void jouer() {
		System.out.println("Le pianiste "+prenom+" joue ! "+instrument.son());
	}	
}
