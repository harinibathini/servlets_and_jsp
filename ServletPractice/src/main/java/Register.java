import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String dbemail = req.getParameter("email");
        String dbpwd = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
            ps = con.prepareStatement("");
            ps.setString(1, req.getParameter("fname"));
            ps.executeUpdate();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("signin.html");
            out.println("<h2>Registered Successfully</h2>");
            requestDispatcher.include(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
