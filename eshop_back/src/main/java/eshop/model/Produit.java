package eshop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import eshop.view.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="product")
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private Integer id;
	
	@Column(name="label",nullable = false,length = 50)
	@NotBlank(message="Le libelle doit avoir du contenu non vide")
	@JsonView(Views.Common.class)
	private String libelle;
	
	@Column(name="price",columnDefinition = "DECIMAL(6,2)")
	@Min(100)
	@DecimalMax(value="10000",inclusive = false)
	@JsonView(Views.Common.class)
	private double prix;
	
	@ManyToOne
	@JoinColumn(name="vendeur",nullable = false)
	@JsonView(Views.Produit.class)
	private Fournisseur fournisseur;
	
	
	@OneToMany(mappedBy="produit")
	@JsonView(Views.ProduitWithVentes.class)
	private List<Achat> ventes;
	
	
	public Produit() {}

	public Produit(String libelle, double prix,Fournisseur fournisseur) {
		this.libelle = libelle;
		this.prix = prix;
		this.fournisseur=fournisseur;
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	

	public List<Achat> getVentes() {
		return ventes;
	}

	public void setVentes(List<Achat> ventes) {
		this.ventes = ventes;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", libelle=" + libelle + ", prix=" + prix + ", fournisseur=" + fournisseur + "]";
	}

	
	
	
	
}
