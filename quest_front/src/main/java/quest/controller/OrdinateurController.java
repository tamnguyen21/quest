package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.model.Ordinateur;
import quest.service.OrdinateurService;

@Controller
@RequestMapping("/ordinateur")
public class OrdinateurController {
	
	@Autowired
	private OrdinateurService ordinateurSrv;
	
	@GetMapping
	public String allOrdinateurs(Model model) {
		model.addAttribute("ordinateur",ordinateurSrv.getAll());
		model.addAttribute("ordinateur",new Ordinateur());
		return "ordinateurs";
	}
	
	@GetMapping("/{id}")
	public String ficheOrdinateur(@PathVariable Integer id, Model model ) {
		Ordinateur ordinateur = ordinateurSrv.getById(id);
		model.addAttribute("ordinateur",ordinateur);
		return "updateOrdinateur";
	}
	
	@GetMapping("/delete/{id}")
	public String supprimerOrdinateur(@PathVariable Integer id) {
		ordinateurSrv.deleteById(id);
		return "redirect:/ordinateur";
	}
	
	@PostMapping
	public String ajoutOrdinateur(@ModelAttribute Ordinateur ordinateur, BindingResult result, Model model) {
		ordinateurSrv.create(ordinateur);
		return "redirect:/ordinateur";
		
	}
	
	/*public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ordinateurSrv = ctx.getBean(OrdinateurService.class);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null) 
		{
			allOrdinateurs(request,response);
		}
		else 
		{
			if(request.getParameter("delete")==null) 
			{
				ficheOrdinateur(request, response);
			}

			else 
			{
				supprimerOrdinateur(request,response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("id")==null) {
			ajoutOrdinateur(request,response);
		}
		else 
		{
			modifierOrdinateur(request,response);
		}
	}


	public void ficheOrdinateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		Ordinateur tableBdd = ordinateurSrv.getById(id);

		request.setAttribute("ordinateur", tableBdd);
		this.getServletContext().getRequestDispatcher("/WEB-INF/updateOrdinateur.jsp").forward(request, response);
	}


	public void allOrdinateurs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Ordinateur> ordinateurs =ordinateurSrv.getAll();
		request.setAttribute("ordinateurs", ordinateurs);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ordinateur.jsp").forward(request, response);
	}
	
	
	public void modifierOrdinateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		String marque = request.getParameter("marque");
		int ram = Integer.parseInt(request.getParameter("ram"));

		Ordinateur ordinateur = new Ordinateur(id,marque,ram);

		ordinateurSrv.update(ordinateur);
		
		response.sendRedirect("ordinateur");
	}
	
	
	public void ajoutOrdinateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String marque = request.getParameter("marque");
		int ram = Integer.parseInt(request.getParameter("ram"));

		Ordinateur ordinateur = new Ordinateur(marque,ram);
		ordinateurSrv.create(ordinateur);
		
		response.sendRedirect("ordinateur");
	}
	
	public void supprimerOrdinateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		
		ordinateurSrv.deleteById(id);
		
		response.sendRedirect("ordinateur");
	}*/

}
