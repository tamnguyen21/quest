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

import quest.model.Ordinateur;
import quest.service.OrdinateurService;
import quest.view.Views;

@RestController
@RequestMapping("/api/ordinateur")
public class OrdinateurRestController {

	@Autowired
	OrdinateurService ordinateurSrv;

	@JsonView(Views.Ordinateur.class)
	@GetMapping
	public List<Ordinateur> allOrdinateurs() {
		return ordinateurSrv.getAll();
	}

	@JsonView(Views.Ordinateur.class)
	@GetMapping("/{id}")
	public Ordinateur ficheOrdinateur(@PathVariable Integer id, Ordinateur ordinateur) {
		return ordinateurSrv.getById(id);
	}

	@PostMapping
	public Ordinateur ajoutOrdinateur(@RequestBody Ordinateur ordinateur) {
		return ordinateurSrv.create(ordinateur);
	}

	@JsonView(Views.Ordinateur.class)
	@PutMapping("/{id}")
	public Ordinateur modifierOrdinateur(@PathVariable Integer id, @RequestBody Ordinateur ordinateur) {
		ordinateur.setId(id);
		return (Ordinateur) ordinateurSrv.update(ordinateur);
	}

	@JsonView(Views.Ordinateur.class)
	@DeleteMapping("/{id}")
	public void supprimerOrdinateur(@PathVariable Integer id) {
		ordinateurSrv.deleteById(id);
	}

}
