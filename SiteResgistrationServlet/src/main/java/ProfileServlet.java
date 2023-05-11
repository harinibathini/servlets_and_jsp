import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        HttpSession session = req.getSession();
        if(session.getAttribute("uname")==null){
            resp.sendRedirect("index.html");
        }
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc", "root", "81189149@hH");
                st = con.createStatement();
                rs = st.executeQuery("select * from siteregister");

                while (rs.next()) {
                    out.println("id: " + rs.getString(1) + "<br>");
                    out.println("fname: " + rs.getString(2) + "<br>");
                    out.println("lname: " + rs.getString(3) + "<br>");
                    out.println("dob: " + rs.getString(4) + "<br>");
                    out.println("email: " + rs.getString(5) + "<br>");
                    out.println("password: " + rs.getString(6) + "<br>");
                    out.println("pic: " + rs.getString(7) + "<br>");
                    out.println("address: " + rs.getString(8) + "<br>");
                    out.println("phone: " + rs.getString(9) + "<br>");
                }

                out.println("<a href='LogoutServlet'>Logout</a>");

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    }
