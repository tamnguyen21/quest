package quest.dto.response;

import java.time.LocalDate;

import quest.model.Filiere;

public class FiliereResponse {
	private int id;
	private String libelle;
	private LocalDate debut;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static FiliereResponse convert(Filiere filiere) {
		FiliereResponse resp = new FiliereResponse();

        resp.setId(filiere.getId());
        resp.setLibelle(filiere.getLibelle());
        resp.setDebut(filiere.getDebut());
        resp.setFin(filiere.getFin());

        return resp;
    }
}
