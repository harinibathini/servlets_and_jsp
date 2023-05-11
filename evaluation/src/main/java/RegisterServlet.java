import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Stack;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection con = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc","root","root");
            out.println("jdbc Connection Done");

            ps = con.prepareStatement("insert into evaluation7(uname,email,password,city,phone) values(?,?,?,?,?)");
            ps.setString(1, req.getParameter("uname"));
            ps.setString(2, req.getParameter("email"));
            ps.setString(3, req.getParameter("password"));
            ps.setString(4, req.getParameter("city"));
            ps.setString(5, req.getParameter("phone"));
            ps.executeUpdate();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.html");
            out.println("<h2>Registered Successfully</h2>");
            requestDispatcher.include(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


//            ps = con.prepareStatement("insert into logintable(user,pwd) values(?,?)");
//            ps.setString(1, req.getParameter("user"));
//            ps.setString(2, req.getParameter("pwd"));
//            ps.executeUpdate();
