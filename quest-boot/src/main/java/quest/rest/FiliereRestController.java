package quest.rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quest.dto.request.CreateFiliereRequest;
import quest.model.Filiere;
import quest.service.FiliereService;
import quest.view.Views;

@RestController
@RequestMapping("/api/filiere")
public class FiliereRestController {

	private static final Logger log = LoggerFactory.getLogger(FiliereRestController.class);

	@Autowired
	FiliereService filiereSrv;


	@JsonView(Views.Filiere.class)
	@GetMapping
	public List<Filiere> allFilieres()
	{
		log.info("GET /api/filiere - allFilieres() called");

		List<Filiere> filieres = filiereSrv.getAll();

        log.info("GET /api/filiere - allFilieres() returned {} filières", filieres != null ? filieres.size() : 0);

        return filieres;
	}

	@JsonView(Views.Filiere.class)
	@GetMapping("/{id}")
	public ResponseEntity<Filiere> ficheFiliere(@PathVariable Integer id, Filiere filiere) {
		log.info("GET /api/filiere/{} - ficheFiliere() called", id);
		Filiere f = filiereSrv.getById(id);

		if (f == null) {
			log.info("GET /api/filiere/{} - ficheFiliere() - filière not found", id);
			return ResponseEntity.notFound().build();
		}

		log.info("GET /api/filiere/{} - ficheFiliere() - filière found", id);
		return ResponseEntity.ok(f);
	}


	@PostMapping
	public Integer ajoutFiliere(@Valid @RequestBody CreateFiliereRequest request)
	{
		log.info("POST /api/filiere - ajoutFiliere() called with request: {}", request);

		Filiere filiere = new Filiere();
        BeanUtils.copyProperties(request, filiere);

        filiereSrv.create(filiere);

		log.info("POST /api/filiere - ajoutFiliere() created filière with id: {}", filiere.getId());

		return filiere.getId();
	}


	@JsonView(Views.Filiere.class)
	@PutMapping("/{id}")
	public Filiere modifierFiliere(@PathVariable Integer id,@RequestBody Filiere filiere)
	{
		log.info("PUT /api/filiere/{} - modifierFiliere() called with filière: {}", id, filiere);

		filiere.setId(id);
		
		log.info("PUT /api/filiere/{} - modifierFiliere() updating filière", id);

		return filiereSrv.update(filiere);
	}


	@JsonView(Views.Filiere.class)
	@DeleteMapping("/{id}")
	public void supprimerFiliere(@PathVariable Integer id) {
		log.info("DELETE /api/filiere/{} - supprimerFiliere() called", id);
		filiereSrv.deleteById(id);
		log.info("DELETE /api/filiere/{} - supprimerFiliere() completed", id);
	}

}