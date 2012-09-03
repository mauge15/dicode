package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dicode.ExperimentsManager;


/**
 * Servlet implementation class IncludeService
 */
public class UpdatePermissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePermissions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		// TODO Auto-generated method stub
		String exp = request.getParameter("exp");
		Enumeration<String> listas = request.getParameterNames();
		while (listas.hasMoreElements())
		{
			System.out.println("Elemento "+listas.nextElement());
		}
		String[] permissions = request.getParameterValues("permissions[]");
		System.out.println("Receiving Data "+exp);
		String[] temp;
		ExperimentsManager eMan = new ExperimentsManager();
		for (int i=0;i<permissions.length;i++)
		{
			temp = permissions[i].split(",");
			String idUser = temp[0];
			String idPermission = temp[1];
			String result="1";
			if (idPermission.equals(new String("0")))
			{
				System.out.println("Removing permission of user: "+idUser);
			 	result = eMan.updateUserPermissionInExperiment(idUser, exp, idPermission);
				System.out.println("The answer of removing permission is -"+result+"-");
			}
			else
			{
				System.out.println("Updating user: "+idUser+" idPermission "+idPermission);
				result = eMan.updateUserPermissionInExperiment(idUser, exp, idPermission);
				if (result.equals("0"))
				{
					result=eMan.addUserPermissionInExperiment(idUser, exp, idPermission);
				}
				System.out.println("The answer of saving new config is -"+result+"-");
			}
			if (result.equals(new String("1")))
			{
				System.out.println("Permission saved correctly");
			}
			else
			{
				System.out.println("Something went wrong");
			}
		}
		
		PrintWriter out = response.getWriter();
		//System.out.println(item);
		
		try {
			out.print("1");
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
