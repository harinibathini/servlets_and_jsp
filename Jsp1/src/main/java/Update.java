import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Update")
public class Update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.println("Hello from Servlet");
        resp.setContentType("Text/HTML");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String phone = req.getParameter("phone");


        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc", "root", "root");
            PreparedStatement ps = con.prepareStatement("update register set name=?, email=?, password=?,phone=? WHERE id=?");
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,pass);
            ps.setString(4,phone);
            ps.setInt(5,id);
            ps.executeUpdate();
            RequestDispatcher rd = req.getRequestDispatcher("Login");
            rd.forward(req,resp);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}