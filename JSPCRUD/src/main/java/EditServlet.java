import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {

    Connection con = Connectivity.Create();
    ResultSet rs;
    PreparedStatement ps,ps1;
    Statement st;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String id = req.getParameter("id1");
        String para = req.getParameter("para");
        String value = req.getParameter("val");



        System.out.println(para);

        try {

            ps = con.prepareStatement("update crudregister set "+para+" = '"+value+"' where id = '"+id+"'");
            ps.executeUpdate();


            out.println("<h1>Updated Sucessfully!");
            out.println("<h1><a href='WelcomeAdmin'>Back</a></h1>");
            RequestDispatcher rd = req.getRequestDispatcher("Delete.html");
            rd.include(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
