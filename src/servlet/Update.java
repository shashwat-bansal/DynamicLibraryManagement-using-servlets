package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Book;
import operation.Operate;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Book book = new Book();
		Operate<Book> obj= new Operate<>();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		book.setId(Integer.parseInt(req.getParameter("id")));
		book.setName(req.getParameter("name"));
		book.setAuthor(req.getParameter("author"));
		book.setStock(Integer.parseInt(req.getParameter("stock")));
		book.setLent(Integer.parseInt(req.getParameter("lent")));
		book.setTimestamp(timestamp.getTime());
		try {
			obj.update(book.getId(),book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<html><head><title>Books</title></head><body><h2>Book updated in Library</h2> <br>"
				+ "<input type=\"button\" value=\"Back\" onclick=\"history.back()\">"
				+ "<form action=\"index.html\">" + 
				"    <input type=\"submit\" value=\"Home\" />\n" + 
				"</form>");
		out.println("</body></html>");
	}

}
