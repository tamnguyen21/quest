package quest.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import quest.view.Views;

@Entity
@Table(name="module")
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private Integer id;
	
	@Column(nullable = false)
	@JsonView(Views.Common.class)
	private LocalDate debut;
	
	@Column(nullable = false)
	@JsonView(Views.Common.class)
	private LocalDate fin;
	@JsonView(Views.Common.class)
	private int quest;
	
	@ManyToOne
	@JoinColumn(name="filiere",nullable = false)
	
	@JsonView(Views.MatiereWithModule.class)
	private Filiere filiere;
	@ManyToOne
	@JoinColumn(name="matiere",nullable = false)
	@JsonView(Views.FiliereWithModule.class)
	private Matiere matiere;
	@ManyToOne
	@JoinColumn(name="formateur")
	@JsonView(Views.FiliereWithModule.class)
	private Formateur formateur;
	
	public Module() {}
	
	public Module(LocalDate debut, LocalDate fin, int quest, Filiere filiere, Matiere matiere,Formateur formateur) {
		this.debut = debut;
		this.fin = fin;
		this.quest = quest;
		this.filiere = filiere;
		this.matiere = matiere;
		this.formateur=formateur;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public int getQuest() {
		return quest;
	}
	public void setQuest(int quest) {
		this.quest = quest;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", debut=" + debut + ", fin=" + fin + ", quest=" + quest + ", filiere=" + filiere
				+ ", matiere=" + matiere + ", formateur=" + formateur + "]";
	}

	
	
	
	

}
