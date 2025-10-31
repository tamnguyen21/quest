package demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class DemoController {


	@GetMapping("")
	public String accueil() 
	{
		return "/WEB-INF/demo.jsp";
	}
	
	@GetMapping("/demo/{chaine}")
	public String test(@PathVariable String chaine,String test) 
	{
		System.out.println(chaine);
		System.out.println(test);
		return "";
	}
	
	@PostMapping
	public String recevoirFormAccueil(String prenom,int age,Model model,HttpSession session) 
	{
		session.setAttribute("loginSession", "tpt");
		System.out.println(prenom);
		System.out.println(age);
		model.addAttribute("maVariable", prenom+"-"+age);
		return "/WEB-INF/apresForm.jsp";
	}

	
}
