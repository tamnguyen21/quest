package quest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import quest.config.AppConfig;
import quest.model.Civilite;
import quest.model.Formateur;
import quest.service.PersonneService;


@WebServlet("/formateur")
public class FormateurController extends HttpServlet {

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null) 
		{
			allFormateurs(request,response);
		}
		else 
		{
			if(request.getParameter("delete")==null) 
			{
				ficheFormateur(request, response);
			}

			else 
			{
				supprimerFormateur(request,response);
			}
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("id")==null) {
			ajoutFormateur(request,response);
		}
		else 
		{
			modifierFormateur(request,response);
		}

	}

	
	
	
	

	public void ficheFormateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PersonneService personneSrv = ctx.getBean(PersonneService.class);
		
		
		Integer id=Integer.parseInt(request.getParameter("id"));
		Formateur formateurBdd = personneSrv.getFormateurById(id);

		request.setAttribute("formateur", formateurBdd);
		request.setAttribute("civilites", Civilite.values());
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/updateFormateur.jsp").forward(request, response);
	}



	public void allFormateurs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PersonneService personneSrv = ctx.getBean(PersonneService.class);
		
		List<Formateur> formateurs = personneSrv.getAllFormateurs();
		request.setAttribute("formateurs", formateurs);
	
		request.setAttribute("civilites", Civilite.values());
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/formateur.jsp").forward(request, response);
		
	}
	
	
	public void modifierFormateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PersonneService personneSrv = ctx.getBean(PersonneService.class);
		
		Integer id=Integer.parseInt(request.getParameter("id"));
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		//boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
		boolean admin = (request.getParameter("admin")==null)?false:true;
		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));

		Formateur formateur = new Formateur(id,login,password, nom, prenom, civilite, admin);

		personneSrv.update(formateur);
		
		response.sendRedirect("formateur");
	}
	
	
	public void ajoutFormateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PersonneService personneSrv = ctx.getBean(PersonneService.class);
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		//boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
		boolean admin = (request.getParameter("admin")==null)?false:true;
		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));

		Formateur formateur = new Formateur(login,password, nom, prenom, civilite, admin);

		personneSrv.create(formateur);
		
		response.sendRedirect("formateur");
	}
	public void supprimerFormateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		PersonneService personneSrv = ctx.getBean(PersonneService.class);
		
		Integer id=Integer.parseInt(request.getParameter("id"));
		
		personneSrv.deleteById(id);
		response.sendRedirect("formateur");
		
	}



}
