package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parsers.ServiceParser;
import parsers.URLparser;

import dicode.ServicesManager;

/**
 * Servlet implementation class DeleteServiceDB
 */
public class DeleteServiceDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServiceDB() {
        super();
    }

    /**
  	 * @see HttpServlet#doGet(HttpServletRequest request,
  	 * HttpServletResponse response)
  	 */
  	protected void doGet(HttpServletRequest request,
  			HttpServletResponse response) throws ServletException, IOException {
  		doPost(request, response);
  	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServicesManager manS = new ServicesManager();
		String xml;
		String result;
		String srvID;
		String srvName;
		String userID;
		String msg;
		String contextPath = URLparser.giveContext();
		HttpSession sessionOk;
		
		ServiceParser pars1;
		
		// Get parameters
		srvID = (String)request.getParameter("srvID");
		srvName = (String)request.getParameter("srvName");
		sessionOk = request.getSession();
		userID = (String)sessionOk.getAttribute("userID");
		
		if (srvID == null || srvName == null) {
			msg = "The service can not be deleted. There is a problem with the " +
					"solicitude.";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath + "/services.jsp"));
			return;
		}
		
		// Check if the service exists
		xml = manS.service(srvID);
		pars1 = new ServiceParser(xml);
		if (pars1.getID() == null || pars1.getID().equals("-5")) {
			msg = "The service does not exist. Please, go to the services page and " +
					"select a service from the list.";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath + "/services.jsp"));
			return;
		}
		
		// Check if the user has permission to modify/delete the experiment
		if (!pars1.getUploaderUser().equals(userID)) {
			msg = "You have no permissions to delete this service.";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath + "/services.jsp"));
			return;
		}
		
		
		/*
		// Delete entries of experiment configuration table
		result = manS.deleteServiceInExperimentConfiguration(srvID);
		if (!result.equals("0")&& !result.equals("1")) {
			msg = "There has been an error trying to delete the service '" +
			srvName + "' of the system. ERROR01("+result+")";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath + "/services.jsp"));
			return;
		}
		
		// Delete entries of experiment includes service table
		result = manS.deleteServiceInExperiment(srvID);
		if (!result.equals("0")&& !result.equals("1")) {
			msg = "There has been an error trying to delete the service '" +
			srvName + "' of the system. ERROR02("+result+")";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath + "/services.jsp"));
			return;
		}*/
		
		// TODO Delete the service from the database
		result = manS.deleteService(srvID);
		if (!result.equals("0")&& !result.equals("1")) {
			msg = "There has been an error trying to delete the service '" +
			srvName + "' of the system. ERROR03("+result+")";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath + "/services.jsp"));
			return;
		}
		
		msg = "The service '" + srvName + "' has been deleted.";
		response.sendRedirect(response.encodeRedirectURL(contextPath +
				"/confirmation.jsp?msg=" + msg + "&link=" + contextPath +
				"/services.jsp"));
		
	}

}
