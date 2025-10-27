package quest.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quest.model.Filiere;
import quest.context.Singleton;

@WebServlet("/filiere")
public class FiliereController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null) 
		{
			allFilieres(request,response);
		}
		else 
		{
			if(request.getParameter("delete")==null) 
			{
				ficheFiliere(request, response);
			}

			else 
			{
				supprimerFiliere(request,response);
			}
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("id")==null) {
			ajoutFiliere(request,response);
		}
		else 
		{
			modifierFiliere(request,response);
		}

	}






	public void ficheFiliere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		Integer id=Integer.parseInt(request.getParameter("id"));
		Filiere filiere = Singleton.getInstance().getDaoFiliere().findById(id);

		request.setAttribute("filiere", filiere);

		this.getServletContext().getRequestDispatcher("/WEB-INF/updateFiliere.jsp").forward(request, response);
	}



	public void allFilieres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Filiere> filieres = Singleton.getInstance().getDaoFiliere().findAll();
		request.setAttribute("filieres", filieres);

		this.getServletContext().getRequestDispatcher("/WEB-INF/filiere.jsp").forward(request, response);

	}


	public void modifierFiliere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		Integer id=Integer.parseInt(request.getParameter("id"));
		String libelle = request.getParameter("libelle");
		LocalDate debut = LocalDate.parse(request.getParameter("debut"));		
		LocalDate fin = LocalDate.parse(request.getParameter("fin"));
		Integer version = Integer.parseInt(request.getParameter("version"));

		Filiere filiere = new Filiere(id,libelle,debut,fin);
		filiere.setVersion(version);
		Singleton.getInstance().getDaoFiliere().save(filiere);

		response.sendRedirect("filiere");
	}

	public void ajoutFiliere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String libelle = request.getParameter("libelle");
		LocalDate debut = LocalDate.parse(request.getParameter("debut"));		
		LocalDate fin = LocalDate.parse(request.getParameter("fin"));


		Filiere filiere = new Filiere(null,libelle,debut,fin);

		Singleton.getInstance().getDaoFiliere().save(filiere);

		response.sendRedirect("filiere");

	}
	public void supprimerFiliere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		Integer id=Integer.parseInt(request.getParameter("id"));

		Singleton.getInstance().getDaoFiliere().deleteById(id);

		response.sendRedirect("filiere");

	}
}
