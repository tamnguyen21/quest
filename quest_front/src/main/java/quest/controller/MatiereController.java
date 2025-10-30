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
import quest.model.Matiere;
import quest.service.MatiereService;


@WebServlet("/matiere")
public class MatiereController extends HttpServlet {

	
	private MatiereService matiereSrv;
	
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		matiereSrv = ctx.getBean(MatiereService.class);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null) 
		{
			if(request.getParameter("searchLike")==null) 
			{
				allMatieres(request,response);
			}
			else 
			{
				allMatiereLibelleLike(request,response);
			}
		}
		else 
		{
			if(request.getParameter("delete")==null) 
			{
				ficheMatiere(request, response);
			}

			else 
			{
				supprimerMatiere(request,response);
			}
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("id")==null) {
			ajoutMatiere(request,response);
		}
		else 
		{
			modifierMatiere(request,response);
		}

	}






	public void ficheMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		
		
		Integer id=Integer.parseInt(request.getParameter("id"));
		Matiere matiereBdd = matiereSrv.getById(id);

		request.setAttribute("matiere", matiereBdd);

		this.getServletContext().getRequestDispatcher("/WEB-INF/updateMatiere.jsp").forward(request, response);
	}



	public void allMatieres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		List<Matiere> matieres = matiereSrv.getAll();
		request.setAttribute("matieres", matieres);
		this.getServletContext().getRequestDispatcher("/WEB-INF/matiere.jsp").forward(request, response);

	}


	public void modifierMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		String libelle = (request.getParameter("libelle"));
		Matiere matiere = new Matiere(id,libelle);

		matiereSrv.update(matiere);
		
		response.sendRedirect("matiere");
	}
	public void ajoutMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String libelle = (request.getParameter("libelle"));
		Matiere matiere = new Matiere(null,libelle);
		
		matiereSrv.create(matiere);

		
		response.sendRedirect("matiere");

	}
	public void supprimerMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		matiereSrv.deleteById(id);
		response.sendRedirect("matiere");

	}

	private void allMatiereLibelleLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Matiere> matieres = matiereSrv.getAllByLibelleLike(request.getParameter("searchLike"));
		if(matieres.isEmpty()) 
		{
			response.getWriter().println("<tr><td align='center' colspan='3'>Aucun resultat</td></tr>");
		}
		for(Matiere matiere : matieres) {
		response.getWriter().println("<tr>");
		response.getWriter().println("<td>"+matiere.getId()+"</td>");
		response.getWriter().println("<td>"+matiere.getLibelle()+"</td>");
		response.getWriter().println("<td>");
		response.getWriter().println("<a href='matiere?id="+matiere.getId()+"'><input type='button' value='Modifier'></a>");
		response.getWriter().println("<a href='matiere?id="+matiere.getId()+"&delete'><input type='button' value='Supprimer'></a>");
		response.getWriter().println("</td>");
		response.getWriter().println("</tr>");
		}
	}

}
