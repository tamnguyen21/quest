package demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {


	@RequestMapping(value="/home")
	public String accueil() 
	{
		return "/WEB-INF/demo.jsp";
	}

	@RequestMapping(value="/demo",method = RequestMethod.POST)
	public String recevoirFormAccueil(String prenom,int age,Model model,HttpSession session) 
	{
		session.setAttribute("loginSession", "tpt");
		System.out.println(prenom);
		System.out.println(age);
		model.addAttribute("maVariable", prenom+"-"+age);
		return "/WEB-INF/apresForm.jsp";
	}

}
