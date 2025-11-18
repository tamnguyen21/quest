package quest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import quest.model.Formateur;
import quest.service.PersonneService;
import quest.view.Views;

@RestController
@RequestMapping("/api/formateur")
public class FormateurRestController {
	@Autowired
	PersonneService personneSrv;
	
	@GetMapping
	@JsonView(Views.Formateur.class)
	public List<Formateur> allFormateurs(){
		return personneSrv.getAllFormateurs();
	}
	
	
	@GetMapping("/{id}")
	@JsonView(Views.Formateur.class)
	public Formateur ficheFormateur(@PathVariable Integer id) {
		return personneSrv.getFormateurById(id);
	}
	
	@GetMapping("/modules/{id}")
	@JsonView(Views.FormateurWithModules.class)
	public Formateur ficheFormateurWithModules(@PathVariable Integer id) {
		return personneSrv.getFormateurWithModules(id);
				}
	
	@PostMapping
	public Formateur ajouterFormateur(@RequestBody Formateur formateur) {
		return (Formateur) personneSrv.create(formateur);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.Formateur.class)
	public Formateur modifierFormateur(@PathVariable Integer id, @RequestBody Formateur formateur) {
		formateur.setId(id);
		return (Formateur) personneSrv.update(formateur);
	}
	
	@DeleteMapping("/{id}")
	@JsonView(Views.Formateur.class)
	public void deleteFormateur(@PathVariable Integer id) {
		personneSrv.deleteById(id);
	}
}
