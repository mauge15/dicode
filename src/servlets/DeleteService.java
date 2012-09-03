package servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dicode.ExperimentsManager;


/**
 * Servlet implementation class DeleteService
 */
public class DeleteService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteService() {
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
		System.out.println("Numero de experimento: "+exp);
		System.out.println("Numero de servicio: "+srv);
		
		ExperimentsManager man = new ExperimentsManager();
		//Cambiar Servicio
		String result = man.deleteUserWidgetConfiguration(usr, srv, exp);
		
		PrintWriter out = response.getWriter();
		System.out.println("Resultado de la llamada a borrar servicio: "+result);
		try 
		{
			out.print(result);
		} 
		finally 
		{
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
