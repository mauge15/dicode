package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parsers.CoPeItParser;
import dicode.CopeRESTClient;

/**
 * Servlet implementation class UploadFile
 */
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessionOk = request.getSession();
		String user = (String) ((sessionOk.getAttribute("userName") != null) ? sessionOk.getAttribute("userName") : " ");
		String pass = (String) ((sessionOk.getAttribute("upss") != null) ? sessionOk.getAttribute("upss"): " ");
		
		String spaceId = request.getParameter("spaceId");
		String title = request.getParameter("title");
		String posx = request.getParameter("posx");
		String posy = request.getParameter("posy");
		String contentUrl = request.getParameter("contentUrl");
		
		response.setContentType("text/xml");
	    
	    int error = 0;
	    CoPeItParser pars;
	    String code = null;
	    String view = null;
	    String result=null;
	    String result2=null;
	    System.out.println("\n\nLOGIN");
	    try {
			result = CopeRESTClient.copeItLoginComplete(user, pass);
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
			System.out.println("\n\nCREATE");
	    	try {
	    		result2 = CopeRESTClient.copeItUploadFile(spaceId, title, posx, posy, contentUrl).substring(1);
	    		result = result2.substring(1);
	    		pars = new CoPeItParser(result);
				view = pars.getCode();
				if (Integer.parseInt(view)<=0)
					error += 1;
				System.out.println(result2+"\n");
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
		PrintWriter out = response.getWriter();
		try {
			out.print(result2);
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
