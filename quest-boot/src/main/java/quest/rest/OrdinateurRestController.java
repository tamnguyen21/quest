package quest.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import quest.config.SecurityConfig;
import quest.model.Ordinateur;
import quest.service.OrdinateurService;
import quest.view.Views;

@RestController
@RequestMapping("/api/ordinateur")
public class OrdinateurRestController {

	private final static Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	OrdinateurService ordinateurSrv;

	@JsonView(Views.Ordinateur.class)
	@GetMapping
	public List<Ordinateur> allOrdinateurs() {
		log.info("Lancement de la fonction allOrdinateurs");
		return ordinateurSrv.getAll();
	}

	@JsonView(Views.Ordinateur.class)
	@GetMapping("/{id}")
	public Ordinateur ficheOrdinateur(@PathVariable Integer id, Ordinateur ordinateur) {
		log.info("Lancement de la fonction ficheOrdinateur avec l'id {}", id);
		return ordinateurSrv.getById(id);
	}

	@PostMapping
	public Ordinateur ajoutOrdinateur(@RequestBody Ordinateur ordinateur) {
		log.info("Lancement de la fonction ajoutOrdinateur avec les données : id = {}, marque = {}, RAM = {}",
				ordinateur.getId(), ordinateur.getMarque(), ordinateur.getRam());
		return ordinateurSrv.create(ordinateur);
	}

	@JsonView(Views.Ordinateur.class)
	@PutMapping("/{id}")
	public Ordinateur modifierOrdinateur(@PathVariable Integer id, @RequestBody Ordinateur ordinateur) {
		ordinateur.setId(id);
		log.info(
				"Lancement de la fonction modifierOrdinateur d'id {} avec les données : id = {}, marque = {}, RAM = {}",
				id, ordinateur.getId(), ordinateur.getMarque(), ordinateur.getRam());
		return (Ordinateur) ordinateurSrv.update(ordinateur);
	}

	@JsonView(Views.Ordinateur.class)
	@DeleteMapping("/{id}")
	public void supprimerOrdinateur(@PathVariable Integer id) {
		log.info("Lancement de lafonction supprimerOrdinateur d'id {}", id);
		ordinateurSrv.deleteById(id);
	}

}
