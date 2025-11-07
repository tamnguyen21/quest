package fr.formation.dto.response;

import fr.formation.model.Produit;

public class ProduitResponse {
    private int id;
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static ProduitResponse convert(Produit produit) {
        ProduitResponse resp = new ProduitResponse();

        resp.setId(produit.getId());
        resp.setNom(produit.getNom());

        return resp;
    }
}
