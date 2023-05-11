import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    Connection con = Connectivity.Create();
    ResultSet rs;
    PreparedStatement ps,ps1,ps2;
    Statement st;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String id = req.getParameter("id3");

        String id1 = null,name=null,email=null,pass=null,city=null,phone=null;

        try {
            ps1 = con.prepareStatement("select * from crudregister where id=?");
            ps1.setString(1,id);
            rs = ps1.executeQuery();

            while (rs.next()){
                id1=rs.getString(1);
                name = rs.getString(2);
                email = rs.getString(3);
                pass = rs.getString(4);
                city = rs.getString(5);
                phone = rs.getString(6);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            ps2 = con.prepareStatement("insert into afterdelete values(?,?,?,?,?,?)");
            ps2.setString(1,id1);
            ps2.setString(2,name);
            ps2.setString(3,email);
            ps2.setString(4,pass);
            ps2.setString(5,city);
            ps2.setString(6,phone);

            ps2.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            ps = con.prepareStatement("delete from crudregister where id=?");
            ps.setString(1, id);
            ps.execute();

            //out.println("<h1>Deleted Sucessfully!</h1>");
            RequestDispatcher rd = req.getRequestDispatcher("WelcomeAdmin");
            rd.forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}