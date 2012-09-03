package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parsers.ListUsersParser;
import parsers.URLparser;
import parsers.userParser;

import dicode.CopeRESTClient;
import dicode.UsersManager;

/**
 * Servlet implementation class CopeitGrant
 */
public class CopeItGrant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CopeItGrant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String xml=null;
		String xml2=null;
		UsersManager manU;
		ListUsersParser pars1;
		userParser pars2;
		int numUsers, count;
		String userID;
		String username;

		
		String copeAdmin = URLparser.giveCIUsername();
		String copeAdPass = URLparser.giveCIPass();
		
		String type = request.getParameter("type");
		
		manU = new UsersManager();
		xml = manU.listUsers();
		pars1 = new ListUsersParser(xml);
		numUsers = pars1.getNumberUsers();
		try {
			CopeRESTClient.copeItLoginComplete(copeAdmin, copeAdPass);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for (count = 0; count < numUsers; count++){
			userID = pars1.getID(count);
			xml2 = manU.user(userID);
			pars2 = new userParser(xml2);
			username = pars2.getUserName();
			
			//GRANT
			try {
				CopeRESTClient.copeItGrantPrivileges(username, type);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		try {
			CopeRESTClient.copeItLogoutComplete(copeAdmin);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Hello WWW</TITLE></HEAD>\n" +
                "<BODY>\n");
		String xml=null;
		String xml2=null;
		UsersManager manU;
		ListUsersParser pars1;
		userParser pars2;
		int numUsers, count;
		String userID;
		String username;
		String result; //
		
		String copeAdmin = URLparser.giveCIUsername();
		String copeAdPass = URLparser.giveCIPass();
		
		String type = request.getParameter("type");
		
		manU = new UsersManager();
		xml = manU.listUsers();
		pars1 = new ListUsersParser(xml);
		numUsers = pars1.getNumberUsers();
		try {
			result = CopeRESTClient.copeItLoginComplete(copeAdmin, copeAdPass);//
			CopeRESTClient.copeItLoginComplete(copeAdmin, copeAdPass);
			out.println("<p>Resultado de logear al administrador: "+result+"</p>\n");//
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for (count = 0; count < numUsers; count++){
			userID = pars1.getID(count);
			xml2 = manU.user(userID);
			pars2 = new userParser(xml2);
			username = pars2.getUserName();
			out.println("<p>--Dando privilegios a "+username+" en el workspace "+type+"</p>\n"); //
			
			//GRANT
			result=null; //
			try {
				result = CopeRESTClient.copeItGrantPrivileges(username, type); //
				CopeRESTClient.copeItGrantPrivileges(username, type);
				result = result.substring(1);//
				out.println("<p>Resultado: "+result+"</p>\n"); //
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		
		try {
			result = CopeRESTClient.copeItLogoutComplete(copeAdmin); //
			CopeRESTClient.copeItLogoutComplete(copeAdmin);
			result = result.substring(1);//
			out.println("<p>Resultado de deslogear al administrador: "+result+"</p>\n");//
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		out.println("</BODY></HTML>");//
	}
	 * */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
