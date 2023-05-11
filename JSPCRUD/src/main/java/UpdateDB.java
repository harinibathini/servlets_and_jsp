import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/UpdateDB")
public class UpdateDB extends HttpServlet {
    Connection con = Connectivity.Create();
    ResultSet rs;
    PreparedStatement ps, ps1;
    Statement st;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String name = req.getParameter("nameup");
        String id = req.getParameter("idup");
        String email = req.getParameter("emailup");
        String city = req.getParameter("cityup");
        String phone = req.getParameter("phoneup");

        try {
            ps = con.prepareStatement("update crudregister set id='" + id + "',name='" + name + "',email='" + email + "',city='" + city + "',phone='" + phone + "' where id=?");
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher rd = req.getRequestDispatcher("WelcomeAdmin");
        rd.forward(req, resp);
    }
}
