package quest.rest;

import java.util.List;

import org.apache.catalina.connector.Response;
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
import quest.dto.request.CreateFiliereRequest;
import quest.model.Filiere;
import quest.service.FiliereService;
import quest.view.Views;

@RestController
@RequestMapping("/api/filiere")
public class FiliereRestController {

	@Autowired
	FiliereService filiereSrv;


	@JsonView(Views.Filiere.class)
	@GetMapping
	public List<Filiere> allFilieres()
	{
		return filiereSrv.getAll();
	}

	@JsonView(Views.Filiere.class)
	@GetMapping("/{id}")
	public ResponseEntity<Filiere> ficheFiliere(@PathVariable Integer id, Filiere filiere) {
		Filiere f = filiereSrv.getById(id);

		if (f == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(f);
	}


	@PostMapping
	public Integer ajoutFiliere(@Valid @RequestBody CreateFiliereRequest request)
	{
		Filiere filiere = new Filiere();
        BeanUtils.copyProperties(request, filiere);

        filiereSrv.create(filiere);

		return filiere.getId();
	}


	@JsonView(Views.Filiere.class)
	@PutMapping("/{id}")
	public Filiere modifierFiliere(@PathVariable Integer id,@RequestBody Filiere filiere)
	{
		filiere.setId(id);
		return (Filiere) filiereSrv.update(filiere);
	}


	@JsonView(Views.Filiere.class)
	@DeleteMapping("/{id}")
	public void supprimerFiliere(@PathVariable Integer id) {
		filiereSrv.deleteById(id);
	}

}