import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    Connection con = Connectivity.Create();
    ResultSet rs;
    PreparedStatement ps,ps1;
    Statement st;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String id = req.getParameter("id3");
        System.out.println(id);

        String id1="",name = "",email="",pass="",city="",phone="";


        try {

            ps = con.prepareStatement("select * from crudregister where id=?");
            ps.setString(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                id1 = rs.getString(1);
                name = rs.getString(2);
                email = rs.getString(3);
                pass = rs.getString(4);
                city = rs.getString(5);
                phone = rs.getString(6);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("<h2>Student ID:"+id+"</h2>" +
                "<form action='UpdateDB'>" +
                "NAME: <input type='text' name='nameup' value='"+name+"'>" +
                "EMAIL:<input type='text' name='emailup' value='"+email+"'>" +
                "CITY:<input type='text' name='cityup' value='"+city+"'>" +
                "PHONE:<input type='text' name='phoneup' value='"+phone+"'>" +
                "<input type='hidden' name='idup' value='"+id+"' >"+
                "<input type='submit' value='UPDATE'>" +
                "</form>");

    }
}
