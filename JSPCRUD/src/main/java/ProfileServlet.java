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

    Connection con = Connectivity.Create();
    ResultSet rs;
    PreparedStatement ps,ps1;
    Statement st;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        String name  = (String) session.getAttribute("name");
        String email  = (String) session.getAttribute("email");


        try {
            ps = con.prepareStatement("select * from crudregister where name=? and email=?");
            ps.setString(1,name);
            ps.setString(2,email);
            rs = ps.executeQuery();

            while (rs.next()){
                out.println("<div align='center'><h1>ID:"+rs.getString(1)+"</h1>");
                out.println("<div align='center'><h1>Name:"+rs.getString(2)+"</h1>");
                out.println("<div align='center'><h1>Email:"+rs.getString(3)+"</h1>");
                out.println("<div align='center'><h1>City:"+rs.getString(5)+"</h1>");
                out.println("<div align='center'><h1>Phone:"+rs.getString(6)+"</h1></div>");
            }

            out.println("<h1><a href='WelcomeStudentServlet'>Back</a></h1>");




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
