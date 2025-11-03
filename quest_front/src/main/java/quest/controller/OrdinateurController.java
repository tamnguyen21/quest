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
import quest.model.Ordinateur;
import quest.service.OrdinateurService;


@WebServlet("/ordinateur")
public class OrdinateurController extends HttpServlet {

	private OrdinateurService ordinateurSrv;

	public void init(ServletConfig config) throws ServletException
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
	}

}