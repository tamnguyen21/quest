package quest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import quest.model.Stagiaire;
import quest.service.PersonneService;
import quest.view.Views;

@RestController
@RequestMapping("/api/stagiaire")
@PreAuthorize("hasAnyRole('FORMATEUR', 'ADMIN')")
public class StagiaireRestController {
	@Autowired
	PersonneService personneSrv;

	@GetMapping
	@JsonView(Views.Stagiaire.class)
	public List<Stagiaire> allStagiaires()
	{
		return personneSrv.getAllStagiaires();
	}

	@GetMapping("/{id}")
	@JsonView(Views.Stagiaire.class)
	public Stagiaire ficheStagiaire(@PathVariable Integer id)
	{
		return personneSrv.getStagiaireById(id);
	}

	@PostMapping
	@JsonView(Views.Stagiaire.class)
	@PreAuthorize("hasRole('ADMIN')")
	public Stagiaire ajouterStagiaire(@RequestBody Stagiaire stagiaire)
	{
		return (Stagiaire) personneSrv.create(stagiaire);
	}

	@PutMapping("/{id}")
	@JsonView(Views.Stagiaire.class)
	@PreAuthorize("hasRole('ADMIN')")
	public Stagiaire modifierStagiaire(@PathVariable Integer id,@RequestBody Stagiaire stagiaire)
	{
		stagiaire.setId(id);
		return (Stagiaire) personneSrv.update(stagiaire);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteStagiaire(@PathVariable Integer id)
	{
		personneSrv.deleteById(id);
	}


}
