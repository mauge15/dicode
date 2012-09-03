package servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parsers.URLparser;
//import parsers.userParser;

import dicode.UsersManager;
import parsers.UserStateParser;
import dicode.CopeRESTClient;
/**
 * Servlet implementation class Login
 */


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
	    String userName=request.getParameter("UName");
		String password=request.getParameter("Lpassword");
		String msg;
		String title;
		
		if (userName.equals(""))
		{
			request.setAttribute("errorName", "Enter user name");
			request.setAttribute("pass", password);
			try 
			{
                getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
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
		else if (password.equals(""))
		{
			request.setAttribute("errorPass", "Enter password");
			request.setAttribute("user", userName);
			try 
			{
                getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
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
		else
		{
			UsersManager man = new UsersManager();
			String result = man.validateUser(userName, password);
			if ((result.equals("-1"))||(result.equals("-2"))) 
			{
				//Go to confirmation page
				request.setAttribute("error", "Unknown error");
	            try 
	            {
	            	getServletConfig().getServletContext().getRequestDispatcher( "/index.jsp").forward(request,response);
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
			else if (result.equals("0")) 
			{
	            request.setAttribute("error", "Invalid user or password");
	            request.setAttribute("userInv", userName);
	            try 
	            {
	            	getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
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
			else 
			{
				String userID = man.isUser(userName);
	    		result = man.userState(userID);
	    		UserStateParser parser = new UserStateParser(result);
	    		System.out.println("Resultado de login: "+result);
	    		
	    		String state = parser.getID(); //Debo coger el estado de un usuario
	    		System.out.println("Estado del usuario: "+state);
	    		if (state.equals("1")) 
	    		{
	    			msg = "This account has not been activated. Please, finish the activation process ";
	    			title = "Account pendant of activation";
	    			response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&title="+title+"&link="+contextPath));

	    		} 
	    		else if (state.equals("2"))
	    		{
	    			System.out.println("State of the user is "+state);
	    			String service = null;
	    			try 
	    			{
	    				service = CopeRESTClient.copeItLogin(userName,password);		
	    				System.out.println("Result of cope it login is "+service);
	    			} 
	    			catch (Exception e) 
	    			{
						e.printStackTrace();
					} 
	    			finally 
	    			{	 
						HttpSession sessionOk = request.getSession();
						sessionOk.setAttribute("userName", userName);
						sessionOk.setAttribute("userID", userID);
						sessionOk.setAttribute("service", service);
						sessionOk.setAttribute("upss", password);
						response.sendRedirect(response.encodeRedirectURL(contextPath + "/welcome.jsp"));
					}
	    		} 
	    		else 
	    		{
	    			msg = "There is a problem with this account. Plase, contact the Dicode Workbench administrator ";
	    			response.sendRedirect(response.encodeRedirectURL(contextPath + "/error.jsp?msg="+msg+"&link="+contextPath));
	    		}
	        }
		} 
	}	
}
