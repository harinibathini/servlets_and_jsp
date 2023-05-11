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
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Connection con = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        HttpSession session = req.getSession();

        if(session.getAttribute("name")==null){
            resp.sendRedirect("login.html");
        }
        else{
           String name = (String) session.getAttribute("name");
           String email = (String) session.getAttribute("email");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("mysql:jdbc://localhost:3306","root","root");
                ps = con.prepareStatement("select * from registerfair where firstname=? and email=?");
                ps.setString(1, name);
                ps.setString(2, email);
                rs = ps.executeQuery();

                while(rs.next()){
                    //String f
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
