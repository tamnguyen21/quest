package quest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import quest.model.Matiere;
import quest.service.MatiereService;
import quest.view.Views;

@RestController
@RequestMapping("/api/matiere")
public class MatiereRestController {

	@Autowired
	MatiereService matiereSrv;


	@JsonView(Views.Matiere.class)
	@GetMapping
	public List<Matiere> allMatieres()
	{
		return matiereSrv.getAll();
	}

	@JsonView(Views.Matiere.class)
	@GetMapping("/{id}")
	public ResponseEntity<Matiere> ficheMatiere(@PathVariable Integer id, Matiere matiere) {
		Matiere m = matiereSrv.getById(id);

		if (m == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(m);
	}

	@PostMapping
	public Matiere ajoutMatiere(@RequestBody Matiere matiere)
	{
		return matiereSrv.create(matiere);
	}


	@JsonView(Views.Matiere.class)
	@PutMapping("/{id}")
	public Matiere modifierMatiere(@PathVariable Integer id,@RequestBody Matiere matiere)
	{
		matiere.setId(id);
		return (Matiere) matiereSrv.update(matiere);
	}


	@JsonView(Views.Matiere.class)
	@DeleteMapping("/{id}")
	public void supprimerMatiere(@PathVariable Integer id) {
		matiereSrv.deleteById(id);
	}

}
