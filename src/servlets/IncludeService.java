package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parsers.ServiceParser;

import dicode.ExperimentsManager;
import dicode.ServicesManager;
import dicode.UsersManager;

/**
 * Servlet implementation class IncludeService
 */
public class IncludeService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncludeService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String exp = request.getParameter("exp");
		String srv = request.getParameter("srv");
		String usr = request.getParameter("usr");
		System.out.println("Receiving Data "+exp+" "+srv+" "+usr );
		
		
		
		UsersManager uMan = new UsersManager();
		ExperimentsManager eMan = new ExperimentsManager();
		String pos = uMan.getNewWidgetConfiguration(usr, exp);
		String[] temp;
		temp = pos.split(",");
		String row = temp[0];
		String col = temp[1];
		
		System.out.println("Saving new widget in position "+pos);
		String result = eMan.saveUserWidgetConfiguration(usr, srv, exp, col, row);
		String item = "";
		System.out.println("The answer of saving new config is -"+result+"-");
		if (result.equals(new String("1")))
		{
			System.out.println("Widget configuration saved correctly");
			ServicesManager man2 = new ServicesManager();
			result = man2.service(srv);
			ServiceParser par = new ServiceParser(result);
			String id = par.getID();
			String url = par.getURI();
			String name = par.getName();
			item = ""+id+","+col+","+row+","+name+","+url;
		}
		else
		{
			item="0";
			System.out.println("Error when saving Widget Configuration");
		}
		
		
		PrintWriter out = response.getWriter();
		System.out.println(item);
		
		
		try {
			out.print(item);
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
