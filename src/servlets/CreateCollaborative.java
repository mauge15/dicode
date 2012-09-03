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
import dicode.ExperimentsManager;

/**
 * Servlet implementation class CreateCollaborative
 */
public class CreateCollaborative extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCollaborative() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionOk = request.getSession();
		ArrayList<String> params = new ArrayList<String>();
		String title = (!request.getParameter("name").equals("")) ? request.getParameter("name"): " ";
			params.add(title);
		String descr = (!request.getParameter("description").equals("0")) ? request.getParameter("description"): " ";
			params.add(descr);
		String priv = "0";
			params.add(priv);
		String modify = "1";
			params.add(modify);
		String upload = "1";
			params.add(upload);
		String uploadmaxsize = "1";
			params.add(uploadmaxsize);
		String type = (String) ((sessionOk.getAttribute("typeID") != null) ? sessionOk.getAttribute("typeID") : " ");
			params.add(type);
		String expID = (String) ((sessionOk.getAttribute("expID") != null) ? sessionOk.getAttribute("expID") : " ");
			params.add(expID);
		String user = (String) ((sessionOk.getAttribute("userName") != null) ? sessionOk.getAttribute("userName") : " ");
		String pass = (String) ((sessionOk.getAttribute("upss") != null) ? sessionOk.getAttribute("upss"): " ");
		
		String cuc = request.getParameter("cuc");
		
		String contextPath = URLparser.giveContext();
		String copeAdmin = URLparser.giveCIUsername();
		String copeAdPass = URLparser.giveCIPass();
	    
		System.out.println(params);
		response.setContentType("text/xml");
	    
	    int error = 0;
	    CoPeItParser pars;
	    String code = null;
	    String view = null;
	    String result=null;
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
			System.out.println("\n\nGRANT");
			result=null;
			try {
				result = CopeRESTClient.copeItGrantPrivileges(user, type);
				result = result.substring(1);
				pars = new CoPeItParser(result);
				code = pars.getCode();
				if (!code.equals("0")&&!code.equals("-4"))
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
			System.out.println("\n\n LOGIN COMPLETE");
			result=null;
			try {
				result = CopeRESTClient.copeItLoginComplete(user, pass);
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
		
		String CpResult = null;
		if (error==0) {
			System.out.println("\n\nCREATE");
	    	try {
	    		CpResult = CopeRESTClient.copeItCreateCW(title, descr, priv, modify, upload, uploadmaxsize, type).substring(1);
	    		result = result.substring(1);
	    		pars = new CoPeItParser(CpResult);
				view = pars.getCode();
				if (Integer.parseInt(view)<=0)
					error += 1;
				System.out.println(CpResult+"\n");
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		}
		
		if (error==0) {
			System.out.println("\n\nLOGOUT");
			result=null;
			try {
				result = CopeRESTClient.copeItLogoutComplete(user);
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
			//Call the REST service to create a new Collaborative
			ExperimentsManager man = new ExperimentsManager();
			result = man.addWorkspace(expID, view, view, view, title, descr);
			/*try {
				//out.print("<id>"+expID+"</id><code>"+code+"</code>");
			} finally {
				out.close();
			}*/
		}
		if (cuc!=null) {
			String msg="The workspace has been created";
			response.sendRedirect(response.encodeRedirectURL(contextPath + "/confirmation.jsp?msg="+msg+"&link="+contextPath+"/workspaces.jsp"));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
