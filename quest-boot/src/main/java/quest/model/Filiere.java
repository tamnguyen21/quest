package quest.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import quest.view.Views;

@Entity
@Table(name="filiere")
public class Filiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private Integer id;
	
	@Column(length = 50,nullable = false)
	@JsonView(Views.Common.class)
	private String libelle;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.Common.class)
	private LocalDate debut;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonView(Views.Common.class)
	private LocalDate fin;
	
	
	
	@OneToMany(mappedBy="filiere")
	@JsonView(Views.FiliereWithModule.class)
	private List<Module> matieres;
	
	
	@OneToMany(mappedBy="filiere")
	@JsonView(Views.FiliereWithStagiaire.class)
	private List<Stagiaire> inscrits;
	
	@Version
	private int version;
	
	public Filiere() {}
		
	public Filiere(Integer id, String libelle, LocalDate debut, LocalDate fin) {
		this.id = id;
		this.libelle = libelle;
		this.debut = debut;
		this.fin = fin;
	}

	public Filiere(String libelle, LocalDate debut, LocalDate fin) {
		this.libelle = libelle;
		this.debut = debut;
		this.fin = fin;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<Module> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Module> matieres) {
		this.matieres = matieres;
	}
	
	

	public List<Stagiaire> getInscrits() {
		return inscrits;
	}

	public void setInscrits(List<Stagiaire> inscrits) {
		this.inscrits = inscrits;
	}

	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getInfos() 
	{
		return this.id+" - "+this.libelle;
	}

	@Override
	public String toString() {
		return "Filiere [id=" + id + ", libelle=" + libelle + ", debut=" + debut + ", fin=" + fin + "]";
	}
	
	
}
