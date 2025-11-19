package quest.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateFiliereRequest {
	@NotBlank
	private String libelle;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate debut;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
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

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;

	}


}
