package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parsers.CoPeItParser;
import parsers.URLparser;

import dicode.CopeRESTClient;
import dicode.UsersManager;

/**
 * Servlet implementation class ChangeProfile
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		String userName= (String)sessionOk.getAttribute("userName");
		String password=request.getParameter("password");
		String nPassword=request.getParameter("nPassword");
		String cNPassword=request.getParameter("cNPassword");
		String contextPath = URLparser.giveContext();
		String copeAdmin = URLparser.giveCIUsername();
		String copeAdPass = URLparser.giveCIPass();
	    String msg;
		UsersManager man = new UsersManager();
		String userID = man.isUser(userName);
		ArrayList<String> list = new ArrayList<String>();
		if(password.equals("")){
			list.add("1");
		}
		if(nPassword.equals("")){
			list.add("2");
		}
		if(cNPassword.equals("")){
			list.add("3");
		}
		if(list.isEmpty()){
			if (userID.equals("0")){
				msg = "There was an error updating password";
				response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath+"/Profile"));
			} else {
				String result = man.validateUser(userName, password);
				System.out.println(result);
				if (result.equals("0")) {
					request.setAttribute("errorPass", "Invalid Password");
					try {
						getServletConfig().getServletContext().getRequestDispatcher(
						"/Profile").forward(request,response);

					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					String update = man.updatePass(userID, nPassword);
					System.out.println(update);
					if (update.equals("1")){
						// Update password in CoPeIt
						int error = 0;
					    CoPeItParser pars;
					    String code = null;
					    result=null;
						System.out.println("\n\nLOGIN");
					    try {
							result = CopeRESTClient.copeItLoginComplete(copeAdmin, copeAdPass);
							result = result.substring(1);
							System.out.println("Resultado de login: "+result);
							pars = new CoPeItParser(result);
							code = pars.getCode();
							if (!code.equals("0"))
								error += 1;
							System.out.println(result);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						if (error==0) {
							System.out.println("\n\nUPDATE PASSWORD");
							result=null;
							try {
								result = CopeRESTClient.copeItUpdatePassword(userName, nPassword);
								result = result.substring(1);
								pars = new CoPeItParser(result);
								code = pars.getCode();
								if (!code.equals("0"))
									error += 1;
								System.out.println(result+"\n");
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						
						if (error==0) {
							System.out.println("\n\nLOGOUT");
							result=null;
							try {
								result = CopeRESTClient.copeItLogoutComplete(copeAdmin);
								result = result.substring(1);
								pars = new CoPeItParser(result);
								code = pars.getCode();
								if (!code.equals("0"))
									error += 1;
								System.out.println(result+"\n");
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						if (error==0) {
						msg = "Password has been changed";
						response.sendRedirect(response.encodeRedirectURL(contextPath + "/confirmation.jsp?msg="+msg+"&link="+contextPath+"/Profile"));
						}
						else {
							msg = "There was an error updating password (CoPeIt)";
							response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath+"/Profile"));
						} 
					} else {
						msg = "There was an error updating password";
						response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath+"/Profile"));
					} 
				}

			}
		} else {
			for(int i=0;i<list.size();i++){
				String item = list.get(i).toString();
				if(item.equals("1")){
					request.setAttribute("errorPass", "Must not be empty");
				}
				else if(item.equals("2")){
					request.setAttribute("errorNPass", "Must not be empty");
				}
				else if(item.equals("3")){
					request.setAttribute("errorCNPass", "Must not be empty");
				}
			}
			try {
                getServletConfig().getServletContext().getRequestDispatcher(
                  "/Profile").forward(request,response);

            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
	}

}
