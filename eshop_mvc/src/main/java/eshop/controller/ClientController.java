package eshop.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eshop.dao.IDAOPersonne;
import eshop.model.Client;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	IDAOPersonne daoPersonne;
	

	@GetMapping
	public String allClients(Model model) {
		model.addAttribute("clients", daoPersonne.findAllClient());
		model.addAttribute("client",new Client());
		model.addAttribute("action","client");
		return "clients/clients";
	}

	@GetMapping("/{id}")
	public String ficheClient(@PathVariable Integer id, Model model) {
		Client client = (Client) daoPersonne.findById(id).get();
		model.addAttribute("clients", daoPersonne.findAllClient());
		model.addAttribute("client", client);
		model.addAttribute("action","client/"+id);
		return "clients/clients";
	}

	@GetMapping("/delete/{id}")
	public String supprimerClient(@PathVariable Integer id) {
		daoPersonne.deleteById(id);
		return "redirect:/client";
	}

	@PostMapping
	public String ajoutClient(String prenom,String nom,int age,String dateNaissance,@RequestParam(name="adresse.numero")String numero,@RequestParam(name="adresse.voie")String voie,@RequestParam(name="adresse.ville")String ville,@RequestParam(name="adresse.cp")String cp) {
		Client client = new Client(prenom,nom,age,LocalDate.parse(dateNaissance),numero,voie,ville,cp);
		daoPersonne.save(client);
		return "redirect:/client";
	}

	@PostMapping("/{id}")
	public String modifierClient(@ModelAttribute Client client) {
		daoPersonne.save(client);
		return "redirect:/client";
	}

}
