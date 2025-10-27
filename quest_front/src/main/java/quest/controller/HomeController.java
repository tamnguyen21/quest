package quest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quest.context.Singleton;
import quest.model.Formateur;
import quest.model.Personne;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("logout")!=null) 
		{
			request.getSession().invalidate();
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else 
		{


			Personne connected = (Personne) request.getSession().getAttribute("connected");
			if(connected==null) 
			{
				request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else 
			{
				if(connected instanceof Formateur) 
				{

					request.getServletContext().getRequestDispatcher("/WEB-INF/espaceFormateur.jsp").forward(request, response);
				}
				else 
				{
					request.getServletContext().getRequestDispatcher("/WEB-INF/espaceStagiaire.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		Personne connected = Singleton.getInstance().getDaoPersonne().findByLoginAndPassword(request.getParameter("login"), request.getParameter("password"));

		if(connected==null) 
		{
			request.setAttribute("error", "Identifiants invalides");
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else 
		{
			request.getSession().setAttribute("connected",connected);
			if(connected instanceof Formateur) 
			{

				request.getServletContext().getRequestDispatcher("/WEB-INF/espaceFormateur.jsp").forward(request, response);
			}
			else 
			{
				request.getServletContext().getRequestDispatcher("/WEB-INF/espaceStagiaire.jsp").forward(request, response);
			}
		}


	}

}
