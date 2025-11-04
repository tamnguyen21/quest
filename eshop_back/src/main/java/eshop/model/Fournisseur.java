package eshop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import eshop.view.Views;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
@DiscriminatorValue("supplier")
public class Fournisseur extends Personne{

	@Column(name="company",length = 30)
	@Size(min=5,max=30,message = "Le nom d'une societe doit faire entre 5 et 30 caracteres")
	@JsonView(Views.Common.class)
	private String societe;

	@OneToMany(mappedBy="fournisseur")
	@JsonView(Views.FournisseurWithStock.class)
	private List<Produit> stock;
	
	public Fournisseur() {}


	public Fournisseur(String nom, String prenom, String societe) {
		super(nom, prenom);
		this.societe = societe;
	}


	public String getSociete() {
		return societe;
	}


	public void setSociete(String societe) {
		this.societe = societe;
	}


	public List<Produit> getStock() {
		return stock;
	}


	public void setStock(List<Produit> stock) {
		this.stock = stock;
	}

	public String getInfosSelect() 
	{
		return this.id+" - "+this.nom+" "+this.prenom+" ("+this.societe+")";
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", societe=" + societe + "]";
	}
	
	
}
