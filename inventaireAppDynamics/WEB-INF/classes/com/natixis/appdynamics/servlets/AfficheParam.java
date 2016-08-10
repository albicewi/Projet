package com.natixis.appdynamics.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.natixis.appdynamics.traitements.GetParamApplication;

@WebServlet("/AfficheParam")
public class AfficheParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AfficheParam() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());		
		
		Map<String,String> mapParamAppli = GetParamApplication.mapParamAppli;		
		
		request.setAttribute("mapParamAppli", mapParamAppli);
		request.setAttribute("passwordApm", mapParamAppli.get(GetParamApplication.PASSWORD_APM));
		this.getServletContext().getRequestDispatcher("/WEB-INF/AfficheParam.jsp").forward(request, response);
	}
}
