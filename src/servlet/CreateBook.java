package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Book;
import operation.Operate;

public class CreateBook extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3288752178262936309L;
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Book book = new Book();
		Operate<Book> obj= new Operate<>();
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    System.out.println(timestamp.toString());
		try {
			if(!obj.IsIdExist(Integer.parseInt(req.getParameter("id")),book.getClass().getSimpleName())) {
				book.setId(Integer.parseInt(req.getParameter("id")));
				book.setName(req.getParameter("name"));
				book.setAuthor(req.getParameter("author"));
				book.setStock(Integer.parseInt(req.getParameter("stock")));
				book.setLent(Integer.parseInt(req.getParameter("lent")));
				book.setTimestamp(timestamp.getTime());
				obj.create(book.getId(),book);
				out.println("<html><head><title>Books</title></head><body><h2>Book added in Library</h2> <br>"
						+ "<input type=\"button\" value=\"Back\" onclick=\"history.back()\">"
						+ "<form action=\"index.html\">" + 
						"    <input type=\"submit\" value=\"Home\" />\n" + 
						"</form>");
				out.println("</body></html>");
			}
			else {
				out.println("<html><head><title>Books</title></head><body><h2>A book with same Id exists</h2><br> "
						+ "<input type=\"button\" value=\"Back\" onclick=\"history.back()\">"
						+ "<form action=\"index.html\">" + 
						"    <input type=\"submit\" value=\"Home\" />\n" + 
						"</form>");
				out.println("</body></html>");
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
