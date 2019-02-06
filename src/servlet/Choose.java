package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Choose extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5667549424539264706L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int i = Integer.parseInt(req.getParameter("manage"));
		if(i==1) {
			RequestDispatcher rd = req.getRequestDispatcher("BookManage");
			rd.forward(req,res);
		} else if(i==2)
			res.sendRedirect("IssuerManagement.html");
	}

}
