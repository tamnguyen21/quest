package quest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import quest.view.Views;

@Entity
public class Formateur extends Personne {

	@JsonView(Views.Common.class)
	private boolean admin;
	
	@OneToMany(mappedBy = "formateur")
	@JsonView(Views.FormateurWithModules.class)
	private List<Module> formations;
	
	public Formateur() {}
	
	public Formateur(Integer id, String login, String password, String nom, String prenom,Civilite civilite, boolean admin) {
		super(id, login, password, nom, prenom,civilite);
		this.admin = admin;
	}

	public Formateur(String login, String password, String nom, String prenom,Civilite civilite, boolean admin) {
		super(login, password, nom, prenom,civilite);
		this.admin = admin;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
	public List<Module> getFormations() {
		return formations;
	}

	public void setFormations(List<Module> formations) {
		this.formations = formations;
	}

	@Override
	public String toString() {
		return "Formateur [admin=" + admin + ", id=" + id + ", login=" + login + ", password=" + password + ", nom="
				+ nom + ", prenom=" + prenom + ", civilite=" + civilite + "]";
	}

	
	
	
}
