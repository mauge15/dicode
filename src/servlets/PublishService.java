package servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import parsers.URLparser;
import dicode.ServicesManager;
//import dicode.UsersManager;

/**
 * Servlet implementation class PublishService
 */
public class PublishService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionOk = request.getSession();
		String userID = (String)sessionOk.getAttribute("userID");
		String name = (String)request.getParameter("nameService");
		String alias = (String)request.getParameter("aliasService");
		String description = (String)request.getParameter("description");
		description = description.equals("Insert your description here...") ? "" : description;
		String uRI = (String)request.getParameter("URIService");
		String categoryID = (String)request.getParameter("category");
		String tagsList[]= request.getParameterValues("tags");
		String nameB = URLEncoder.encode(name,"UTF-8");
		String contextPath = URLparser.giveContext();
	    String msg;
	    ArrayList<String> list = new ArrayList<String>();
	    
	   
	    
	    if(name.equals(""))
	    {
			list.add("1");
		}
		if(uRI.equals(""))
		{
			list.add("2");
		}
		if (alias.length()>15)
		{
			list.add("4");
		}
		String resultName;
		String resultUri;
		if (list.isEmpty()) 
		{
			ServicesManager man = new ServicesManager();
			resultName = man.isServiceName(nameB);
			resultUri = man.isServiceURI(uRI);
			System.out.println("El resultado de isServiceName es: "+resultName);
			System.out.println("El resultado de isServiceURI es: "+resultUri);
			if (resultName.equals("0") && resultUri.equals("0")) 
			{
				int i;
				String tags = "";
				if (tagsList != null) 
				{
					for (i = 0; i < tagsList.length; i++) 
					{
						tags += tagsList[i];
						if (i != tagsList.length - 1)
							tags += ", ";
					}
				}
				System.out.println("Los tags son: " + tags);
				String serviceID = man.addService(userID, name, alias,description, uRI, "1", "2", categoryID, tags);
				System.out.println("Resultado de añadir el servicio: -begin--"+serviceID+"----end");
				if (serviceID.equals("0")) 
				{
					//Data Base error
					msg = "There was an error in the data base publishing service";
					response.sendRedirect(response.encodeRedirectURL(contextPath +
							"/error.jsp?msg=" + msg + "&link=" + contextPath +
							"/newService.jsp"));					
				} 
				else if (serviceID.equals("-2"))
				{
					//Internal error
					msg = "There was an internal error publishing service";
					response.sendRedirect(response.encodeRedirectURL(contextPath +
							"/error.jsp?msg=" + msg + "&link=" + contextPath +
							"/newService.jsp"));
				} 
				else if (serviceID.equals("-3"))
				{
					//Connecting with data base error
					msg = "There was an error connecting with the data base";
					response.sendRedirect(response.encodeRedirectURL(contextPath +
							"/error.jsp?msg=" + msg + "&link=" + contextPath +
							"/newService.jsp"));
				} 
				else if (serviceID.equals("-4"))
				{
					//Service already exists
					msg = "The service " + name + " provided in the URI: '" + uRI +
						  "' is registered yet";
					response.sendRedirect(response.encodeRedirectURL(contextPath +
							"/error.jsp?msg=" + msg + "&link=" + contextPath +
							"/newService.jsp"));
				} 
				else 
				{
					//Service Published
					msg="The service has been published";
					response.sendRedirect(response.encodeRedirectURL(contextPath +
							"/confirmation.jsp?msg=" + msg + "&link=" + contextPath +
							"/services.jsp"));
				}
				/*msg = "The URI: "+uRI+" is already registered in the system.";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/newService.jsp"));*/
			} 
			else if (resultName.equals("-2") || resultUri.equals("-2"))
			{
				// Error SQL
				msg = "There was an internal error publishing service";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/newService.jsp"));
			} 
			else if (resultName.equals("-3") || resultUri.equals("-3"))
			{
				//Error Syntax
				msg = "There was an internal error publishing service";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/newService.jsp"));
			} 
			else if (resultName.equals("-4") || resultUri.equals("-4"))
			{
				//Error SQL Connection
				msg = "There was an error connecting with the data base";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/newService.jsp"));
			} 
			else if (resultName.equals("0") && !resultUri.equals("0"))
			{
				//Name already registered.
				msg = "URI already exists";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/newService.jsp"));
			} 
			else if (!resultName.equals("0") && resultUri.equals("0"))
			{
				//URI already registered.
				msg = "Name already exists";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/newService.jsp"));
			}
		} 
		else 
		{
			for(int i=0;i<list.size();i++)
			{
				String item = list.get(i).toString();
				if(item.equals("1"))
				{
					request.setAttribute("errorSrvName","The service name must be defined");
				} 
				else if(item.equals("2"))
				{
					request.setAttribute("errorSrvURI","The service URI must be defined");
				} else if(item.equals("3"))
				{
					request.setAttribute("errorSrvType","The service type must be selected");
				}
				else if (item.equals("4"))
				{
					request.setAttribute("errorAlias", "The service Alias cannot have more than 15 characters");
				}
			}
			try 
			{
                getServletConfig().getServletContext().getRequestDispatcher("/newService.jsp").forward(request,response);
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
