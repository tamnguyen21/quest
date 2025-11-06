package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import quest.service.PersonneService;

@Controller
public class HomeController {
	@Autowired
	PersonneService personneSrv;

	@GetMapping("/login")
	public String connectedRedirect() {
		return "index";
	}

	@GetMapping("/home")
	public String home(@AuthenticationPrincipal UserDetails user) {
		// Affiche selon le rôle de l'utilisateur connecté
		// UserDetails injecté par Spring via l'annotation AuthenticationPrincipal

		if (user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_FORMATEUR"))) {
			return "espaceFormateur";
		}

		return "espaceStagiaire";
	}
}
