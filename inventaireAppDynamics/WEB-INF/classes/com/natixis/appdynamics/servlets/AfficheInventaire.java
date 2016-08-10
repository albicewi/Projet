package com.natixis.appdynamics.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Afficher tous les nodes", urlPatterns = { "/AfficheInventaire" }, loadOnStartup = 1)
public class AfficheInventaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficheInventaire() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().append("Served at: ").append(request.getContextPath());		
		this.getServletContext().getRequestDispatcher("/WEB-INF/AfficheInventaire.jsp").forward(request, response);		
	}

}
