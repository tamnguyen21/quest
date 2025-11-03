package quest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.model.Formateur;
import quest.model.Personne;
import quest.service.PersonneService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	PersonneService personneSrv;
	
	@GetMapping("/logout")
	public String disconnect(HttpSession session) 
	{
		session.invalidate();
		return "redirect:/home";
	}
	
	@GetMapping 
	public String connectedRedirect(HttpSession session) 
	{
		Personne connected = (Personne) session.getAttribute("connected");
		if(connected==null) 
		{
			return "index";
		}
		else 
		{
			if(connected instanceof Formateur) 
			{

				return "espaceFormateur";
			}
			else 
			{
				return "espaceStagiaire";			
			}
		}
	}
	
	@PostMapping
	public String connect(String login,String password,Model model,HttpSession session) 
	{
		System.out.println("here connexion");
		Personne connected = personneSrv.getByLoginAndPassword(login,password);
		System.out.println(connected);
		if(connected==null) 
		{
			model.addAttribute("error", "Identifiants invalides");
			return "index";
		}
		else 
		{
			session.setAttribute("connected",connected);
			if(connected instanceof Formateur) 
			{

				return "espaceFormateur";
			}
			else 
			{
				return "espaceStagiaire";			
			}
		}
	}
	
}
