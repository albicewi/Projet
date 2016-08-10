package com.natixis.appdynamics.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.natixis.appdynamics.beans.BeanNode;
import com.natixis.appdynamics.traitements.MakeCalculByEntity;

/**
 * Servlet implementation class CalculByEntity
 */
@WebServlet("/CalculByEntity")
public class CalculByEntity extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CalculByEntity() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Map<String, Integer> mapSommeByDomaine = new HashMap<String, Integer>();
		Map<String, Map<String, ArrayList>> mapSommeByProdAppli = new HashMap<String, Map<String, ArrayList>>();
		
		//On récupère la Map contenant <agent, beanAgent>
		ServletContext context = this.getServletContext();
		Map<String, BeanNode> MyMapNode = (Map<String, BeanNode>) context.getAttribute("MapNode");		
		
		MakeCalculByEntity MyCalcul = new MakeCalculByEntity ();
		mapSommeByDomaine=MyCalcul.calculByDomaine(MyMapNode);
		mapSommeByProdAppli=MyCalcul.calculByProdAppli(MyMapNode);
		
		request.setAttribute("mapSommeByDomaine", mapSommeByDomaine);
		request.setAttribute("mapSommeByProdAppli", mapSommeByProdAppli);		
		this.getServletContext().getRequestDispatcher("/WEB-INF/CalculByEntity.jsp").forward(request, response);
	}

}
