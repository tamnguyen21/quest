package quest.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import quest.view.Views;

@Entity
public class Stagiaire extends Personne{

	@Column(length = 50)
	@JsonView(Views.Common.class)
	private String email;
	@Embedded
	@JsonView(Views.Stagiaire.class)
	private Adresse adresse;

	@OneToOne
	@JoinColumn(name="ordinateur")
	@JsonView(Views.Stagiaire.class)
	private Ordinateur ordinateur;
	
	@ManyToOne
	@JsonView(Views.Stagiaire.class)
	@JoinColumn(name="filiere")

	private Filiere filiere;

	public Stagiaire() {}
	
	public Stagiaire(Integer id, String login, String password, String nom, String prenom,Civilite civilite, String email,
			String numero,String voie,String ville,String cp, Ordinateur ordinateur,Filiere filiere) {
		super(id, login, password, nom, prenom,civilite);
		this.email = email;
		this.adresse = new Adresse(numero,voie,ville,cp);
		this.ordinateur = ordinateur;
		this.filiere=filiere;
	}

	public Stagiaire(String login, String password, String nom, String prenom,Civilite civilite, String email,
			String numero,String voie,String ville,String cp, Ordinateur ordinateur,Filiere filiere) {
		super(login, password, nom, prenom,civilite);
		this.email = email;
		this.adresse = new Adresse(numero,voie,ville,cp);
		this.ordinateur = ordinateur;
		this.filiere=filiere;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}



	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	@Override
	public String toString() {
		return "Stagiaire [email=" + email + ", adresse=" + adresse + ", ordinateur=" + ordinateur + ", filiere="
				+ filiere + ", id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", civilite=" + civilite + "]";
	}







}
