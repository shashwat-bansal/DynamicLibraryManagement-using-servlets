package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Book;
import operation.Operate;
public class BookManage<E> extends HttpServlet {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 6741260616500738368L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		int i = Integer.parseInt(req.getParameter("manage"));
		PrintWriter out = res.getWriter();
		
			Operate<Book> obj = new Operate<>();
			Book book =new Book();
			List <Book> list = obj.viewAll(book);	
			Iterator<Book> itr = list.iterator();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			res.setContentType("text/html");
			out.println("<html><head><title>Books</title></head><body><h2>Books available in library:</h2>"
					+ "<form action=\"CreateBook.html\">" + 
					"  <input type=\"submit\" value=\"New Book entry\" />\n" + 
					"  </form> ");
//			String str = String.format("%8s | %15s | %15s | %8s | %8s","ID","Book","Author","Stock","Lent <br>\n");
//			out.println(str);
		    out.println("<table style=\"width:75%\">");
		    out.println("<tr><th>Book Id</th><th>Book Name</th> <th>Author</th><th>Time Created</th><th>Action</th></tr>");
			while(itr.hasNext()) {
				book = itr.next();
//				str=String.format("%8d | %15s | %15s | %8d | %8d\n",book.getId(),book.getName(),book.getAuthor(),book.getStock(),book.getLent());
				out.println("<tr>\n" + 
						"    <td>"+	book.getId()+	"</td>\n" + 
						"    <td>"+	book.getName()+	"</td>\n" + 
						"    <td>"+	book.getAuthor()+	"</td>\n" + 
						"    <td>"+	sdf.format(book.getTimestamp())+	"</td>\n" +
						"    <td><form action = \"Action\" method = POST><select name=\"UpdateDelete\">\n" + 
						"    <option value=\"up"+book.getId()+"\">Update</option>\n" + 
						"    <option value=\"dl"+book.getId()+"\">Delete</option>\n" + 
						"    </select> <input type=\"submit\"></form><td></tr>");
			}
			out.println("</table></body></html>");
	}
}
