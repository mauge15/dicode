package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parsers.URLparser;
import parsers.userParser;

import dicode.JavaMailSamples;
import dicode.UsersManager;

/**
 * Servlet implementation class Mail
 */
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mail() {
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
		
		String contextPath = URLparser.giveContext();
		String mail = request.getParameter("newMail");
		String msg;
		if(mail.equals("")){
			request.setAttribute("errorMail", "Please, enter your e-mail address");
            try {
                  getServletConfig().getServletContext().getRequestDispatcher(
                    "/remember.jsp").forward(request,response);

                } catch (ServletException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                } catch (IOException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                }
		}
		else{
		UsersManager man = new UsersManager();
		String userID = man.isMail(mail);
		

		 	if(userID.equals("0")){
		 		request.setAttribute("errorMail", "This email is not registered in the system");
            	try {
            		getServletConfig().getServletContext().getRequestDispatcher(
                    "/remember.jsp").forward(request,response);

                } catch (ServletException e) {
                  // TODO Auto-generated catch block
                		e.printStackTrace();
                } catch (IOException e) {
                  // TODO Auto-generated catch block
                		e.printStackTrace();
                }
			} else {
				String result = man.user(userID);
				userParser pars = new userParser(result);
				
				String userName = pars.getUserName();
				
				String newPass = man.generatePass();
				String[] entry = {"newpass",userName, newPass, mail}; 
				JavaMailSamples.main(entry);
				result = man.updatePass(userID, newPass);
				if (result.equals("1")) {
					msg = "You will receive an e-mail indicating your user name and the new password. When you enter the system again you must change the password.";
					response.sendRedirect(response.encodeRedirectURL(contextPath + "/confirmation.jsp?msg="+msg+"&link="+contextPath));
				} else {
					msg="There was an error during the process. Please, try again or contact the Dicode Workbench administrator.";
					response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath));
				}
			}
		}

	}
}
