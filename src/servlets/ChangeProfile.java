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
public class ChangeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeProfile() {
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
		HttpSession sessionOk = request.getSession();
		String userName= (String)sessionOk.getAttribute("userName");
		String fullName=request.getParameter("name");
		String mail=request.getParameter("mail");
		String organization=request.getParameter("organization");
		String copeAdmin = URLparser.giveCIUsername();
		String copeAdPass = URLparser.giveCIPass();
		String msg;
		UsersManager man = new UsersManager();
		String userID = man.isUser(userName);
		ArrayList<String> list = new ArrayList<String>();
		if(fullName.equals("")){
			list.add("1");
		}
		if(mail.equals("")){
			list.add("2");
		}
		if(list.isEmpty()){
			//System.out.print("idUser" + userID+ "\n");
			if (!(userID.equals("null"))){
				String result = man.isMail(mail);
				if (result.equals("0") || result.equals(userID)) {
					String update = man.updateUser(userID, fullName, userName, organization, mail);
					//System.out.print("update" + update+ "\n");
					if(update.equals("1")){
						// Change main in copeit
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
								result = CopeRESTClient.copeItUpdateEmail(userName, mail);
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
						msg="Profile has been updated";
						response.sendRedirect(response.encodeRedirectURL(contextPath + "/confirmation.jsp?msg="+msg+"&link="+contextPath+"/Profile"));
						} else {
							msg = "There was an error updating profile (CoPeIt)";
							response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath+"/Profile"));
						}
					} else {
						msg = "There was an error updating profile " + update;
						response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath+"/Profile"));
					}
				} else {
					request.setAttribute("errorMail", "Email is registered");
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
			} else {
				msg="There was an error updating profile (user not found in database). Please, send an email to the administrator including this error report";
				response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath+"/Profile"));
			}
		} else {
			for(int i=0;i<list.size();i++){
				String item = list.get(i).toString();
				if(item.equals("1")){
					request.setAttribute("errorFull", "Must not be empty");
				}
				else if(item.equals("2")){
					request.setAttribute("errorMail", "Must not be empty");
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
