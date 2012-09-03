package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parsers.CoPeItParser;
import parsers.ExperimentParser;
import parsers.ListExperimentsParser;
import parsers.URLparser;

import dicode.CopeRESTClient;
import dicode.ExperimentsManager;
import dicode.JavaMailSamples;
import dicode.UsersManager;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
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
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String name=request.getParameter("name");
		String userName=request.getParameter("userName");
		String mail=request.getParameter("mail");
		String Cmail=request.getParameter("Cmail");
		String password=request.getParameter("password");
		String Cpassword=request.getParameter("Cpassword");
		String organization=request.getParameter("Organization");
		String msg="";
		String contextPath = URLparser.giveContext();
		int error = 0;
		String code, title;
		
		String copeAdmin = URLparser.giveCIUsername();
		String copeAdPass = URLparser.giveCIPass();
		
		ArrayList<String> list = new ArrayList<String>();
		if(name.equals("")){
			list.add("1");
		}
		if(userName.equals("")){
			list.add("2");
		}
		if(mail.equals("")){
			list.add("3");
		}
		if(Cmail.equals("")){
			list.add("4");
		}
		if(password.equals("")){
			list.add("5");
		}
		if(Cpassword.equals("")){
			list.add("6");
		}
		if(list.isEmpty()){
			UsersManager uMan = new UsersManager();
			ExperimentsManager eMan = new ExperimentsManager();
			String isUser = uMan.isUser(userName);
			String isMail = uMan.isMail(mail);
			Boolean found=false;
			if (!(isUser.equals("0")) || !(isMail.equals("0")))
				found=true;
			if (found){
				if (!(isUser.equals("0")))
					request.setAttribute("errorReg", "Username is already registered");
				if (!(isMail.equals("0")))
					request.setAttribute("errorReg2", "email is already registered");
	            try {
	            	getServletConfig().getServletContext().getRequestDispatcher(
	                    "/registration.jsp").forward(request,response);

	            } catch (ServletException e) {
	            	// TODO Auto-generated catch block
	            	e.printStackTrace();
	            } catch (IOException e) {
	            	// TODO Auto-generated catch block
	                e.printStackTrace();
	            }
			} else {
				code = null;
				String restCall = null;
				try {
					restCall = CopeRESTClient.copeItRegister(userName, password,
							mail).substring(1);
	    		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		CoPeItParser pars = new CoPeItParser(restCall);
	    		code = pars.getCode();
	    		msg = pars.getDescription();
	    		if (!code.equals("0"))
						error += 1;
	    		System.out.println("Resultado de registrar usuario en CoPeIt ("+
	    				code+"): "+msg);
				
				if (error==0) {
					String userID = uMan.addUser(name, userName, password, organization,
							mail, "1");
					if (!(userID.equals("0")) && !(userID.equals("-1"))&&
							!(userID.equals("-2"))&&!(userID.equals("-3"))&&
							!(userID.equals("-4"))){
						// Buscar todos los workspaces públicos del sistema
						String xml = eMan.listExperiments();
						ListExperimentsParser pars2;
						
						pars2 = new ListExperimentsParser(xml);
						int nExp = pars2.getNumberExperiments();
						int i;
						String vis, exp, group;
						ExperimentParser pars3;
						String result;
						for (i=0; i<nExp; i++) {
							vis = pars2.getVisibilityID(i);
							if (vis.equals("1")) {
								exp = pars2.getID(i);
								
								// Asignar a permisos al usuario en cada uno de estos workspaces
								// en el CoPeIt
								xml = eMan.experiment(exp);
								pars3 = new ExperimentParser(xml);
								group = pars3.getGroup();
								try {
									result = CopeRESTClient.copeItLoginComplete(copeAdmin,
											copeAdPass).substring(1);
									pars = new CoPeItParser(result);
									code = pars.getCode();
									msg = pars.getDescription();
									if (!code.equals("0"))
										error += 1;
									System.out.println("Resultado de logear al administrador ("+
											code+"): \n"+msg);
									
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								if (error==0) {
									try {
										result = CopeRESTClient.copeItGrantPrivileges(userName,
												group).substring(1);
										pars = new CoPeItParser(result);
										code = pars.getCode();
										msg = pars.getDescription();
										if (!code.equals("0"))
											error += 1;
										System.out.println("Resultado de dar privilegios a " +
												userName + " en el grupo " + group + " (" + code +
												"): " + msg);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
								if (error==0) {
									try {
										result = CopeRESTClient.copeItLogoutComplete(copeAdmin);
										result = result.substring(1);
										pars = new CoPeItParser(result);
										code = pars.getCode();
										msg = pars.getDescription();
										if (!code.equals("0"))
											error += 1;
										System.out.println("Resultado de deslogear al" +
												" administrador (" + code + "): \n" + msg);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
								// Asignar a permisos al usuario en cada uno de estos workspaces
								// en la plataforma
								result = eMan.addUserPermissionInExperiment(userID, exp, "3");
								if (!result.equals("1"))
									error += 1;
							}
							
						}
						if (error != 0) {
							title="Collaborative system registration error: (Error " +
							error + ")";
							msg = "There was an error granting the user. Please send a mail" +
									" to the Dicode Workbench administrator detailing the" +
									" operation and the problem";
							response.sendRedirect(response.encodeRedirectURL(contextPath +
									"/error.jsp?title=" + title + "&msg=" + msg + "&link=" +
									contextPath  + "/registration.jsp"));
						} else {
						
						
							String actCode = uMan.generatePass();
							String[] entry = {"activation", userName, actCode, mail};
							JavaMailSamples.main(entry);
							result = uMan.addConfirmation(userID, actCode);
							System.out.println(result);
							if (result.equals("1")) {
								msg="The request has been accepted. In a short period of time" +
										" you will receive an email confirmation";
								response.sendRedirect(response.encodeRedirectURL(contextPath +
										"/confirmation.jsp?msg="+msg+"&link="+contextPath));
							} else {
								msg="There was an error during the registration process." +
										" Please, try to register again. If the problem persists" +
										" send a mail to the Dicode Workbench administrator" +
										" detailing the operation and the problem";
								response.sendRedirect(response.encodeRedirectURL(contextPath +
										"/error.jsp?msg=" + msg + "&link=" + contextPath +
										"/registration.jsp"));
							}
						}
					} else {
						msg="There was an error ("+ userID +") with the registration" +
								" adding the new account. Please, try to register again. If" +
								" the problem persists send a mail to the Dicode Workbench" +
								" administrator detailing the operation and the problem";
						response.sendRedirect(response.encodeRedirectURL(contextPath +
								"/error.jsp?msg=" + msg + "&link=" + contextPath +
								"/registration.jsp"));
					}
				} else {
					title="Collaborative system registration error: (Error " + error +
					")";
					response.sendRedirect(response.encodeRedirectURL(contextPath +
							"/error.jsp?title=" + title + "&msg=" + msg + "&link=" +
							contextPath + "/registration.jsp"));
					
				}
			}
		}
		else{//There are empty fields
			for(int i=0;i<list.size();i++){
				String item = list.get(i).toString();
				if(item.equals("1")){
					request.setAttribute("errorFull", "Enter full name");
				}
				else if(item.equals("2")){
					request.setAttribute("errorUser", "Enter user name");
				}
				else if(item.equals("3")){
					request.setAttribute("errorMail", "Enter e-mail");
				}
				else if(item.equals("4")){
					request.setAttribute("errorCMail", "Enter e-mail");
				}
				else if(item.equals("5")){
					request.setAttribute("errorPassword", "Enter password");
				}
				else if(item.equals("6")){
					request.setAttribute("errorCPassword", "Enter password");
				}
			}
			request.setAttribute("full", name);
			request.setAttribute("userN", userName);
			request.setAttribute("mail", mail);
			request.setAttribute("Cmail", Cmail);
			request.setAttribute("password", password);
			request.setAttribute("Cpass", Cpassword);
			try {
				
				
                getServletConfig().getServletContext().getRequestDispatcher(
                  "/registration.jsp").forward(request,response);

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
