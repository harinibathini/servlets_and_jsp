import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    Connection con = Connectivity.Create();
    ResultSet rs;
    PreparedStatement ps,ps1;
    Statement st;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String city = req.getParameter("city");
        String phone = req.getParameter("phone");

        try {
            ps = con.prepareStatement("insert into crudregister (name,email,pass,city,phone) values(?,?,?,?,?)");
            ps1 = con.prepareStatement("insert into loginauth values(?,?)");
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,pass);
            ps.setString(4,city);
            ps.setString(5,phone);
            ps1.setString(1,email);
            ps1.setString(2,pass);
            ps.executeUpdate();
            ps1.executeUpdate();

            out.println("<div align='center'><h1 style='color:green'>Registered Sucessfully!!</h1></div>");
            out.println("<div align='center'><h1 style='color:red'><a href='login.html'>LOGIN</a></h1></div>");
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            rd.include(req,resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
