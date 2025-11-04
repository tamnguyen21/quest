package quest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class StagiaireRestController {

	@Autowired
	PersonneService personneSrv;
	
	
	@JsonView(Views.Stagiaire.class)
	@GetMapping
	public List<Stagiaire> allStagiaires()
	{
		return personneSrv.getAllStagiaires();
	}
	
	@JsonView(Views.Stagiaire.class)
	@PutMapping("/{id}")
	public Stagiaire modifierStagiaire(@PathVariable Integer id,@RequestBody Stagiaire stagiaire) 
	{
		stagiaire.setId(id);
		return (Stagiaire) personneSrv.update(stagiaire);
	}
	
	
}
