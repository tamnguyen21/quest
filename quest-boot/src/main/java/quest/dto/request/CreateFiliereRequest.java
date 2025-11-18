package quest.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateFiliereRequest {
	@NotBlank
	private String libelle;
	
	@NotBlank
	private String debut;
	
	@NotBlank
	private String fin;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDebut() {
		return debut;
	}

	public void String(String debut) {
		this.debut = debut;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public void setDebut(String debut) {
		this.debut = debut;
		
	}
	
	
}
