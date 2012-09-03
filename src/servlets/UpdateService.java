package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parsers.ServiceParser;
import parsers.URLparser;
import dicode.ServicesManager;

/**
 * Servlet implementation class ModifyService
 */
public class UpdateService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateService() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessionOk;
		String srvID;
		String srvName;
		String catID;
		String desc;
		String tagsList[];
		String URI;
		String userID;
		ServicesManager manS;
		String result;
		String msg;
		String xml;
		String shortName;
		int i;
		String tags;
		ServiceParser pars1;

		
		// Get parameters
		sessionOk = request.getSession();
		
		srvName = (String)request.getParameter("nameService");
		catID = (String)request.getParameter("category");
		desc = (String)request.getParameter("description");
		tagsList = request.getParameterValues("tags");
		shortName = (String)request.getParameter("aliasService");
		URI = request.getParameter("URIService");
		userID = (String)sessionOk.getAttribute("userID");
		srvID = (String)sessionOk.getAttribute("srvID");
		sessionOk.removeAttribute("srvID");
		
		String contextPath = URLparser.giveContext();
		manS = new ServicesManager();
		if (desc == "" || desc == null || desc == "null")
			desc=" ";
		
		// Check if the service exists
		xml = manS.service(srvID);
		pars1 = new ServiceParser(xml);
		if (pars1.getName().equals("-5")) {
			msg = "The service you are trying to modify does not exists. Please, " +
			"try it again. If the problem persists contact with Dicode Workbench " +
			" administrator";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath +
					"/services.jsp"));
			return;
		}
		
		// Check if all the parameters has been provided
		ArrayList<String> list = new ArrayList<String>();
    
    if(srvName==null || srvName.equals("")){
    	list.add("1");
    }
    if(URI==null || URI.equals("")){
			list.add("2");
    }
    if(catID==null || catID.equals("")) {
			list.add("3");
		}
    
    if (!list.isEmpty()) {
    	for(i=0;i<list.size();i++){
				String item = list.get(i).toString();
				if(item.equals("1")){
					request.setAttribute("errorSrvName",
							"The service name must be defined");
				} else if(item.equals("2")){
					request.setAttribute("errorSrvURI",
							"The service URI must be defined");
				} else if(item.equals("3")){
					request.setAttribute("errorSrvType",
							"The service type must be selected");
				}
			}
			try {
				getServletConfig().getServletContext().getRequestDispatcher(
						"/updService.jsp?srvID=" + srvID + "&srvName=" +
						srvName).forward(request, response);
			} catch (ServletException e) {
				// Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
      	// Auto-generated catch block
        e.printStackTrace();
      }
      return;
		}

		// Check if the user has permission to modify/delete the experiment
		if (!pars1.getUploaderUser().equals(userID)) {
			msg = "You have no permissions to delete this service.";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath + "/services.jsp"));
			return;
		}
		
		// Check the name of the service
		result = manS.isServiceName(srvName);
		if (!result.equals("0") && !result.equals("-5")) {
			if (!srvID.equals(result)) {
				msg = "The service '" + srvName + "' is registered yet";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/services.jsp"));
				return;
			}
		}
		
		// Check the URI of the service
		result = manS.isServiceURI(URI);
		if (!result.equals("0") && !result.equals("-5")) {
			if (!srvID.equals(result)) {
				msg = "The URI '" + URI + "' is registered yet. ERROR("+result+")";
				response.sendRedirect(response.encodeRedirectURL(contextPath +
						"/error.jsp?msg=" + msg + "&link=" + contextPath +
						"/services.jsp"));
				return;
			}
		}
		
		// Update the service
		tags = "";
		if (tagsList != null) {
			for (i = 0; i < tagsList.length; i++) {
				tags += tagsList[i];
				if (i != tagsList.length - 1)
					tags += ", ";
			}
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("The service name parameter is "+srvName);
		result = manS.updateService(srvID, srvName,shortName, desc, URI, catID, tags);
		if (!result.equals("1")) {
			msg = "The service '" + srvName + "' could not be uploaded. Please, " +
			"try again and if the problem persists, contact Dicode Workbench " +
			"administrator. ERROR(" + result + ").";
			response.sendRedirect(response.encodeRedirectURL(contextPath +
					"/error.jsp?msg=" + msg + "&link=" + contextPath +
					"/services.jsp"));
			return;
		}
		
		msg = "The service '" + srvName + "' has been uploaded.";
		response.sendRedirect(response.encodeRedirectURL(contextPath +
				"/confirmation.jsp?msg=" + msg + "&link=" + contextPath +
				"/services.jsp"));
	}

}
