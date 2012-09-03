package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parsers.URLparser;

import dicode.UsersManager;

/**
 * Servlet implementation class Activation
 */
public class Activation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Activation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("usr");
		String code = request.getParameter("cd");
		String contextPath = URLparser.giveContext();
	    
		String msg;
		
		//Establecer el error en caso de que no se hayan obtenido ambos parámetros
		
		UsersManager man = new UsersManager();
		String userID = man.isUser(userName);
		if (userID.equals("0")) {
			msg = "User not found. Please, contact the Dicode Workbench administrator";
			response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath));
		} else {
			String result = man.validateConfirmation(userID, code);
			if (result.equals("1")) {
				result = man.updateState (userID, "2");
				if (result.equals("1")) {
					result = man.deleteConfirmation(userID);
					if (result.equals("0")) {
						msg = "There was an error during the process. Please, contact the Dicode Workbench administrator";
						response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath));
					} else {
						msg = "Your account has been activated. Now you can log into the system";
						response.sendRedirect(response.encodeRedirectURL(contextPath + "/confirmation.jsp?msg="+msg+"&link="+contextPath));
					}
				} else {
					msg = "There was an error activating your account. Please, contact the Dicode Workbench administrator";
					response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath));
				}
			} else {
				msg = "There was an error validating your confirmation code. Please, contact the Dicode Workbench administrator";
				response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath));
			}
		}
	}

}
