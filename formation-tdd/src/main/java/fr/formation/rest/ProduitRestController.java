package fr.formation.rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOProduit;
import fr.formation.dto.response.ProduitResponse;
import fr.formation.dto.response.request.CreateProduitRequest;
import fr.formation.exception.ProduitNotFoundException;
import fr.formation.model.Produit;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {
    @Autowired
    private IDAOProduit dao;

    @GetMapping
    public List<ProduitResponse> findAll() {
        return this.dao.findAll().stream()
            .map(ProduitResponse::convert)
            .toList()
        ;
    }

    @GetMapping("/{id}")
    public ProduitResponse findById(@PathVariable int id) {
        return this.dao.findById(id)
            .map(ProduitResponse::convert)
            .orElseThrow(ProduitNotFoundException::new)
        ;
    }

    @GetMapping("/by-name/{name}")
    public List<ProduitResponse> findByName(@PathVariable String name) {
        return this.dao.findByNom(name).stream()
            .map(ProduitResponse::convert)
            .toList()
        ;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public int create(@Valid @RequestBody CreateProduitRequest request) {
        Produit produit = new Produit();

        BeanUtils.copyProperties(request, produit);

        this.dao.save(produit);

        return produit.getId();
    }
}
