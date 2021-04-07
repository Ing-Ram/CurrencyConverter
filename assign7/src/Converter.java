

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Converter
 */
@WebServlet("/Converter")
public class Converter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Converter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(false);
		
		double rate = 1.0;
		
		if(session != null) {
			rate = (double) session.getAttribute("rate");
		}else {
			/** We will create a new Session*/
			session = request.getSession(true);
			session.setAttribute("rate",rate);
		}
		
		PrintWriter out = response.getWriter();
		out.println( "<!DOCTYPE html>\r\n" + 
                "<html>\r\n" + 
                "<head>\r\n" + 
                "	<meta charset=\"ISO-8859-1\">\r\n" + 
                "	<title>Converter</title>\r\n" + 
                "	<style>\r\n" + 
                "		table, th, td {\r\n" + 
                "			border: 1px solid black;\r\n" + 
                "		}\r\n" + 
                "		\r\n" + 
                "		table th, table td {\r\n" + 
                "			padding-left: 5px;\r\n" + 
                "			padding-right: 5px;\r\n" + 
                "		}\r\n" + 
                "	</style>\r\n" + 
                "</head>\r\n" + 
                "<body>\r\n"
                + "<form method=\"POST\" action=\"“/converter/servlet/Converter\">"				
				);
        out.println("Currency Amount: <input type=\"text\" name=\"dollar\"/><br/>");
        out.println("Exchange Rate: <input type=\"text\" name=\"rate\" value=\"" + rate + "\"/><br/><br/>");
        out.println("<input type=\"submit\" value=\"Send\">");
        out.println ("</form>");
        out.println("</body>");
		out.println ("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		double rate;
		
		if(session != null) {
			if(request.getParameter("rate") != "") {
				rate = Double.parseDouble(request.getParameter("rate"));
			}else {
				rate = (double)session.getAttribute("rate");
			}
			
		double dollar =  Double.parseDouble(request.getParameter("dollar"));
		
		session.setAttribute("rate",rate);
		
		PrintWriter out = response.getWriter();
		
		out.println(
				 "<!DOCTYPE html>\r\n" + 
		                  "<html>\r\n" + 
		                  "<head>\r\n" + 
		                  "	<meta charset=\"ISO-8859-1\">\r\n" + 
		                  "	<title>Converter</title>\r\n" + 
		                  "	<style>\r\n" + 
		                  "		table, th, td {\r\n" + 
		                  "			border: 2px solid green;\r\n" + 
		                  "		}\r\n" + 
		                  "		\r\n" + 
		                  "		table th, table td {\r\n" + 
		                  "			padding-left: 10px;\r\n" + 
		                  "			padding-right: 10px;\r\n" + 
		                  "		}\r\n" + 
		                  "	</style>\r\n" + 
		                  "</head>\r\n" + 
		                  "<body>\r\n"
		                  
				
				);
				String value = String.valueOf(rate*dollar);
		        out.println("Currency Amount: " + dollar +"<br>");
		        out.println("Exchange Rate: " + rate + "<br>");
				out.println("Conversion result: "+ value +"<br>" );
				out.println("</body>\n</html>");
		
		
		
		}
	}

}
