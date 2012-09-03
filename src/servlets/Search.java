package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dicode.ServicesManager;


/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> params = new ArrayList<String>();
		String nm = (!request.getParameter("nm").equals("")) ? request.getParameter("nm"): " ";
			params.add(nm);
		String ct = (!request.getParameter("ct").equals("0")) ? request.getParameter("ct"): " ";
			params.add(ct);
		String gu = (!request.getParameter("gu").equals("0")) ? request.getParameter("gu"): " ";
			params.add(gu);
		
		System.out.println(params);
		response.setContentType("text/xml");
	    PrintWriter out = response.getWriter();
	    
		ServicesManager man = new ServicesManager();
		String result = man.searchService(params);
		System.out.println(result);
		try {
			out.print(result);
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
