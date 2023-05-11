import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        String uname = req.getParameter("firstname");
        String upwd = req.getParameter("password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc","root","81189149@hH");
            out.println("JDBC connection in SignUpServlet done");
            out.println("<br>");

            ps = con.prepareStatement("insert into siteregister(firstname,lastname,dob,email,password,pic,address,phone) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, req.getParameter("firstname"));
            ps.setString(2, req.getParameter("lastname"));
            ps.setString(3, req.getParameter("dob"));
            ps.setString(4, req.getParameter("email"));
            ps.setString(5, req.getParameter("password"));
            ps.setString(6, req.getParameter("pic"));
            ps.setString(7, req.getParameter("address"));
            ps.setString(8, req.getParameter("phone"));

            ps.executeUpdate();
            out.println("Data Inserted");
            out.println("<br>");

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("signIn.html");
            out.println("<h1 style='color:green'>Registered Successfully</h1>");
            requestDispatcher.include(req,resp);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
