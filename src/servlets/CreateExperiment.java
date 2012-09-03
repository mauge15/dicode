package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dicode.CopeRESTClient;
import dicode.ExperimentsManager;
import dicode.UsersManager;

import parsers.CoPeItParser;
import parsers.ListUsersParser;
import parsers.URLparser;
import parsers.userParser;

/**
 * Servlet implementation class CreateExperiment
 */
public class CreateExperiment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateExperiment() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	
	System.out.println("Create Experiment Servlet");
	HttpSession sessionOk = request.getSession();
	String userID = (String)sessionOk.getAttribute("userID");
	String name = (String)request.getParameter("nm");
	String description = (String)request.getParameter("ds");
	description = description.equals("Insert your description here...") ? " " :description;
	String domain = (String)request.getParameter("dm");
	String visibility = (String)request.getParameter("vs");
	String tags = (String)request.getParameter("tgs");
	String users[] = null;
	String contextPath = URLparser.giveContext();
	String copeAdmin = URLparser.giveCIUsername();
	String copeAdPass = URLparser.giveCIPass();
	String msg;
	String group = null; 

	String title = "default";
	String descr = "0";
	UsersManager manU = new UsersManager();
	int i;
	ArrayList<String> list = new ArrayList<String>();
	if(name.equals(""))
	{
		list.add("1");
	}
    if(domain==null)
    {
    	list.add("2");
    }
	if (list.isEmpty()) 
	{
		int error = 0;
	    CoPeItParser parsC;
		String code = null;
		String result = null;
		System.out.println("\n\nLOGIN");
		try 
		{
			result = CopeRESTClient.copeItLoginComplete(copeAdmin, copeAdPass);
			result = result.substring(1);
			System.out.println("Resultado de login: "+result);
			parsC = new CoPeItParser(result);
			code = parsC.getCode();
			if (!code.equals("0"))
				error = 1;
			System.out.println(result);
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		String CpResult = null;
		
		if (error==0) 
		{
			System.out.println("\n\nCREATE");
		    try 
		    {
		    	CpResult = CopeRESTClient.copeItCreateUC(name).substring(1);
	
		    	result = result.substring(1);
		
		    	parsC = new CoPeItParser(CpResult);
		
				code = parsC.getCode();
		
				group = code;
				if (Integer.parseInt(code)<=0)
					error = 2;
				System.out.println(CpResult+"\n");
	
		    } 
		    catch (Exception e) 
		    {
		    	//e.printStackTrace();
		    	System.out.println("The exception is when creating workspace");
		    }
		}
		

		if (error==0) 
		{
			ExperimentsManager manE = new ExperimentsManager();
			// Create logbook
			String logBook = manE.addLogBook();
		
			if (logBook.equals("0")) 
			{
				msg = "There was an error creating the workspace (ERR001)";
					response.sendRedirect(response.encodeRedirectURL(contextPath +
							"/error.jsp?msg=" + msg + "&link=" + contextPath +
							"/newWorkspace.jsp"));
			} 
			else 
			{
				String experimentID;
				// Create experiment
				// TRATAR CON LOS TAGS Y EL DOMAIN
				experimentID = manE.addExperiment(name, description, userID,
							visibility, logBook, tags, domain);

				if (experimentID.equals("0")) 
				{
	
					msg = "There was an error creating the workspace (ERR002)";
						response.sendRedirect(response.encodeRedirectURL(contextPath +
								"/error.jsp?msg=" + msg + "&link=" + contextPath +
								"/newWorkspace.jsp"));
				} 
				else 
				{

					// Insert group number
					result = manE.addExperimentGroup(experimentID, group);
	
					// Give a workspace to the experiment
					if (result.equals("0")) 
					{
						msg = "There was an error creating the workspace (ERR003)";
							response.sendRedirect(response.encodeRedirectURL(contextPath +
									"/error.jsp?msg=" + msg + "&link=" + contextPath +
									"/newWorkspace.jsp"));
					} 
					else 
					{
						System.out.println("Adding workspace and permission to the user");
						result = manE.addUserPermissionInExperiment(userID, experimentID,"4");
						if (!(result.equals("1"))) 
						{
							msg = "There was an error creating the workspace (ERR004)";
							response.sendRedirect(response.encodeRedirectURL(contextPath +
										"/error.jsp?msg=" + msg + "&link=" + contextPath +
										"/newWorkspace.jsp"));
						} 
						else 
						{
							System.out.println("Permission granted");
							// Give permission 3 to the rest of users
							if (visibility.equals("1")) 
							{
								System.out.println("Public Experiments");
								String xml = manU.listUsers();
								ListUsersParser pars = new ListUsersParser(xml);
								int numUsers = pars.getNumberUsers();
								users = new String [numUsers];
								for (i=0; i<numUsers; i++) 
								{
									users[i]=pars.getID(i)+",3";
								}
							} 
							else 
							{
								System.out.println("Private Experiments");
								try
								{
								users = request.getParameterValues("granted[]");
								System.out.println("The granted users are "+users.length);
								}
								catch (Exception e)
								{
									System.out.println("here is some kind of error");
								}
								
							}
							
							boolean granted = true;
							System.out.println("example user 1 "+users[1]);
							if (users != null) 
							{
								System.out.println("Reading list");
								String gUser;
								for (i = 0; i < users.length; i++) 
								{
									String[] tokens = users[i].split(",");
									String id = tokens[0];
									String perm = tokens[1];
									System.out.println("Adding user "+users[i]);
									if (!id.equals(userID)) 
									{
										result = manU.user(id);
										userParser parsU = new userParser(result);
										gUser = parsU.getUserName();
										if (!perm.equals(new String("0")))
										{
											result = manE.addUserPermissionInExperiment(id, experimentID, perm);
											System.out.println("Result of permission "+result);
											if (!result.equals("1"))
											{
												granted=true;
												System.out.println("Todo bien ");
											}
											//Grant users in cope it usecase
											if (error == 0) 
											{
												System.out.println("\n\nGRANT REST OF USERS");
												result = null;
												try 
												{
													result = CopeRESTClient.copeItGrantPrivileges(gUser,group);
													result = result.substring(1);
													parsC = new CoPeItParser(result);
													code = parsC.getCode();
													if (!code.equals("0")&&!code.equals("-4"))
														error = 3;
													System.out.println(result+"\n");
												} 
												catch (Exception e1) 
												{
													e1.printStackTrace();
												}
											}
										}
									}
								}
							}
							if (!granted) 
							{
								msg = "There was an error creating the workspace (ERR005)";
								response.sendRedirect(response.encodeRedirectURL(contextPath +
											"/error.jsp?msg=" + msg + "&link=" + contextPath +
										"/newWorkspace.jsp"));
							} 
							else 
							{
								// Everything OK
								//Create default collaborative
								sessionOk.setAttribute("typeID", group);
								sessionOk.setAttribute("expID", experimentID);
								String call = "CreateCollaborative?cuc=1&name=" + title +"&description=" + descr;
								RequestDispatcher rd = request.getRequestDispatcher(call);
								rd.forward(request, response);
							}
						}
					}			
				}
			}
		}	
	} 
	else 
	{ //All data not provided
		for(i=0;i<list.size();i++)
		{
			String item = list.get(i).toString();
			if(item.equals("1"))
			{
				request.setAttribute("errName", "The workspace name must be defined");
			} 
			else if(item.equals("2"))
			{
				request.setAttribute("errDom", "An workspace domain must be selected");
			}
		}
		try 
		{
			getServletConfig().getServletContext().getRequestDispatcher(
                  "/newWorkspace.jsp").forward(request,response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
        } 
		catch (IOException e) 
		{
			e.printStackTrace();
        }
	}
}

}
