import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Edit")
public class Edit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Hello from Servlet");
        resp.setContentType("Text/HTML");
        int id = Integer.parseInt(req.getParameter("id"));
        out.println(id);
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc", "root", "root");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM register WHERE id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            req.setAttribute("id",rs.getInt(1));
            req.setAttribute("name", rs.getString(2));
            req.setAttribute("email", rs.getString(3));
            req.setAttribute("pass", rs.getString(4));
            req.setAttribute("phone", rs.getString(5));

            RequestDispatcher rd = req.getRequestDispatcher("Student.jsp");
            rd.forward(req,resp);





        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
