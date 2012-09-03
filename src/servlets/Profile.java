package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parsers.URLparser;
import parsers.userParser;

import dicode.UsersManager;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		HttpSession sessionOk = request.getSession();
		if (sessionOk.getAttribute("userName") != null) {
		String userID = (String)sessionOk.getAttribute("userID");

		UsersManager man = new UsersManager();
		String result = man.user(userID);
		
		if (result.equals("0")) {
			request.setAttribute("error", "Unknown error loading profile");
            try {
                  getServletConfig().getServletContext().getRequestDispatcher(
                    "/index.jsp").forward(request,response);

                } catch (ServletException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
          
		} else {
			userParser pars = new userParser(result);
			
			String userName = pars.getUserName();
			String fullName = pars.getFullName();
			String organization = pars.getOrganization();
			String eMail = pars.geteMail();
			
			request.setAttribute("userName", userName);
            request.setAttribute("fullName", fullName);
            
            request.setAttribute("organization", organization);
            
            request.setAttribute("eMail", eMail);
            
            if (!(request.getAttribute("errorFull")==null)) {
            	String errorFull = request.getAttribute("errorFull").toString();
            	request.setAttribute ("errorFull", errorFull);
            }
            if (!(request.getAttribute("errorMail")==null)) {
            	String errorMail = request.getAttribute("errorMail").toString();
            	request.setAttribute ("errorMail", errorMail);
            }
            if (!(request.getAttribute("errorPass")==null)) {
            	String errorPass = request.getAttribute("errorPass").toString();
            	request.setAttribute ("errorPass", errorPass);
            }
            if (!(request.getAttribute("errorNPass")==null)) {
            	String errorNPass = request.getAttribute("errorNPass").toString();
            	request.setAttribute ("errorNPass", errorNPass);
            }
            if (!(request.getAttribute("errorCNPass")==null)) {
            	String errorCNPass = request.getAttribute("errorCNPass").toString();
            	request.setAttribute ("errorCNPass", errorCNPass);
            }
            
            try {
                getServletConfig().getServletContext().getRequestDispatcher(
                  "/profile.jsp").forward(request,response);

              } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
		}
		
		} else {
			String contextPath = URLparser.giveContext();
			response.sendRedirect(response.encodeRedirectURL(contextPath + "/index.jsp?error=Login is needed"));
		}
	}
	

}
