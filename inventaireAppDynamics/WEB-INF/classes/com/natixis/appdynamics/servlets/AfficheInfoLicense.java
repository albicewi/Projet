package com.natixis.appdynamics.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.natixis.appdynamics.traitements.GetInfoLicense;

@WebServlet(description = "Afficher tous les nodes", urlPatterns = { "/AfficheInfoLicense" }, loadOnStartup = 1)
public class AfficheInfoLicense extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficheInfoLicense() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		GetInfoLicense getInfoLicence = new GetInfoLicense();
		Map<String, Object> myMapInfoLicense = getInfoLicence.RetrieveInfoLicense();
		request.setAttribute("myMapInfoLicense", myMapInfoLicense);
		this.getServletContext().getRequestDispatcher("/WEB-INF/AfficheInfoLicense.jsp").forward(request, response);		
	}

}
