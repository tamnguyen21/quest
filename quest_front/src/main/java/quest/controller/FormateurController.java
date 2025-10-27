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
import quest.model.Formateur;


@WebServlet("/formateur")
public class FormateurController extends HttpServlet {

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
		Integer id=Integer.parseInt(request.getParameter("id"));
		Formateur formateurBdd = (Formateur) Singleton.getInstance().getDaoPersonne().findById(id);

		request.setAttribute("formateur", formateurBdd);
		request.setAttribute("civilites", Civilite.values());
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/updateFormateur.jsp").forward(request, response);
	}



	public void allFormateurs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Formateur> formateurs = Singleton.getInstance().getDaoPersonne().findAllFormateur();
		request.setAttribute("formateurs", formateurs);
	
		request.setAttribute("civilites", Civilite.values());
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/formateur.jsp").forward(request, response);
		
	}
	
	
	public void modifierFormateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		//boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
		boolean admin = (request.getParameter("admin")==null)?false:true;
		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));

		Formateur formateur = new Formateur(id,login,password, nom, prenom, civilite, admin);

		Singleton.getInstance().getDaoPersonne().save(formateur);
		
		response.sendRedirect("formateur");
	}
	
	
	public void ajoutFormateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		//boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
		boolean admin = (request.getParameter("admin")==null)?false:true;
		Civilite civilite = Civilite.valueOf(request.getParameter("civilite"));

		Formateur formateur = new Formateur(login,password, nom, prenom, civilite, admin);

		Singleton.getInstance().getDaoPersonne().save(formateur);
		
		response.sendRedirect("formateur");
	}
	public void supprimerFormateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		
		Singleton.getInstance().getDaoPersonne().deleteById(id);
		response.sendRedirect("formateur");
		
	}



}
