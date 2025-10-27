package quest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quest.context.Singleton;
import quest.model.Civilite;
import quest.model.Filiere;
import quest.model.Ordinateur;
import quest.model.Stagiaire;


@WebServlet("/stagiaire")
public class StagiaireController extends HttpServlet {

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
		Stagiaire  stagiaireBdd = (Stagiaire) Singleton.getInstance().getDaoPersonne().findById(id);

		request.setAttribute("filieres", Singleton.getInstance().getDaoFiliere().findAll());
		request.setAttribute("ordinateurs", Singleton.getInstance().getDaoOrdinateur().findAll());
		request.setAttribute("civilites", Civilite.values());
		request.setAttribute("stagiaire", stagiaireBdd);
		this.getServletContext().getRequestDispatcher("/WEB-INF/updateStagiaire.jsp").forward(request, response);
	}



	public void allStagiaires(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Stagiaire> stagiaires = Singleton.getInstance().getDaoPersonne().findAllStagiaire();

		request.setAttribute("filieres", Singleton.getInstance().getDaoFiliere().findAll());
		request.setAttribute("ordinateurs", Singleton.getInstance().getDaoOrdinateur().findAll());
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
		Ordinateur  ordiBdd = Singleton.getInstance().getDaoOrdinateur().findById(idordi);
		Filiere filBdd = Singleton.getInstance().getDaoFiliere().findById(Integer.parseInt(request.getParameter("filiere.id")));

		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));

		Stagiaire stagiaire = new Stagiaire(id,login, password, nom, prenom, civilite, email, numero, voie, ville, cp, ordiBdd, filBdd);

		Singleton.getInstance().getDaoPersonne().save(stagiaire);

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
		Ordinateur  ordiBdd = Singleton.getInstance().getDaoOrdinateur().findById(idordi);
		Filiere filBdd = Singleton.getInstance().getDaoFiliere().findById(Integer.parseInt(request.getParameter("filiere.id")));
		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));

		Stagiaire stagiaire = new Stagiaire(login, password, nom, prenom, civilite, email, numero, voie, ville, cp, ordiBdd, filBdd);

		Singleton.getInstance().getDaoPersonne().save(stagiaire);

		response.sendRedirect("stagiaire");
	}

	public void supprimerStagiaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));

		Singleton.getInstance().getDaoPersonne().deleteById(id);

		response.sendRedirect("stagiaire");

	}

}
