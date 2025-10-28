package orchestre.model;

import org.springframework.stereotype.Component;

@Component
public class Piano implements IInstrument {

	public String son() {
		return "Le piano fait plink plink";
	}
}
