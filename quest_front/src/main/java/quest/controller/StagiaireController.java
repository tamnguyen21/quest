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
import quest.model.Filiere;
import quest.model.Ordinateur;
import quest.model.Stagiaire;
import quest.service.FiliereService;
import quest.service.OrdinateurService;
import quest.service.PersonneService;


@WebServlet("/stagiaire")
public class StagiaireController extends HttpServlet {

	private PersonneService personneSrv;
	private OrdinateurService ordinateurSrv;
	private FiliereService filiereSrv;
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		personneSrv = ctx.getBean(PersonneService.class);
		ordinateurSrv = ctx.getBean(OrdinateurService.class);
		filiereSrv = ctx.getBean(FiliereService.class);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null) 
		{
			allStagiaires(request,response);
		}
		else 
		{
			if(request.getParameter("delete")==null) 
			{
				ficheStagiaire(request, response);
			}

			else 
			{
				supprimerStagiaire(request,response);
			}
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("id")==null) {
			ajoutStagiaire(request,response);
		}
		else 
		{
			modifierStagiaire(request,response);
		}

	}






	public void ficheStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		Integer id=Integer.parseInt(request.getParameter("id"));
		Stagiaire  stagiaireBdd = personneSrv.getStagiaireById(id);

		request.setAttribute("filieres", filiereSrv.getAll());
		request.setAttribute("ordinateurs", ordinateurSrv.getAll());
		request.setAttribute("civilites", Civilite.values());
		request.setAttribute("stagiaire", stagiaireBdd);
		this.getServletContext().getRequestDispatcher("/WEB-INF/updateStagiaire.jsp").forward(request, response);
	}



	public void allStagiaires(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Stagiaire> stagiaires = personneSrv.getAllStagiaires();

		request.setAttribute("filieres", filiereSrv.getAll());
		request.setAttribute("ordinateurs",ordinateurSrv.getAll());
		request.setAttribute("civilites", Civilite.values());
		request.setAttribute("stagiaires", stagiaires);
		this.getServletContext().getRequestDispatcher("/WEB-INF/stagiaire.jsp").forward(request, response);
	}


	public void modifierStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String numero = request.getParameter("adresse.numero");
		String voie = request.getParameter("adresse.voie");
		String cp = request.getParameter("adresse.cp");
		String ville = request.getParameter("adresse.ville");
		Integer idordi=Integer.parseInt(request.getParameter("ordinateur.id"));
		Ordinateur  ordiBdd =ordinateurSrv.getById(idordi);
		Filiere filBdd = filiereSrv.getById(Integer.parseInt(request.getParameter("filiere.id")));

		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));

		Stagiaire stagiaire = new Stagiaire(id,login, password, nom, prenom, civilite, email, numero, voie, ville, cp, ordiBdd, filBdd);

		personneSrv.update(stagiaire);

		response.sendRedirect("stagiaire");
	}
	public void ajoutStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String numero = request.getParameter("adresse.numero");
		String voie = request.getParameter("adresse.voie");
		String cp = request.getParameter("adresse.cp");
		String ville = request.getParameter("adresse.ville");
		Integer idordi=Integer.parseInt(request.getParameter("ordinateur.id"));
		Ordinateur  ordiBdd = ordinateurSrv.getById(idordi);
		Filiere filBdd = filiereSrv.getById(Integer.parseInt(request.getParameter("filiere.id")));
		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));

		Stagiaire stagiaire = new Stagiaire(login, password, nom, prenom, civilite, email, numero, voie, ville, cp, ordiBdd, filBdd);

		personneSrv.create(stagiaire);

		response.sendRedirect("stagiaire");
	}

	public void supprimerStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));

		personneSrv.deleteById(id);

		response.sendRedirect("stagiaire");

	}

}
