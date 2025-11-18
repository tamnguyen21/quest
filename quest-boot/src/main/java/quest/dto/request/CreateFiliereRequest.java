package quest.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class CreateFiliereRequest {
	@NotBlank
	private String libelle;
	
	@NotBlank
	private LocalDate debut;
	
	@NotBlank
	private LocalDate fin;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	
	
}
