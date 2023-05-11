import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Hello from Servlet");
		ServletContext servletContext = getServletContext(); //another 2 ways?

		String uname = request.getParameter("uname");
		String pass = request.getParameter("pwd");
		int count = 0;

		try {
			Class.forName(servletContext.getInitParameter("driver"));
			Connection con = DriverManager.getConnection(servletContext.getInitParameter("url"),servletContext.getInitParameter("username"),servletContext.getInitParameter("password"));
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from info");
			while (rs.next()){
				String dbname = rs.getString(1);
				String dbpass = rs.getString(2);

				if(uname.equals(dbname) && pass.equals(dbpass)){
					out.println("Welcome! "+uname);
					count++;
					break;
				}
			}


			if(count==0){
				out.println("Sorry!"+uname+" Invalid Credentials!");
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
