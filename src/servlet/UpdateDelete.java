package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Book;
import operation.Operate;

public class UpdateDelete extends HttpServlet {

	private static final long serialVersionUID = -3278350152239056924L;
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String input=req.getParameter("UpdateDelete");
		Book book = new Book();
		Operate<Book> obj= new Operate<>();
		PrintWriter out = res.getWriter();
		if(input.substring(0,2).equals("up")) {
			int id=Integer.parseInt(input.substring(2));
			res.setContentType("text/html");
			out.println("<html><head><title>Books</title></head><body><h2>Update Book</h2><br>");
			book=obj.viewOne(id,book);
			out.println(book.toString());
			out.println("<form action = \"Update\" method = POST>\n" + 
					"		Book Id        :<input type = \"text\" name = \"id\" value=\""+id+"\" readonly><br>\n" + 
					"		Enter new Book Name      :<input type = \"text\" name = \"name\" pattern =\"[a-zA-Z ]+\" required><br>\n" + 
					"		Enter new Book's Author  :<input type = \"text\" name = \"author\" pattern =\"[a-zA-Z ]+\" required><br>\n" + 
					"		Enter new Stock Quantity :<input type = \"number\" name = \"stock\" min=1 required ><br>\n" + 
					"		Enter new Lent Quantity  :<input type = \"number\" name = \"lent\" min=1 required><br>\n" + 
					"							  <input type = \"Submit\">	  \n" + 
					"							  <input type=\"button\" value=\"Back\" onclick=\"history.back()\">\n" + 
					"	</form>");
			out.println("</body></html>");
			
		}
		else if(input.substring(0,2).equals("dl")) {
			int id=Integer.parseInt(input.substring(2));
			obj.delete(id,book);
			RequestDispatcher rd = req.getRequestDispatcher("BookManage");
			rd.forward(req,res);
		}

	}

}
