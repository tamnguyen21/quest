package eshop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DiscriminatorValue("customer")
public class Client extends Personne {

	@Positive
	@Max(99)
	private int age;
	@Column(name="birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNaissance;
	@Embedded
	private Adresse adresse;
	
	@OneToMany(mappedBy="client")
	/*@JoinTable
	(
	name="achats",
	joinColumns = @JoinColumn(name="acheteur"),
	inverseJoinColumns = @JoinColumn(name="produit")
	)*/
	private List<Achat> achats = new ArrayList();
	
	public Client() {}

	public Client(String nom, String prenom, int age, LocalDate dateNaissance,String numero,String voie,String ville,String cp) {
		super(nom, prenom);
		this.age = age;
		this.dateNaissance = dateNaissance;
		this.adresse=new Adresse(numero,voie,ville,cp);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	

	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", dateNaissance="
				+ dateNaissance + ", adresse=" + adresse + "]";
	}
	
	
	
}
