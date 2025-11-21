package quest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.config.SecurityConfig;
import quest.dao.IDAOOrdinateur;
import quest.model.Ordinateur;

@Service
public class OrdinateurService {

	@Autowired
	IDAOOrdinateur daoOrdinateur;

	private final static Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	public Ordinateur getById(Integer id) {
		/*
		 * Optional <Ordinateur> opt = daoOrdinateur.findById(id);
		 * if(opt.isEmpty()) {return null;}
		 * else {return opt.get();}
		 */
		log.info("Lancement de la fonction getById avec id : {}", id);
		Optional<Ordinateur> res = daoOrdinateur.findById(id);
		if (res.isEmpty()) {
			log.error("Ordinateur d'id {} non trouvé", id);
			return null;
		} else {
			log.info("Ordinateur d'id {} trouvé", id);
			return res.get();
		}
	}

	public List<Ordinateur> getAll() {
		log.info("Lancement de la fonction getAll");
		return daoOrdinateur.findAll();
	}

	public Ordinateur create(Ordinateur ordinateur) {
		log.info("Lancement de la fonction create avec id : {}, marque : {}, RAM : {}",
				ordinateur.getId(), ordinateur.getMarque(), ordinateur.getRam());
		return daoOrdinateur.save(ordinateur);
	}

	public Ordinateur update(Ordinateur ordinateur) {
		log.info("Lancement de la fonction update avec id : {}, marque : {}, RAM : {}",
				ordinateur.getId(), ordinateur.getMarque(), ordinateur.getRam());
		return daoOrdinateur.save(ordinateur);
	}

	public void deleteById(Integer id) {
		log.info("Lancement de la fonction create avec id : {}", id);
		daoOrdinateur.deleteById(id);
	}

	public void delete(Ordinateur ordinateur) {
		log.warn("Attention une tentative de delete de l' ordinateur d'id {} va avoir lieu", ordinateur.getId());
		log.info("Lancement de la fonction delete avec id : {}, marque : {}, RAM : {}",
				ordinateur.getId(), ordinateur.getMarque(), ordinateur.getRam());
		daoOrdinateur.delete(ordinateur);
	}

}
