package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ProjectAPI")
public class ProjectsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Project projObject = new Project();

    /**
     * Default constructor. 
     */
    public ProjectsAPI() {
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
		// TODO Auto-generated method stub
		System.out.println("Got insert");
		String pcode = request.getParameter("projectCode");
		String pname = request.getParameter("projectName");
		String rcode = request.getParameter("researcherCode");
		String rname = request.getParameter("researcherName");
		String eprice = request.getParameter("projectPrice");
		String pdesc = request.getParameter("projectDescription");

		String output =  projObject.insertProject(pcode, pname, rcode, rname, eprice, pdesc);
				
				}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got here");
		Map paras = getParasMap(request); 
		String pID = paras.get("hidprojectIDSave").toString();
		String pcode = paras.get("projectCode").toString();
		String pname = paras.get("projectName").toString();
		String rcode = paras.get("researcherCode").toString();
		String rname = paras.get("researcherName").toString();
		String eprice = paras.get("projectPrice").toString();
		String pdesc = paras.get("projectDescription").toString();
		
		String output = projObject.updateProject(pID, pcode, pname, rcode, rname, eprice, pdesc) ;
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = projObject.deleteProject(paras.get("projectID").toString()); 
		response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}


}
