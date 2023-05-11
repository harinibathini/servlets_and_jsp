import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

    @WebServlet("/Login")
    public class Welcome extends HttpServlet {
        private static final long serialVersionUID = 1L;

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            out.println("Hello from Servlet");
            ServletContext servletContext = getServletContext(); //another 2 ways?

            try {
                Class.forName(servletContext.getInitParameter("driver"));
                Connection con = DriverManager.getConnection(servletContext.getInitParameter("url"),servletContext.getInitParameter("username"),servletContext.getInitParameter("password"));
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from info");

                 out.println("<table><caption>Student Info</caption><th>ID</th><th>NAME</th><th>MARKS</th>");

                while (rs.next()){
                   out.println(("<td>"+rs.getInt(1)+"</td>")+("<td>"+rs.getString(2)+"</td>")+("<td>"+rs.getInt(3)+"</td>"));
                }
                out.println("<tr></table>");

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

