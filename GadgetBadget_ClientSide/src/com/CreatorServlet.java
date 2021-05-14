package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Creator_IT19234526;


/**
 * Servlet implementation class CreatorServlet
 */
@WebServlet("/CreatorServlet")
public class CreatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Creator_IT19234526 creatorObj = new Creator_IT19234526();
	
    public CreatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = creatorObj.insertCreator(request.getParameter("fullname"), 
				request.getParameter("city"), 
				request.getParameter("contactnum"), 
				request.getParameter("email"), 
				request.getParameter("fieldofinterest"), 
				request.getParameter("currentbudget"));
		
		response.getWriter().write(result);
	}

	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();  
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
		 
		  String[] params = queryString.split("&");   
		  for (String param : params)   {
			  String[] p = param.split("=");    
			  map.put(p[0], p[1]);   
		  }  
		  
		}catch (Exception e)  {  
			
		} 
		return map;
	}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
        Map<String, String> param = getParasMap(request);
		
		String result = creatorObj.updateCreator(param.get("creatoridsave").toString(),
				param.get("fullname").toString().toString().replace("+", " "),     
		 		param.get("city").toString(),        
		 		param.get("contactnum").toString().toString().replace("%40", "@"),        
		 		param.get("email").toString(),
		 		param.get("fieldofinterest").toString().toString().replace("+", " "), 
		 		param.get("currentbudget").toString().toString().replace("+", " "));
		
		response.getWriter().write(result);
	
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Map<String, String> param = getParasMap(request);
		
		String result = creatorObj.deleteCreator(param.get("creatorid").toString());
		
		response.getWriter().write(result);
	}

}
