package quest.controller;

import java.io.IOException;
import java.time.LocalDate;
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
import quest.dao.IDAOFiliere;
import quest.model.Filiere;

@WebServlet("/filiere")
public class FiliereController extends HttpServlet {

	private IDAOFiliere daoFiliere;
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		daoFiliere = ctx.getBean(IDAOFiliere.class);
	}


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
		Filiere filiere = daoFiliere.findById(id).get();

		request.setAttribute("filiere", filiere);

		this.getServletContext().getRequestDispatcher("/WEB-INF/updateFiliere.jsp").forward(request, response);
	}



	public void allFilieres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		List<Filiere> filieres = daoFiliere.findAll();
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
		daoFiliere.save(filiere);

		response.sendRedirect("filiere");
	}

	public void ajoutFiliere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String libelle = request.getParameter("libelle");
		LocalDate debut = LocalDate.parse(request.getParameter("debut"));		
		LocalDate fin = LocalDate.parse(request.getParameter("fin"));


		Filiere filiere = new Filiere(null,libelle,debut,fin);

		daoFiliere.save(filiere);

		response.sendRedirect("filiere");

	}
	public void supprimerFiliere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));

		daoFiliere.deleteById(id);

		response.sendRedirect("filiere");

	}
}
