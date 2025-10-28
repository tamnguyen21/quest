package orchestre.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import orchestre.model.Guitariste;
import orchestre.model.IMusicien;

public class Test {

	@Autowired
	IMusicien guitariste;
	
	@Autowired
	IMusicien pianiste;
	
	@Autowired 
	@Qualifier("flutiste")
	IMusicien olivier;
	
	public void run(String[] args) {
	
		/*guitariste.jouer();
		pianiste.jouer();
		olivier.jouer();
		*/
	
		guitariste.toString();
			
	}

}
