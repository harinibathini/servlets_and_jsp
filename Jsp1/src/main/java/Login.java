import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Hello from Servlet");
        resp.setContentType("Text/HTML");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");


        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            out.println("heloo2...");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc","root","root");
            Statement st = con.createStatement();
            int id;

            ResultSet rs = st.executeQuery("select id, name,email,password,phone from register");
            out.println("<table><tr><th>Name</th><th>Email</th><th>Password</th><th>Phone</th><th>Edit</th><th>Delete</th></tr>");
            while(rs.next()){
                id = rs.getInt(1);
                out.println("<tr>");
                out.println("<td>");
                out.println(rs.getString(2));
                out.println("</td><td>");
                out.println(rs.getString(3));
                out.println("</td><td>");
                out.println(rs.getString(4));
                out.println("</td><td>");
                out.println(rs.getString(5));
                out.println("</td><td>");
                out.println("<a href='Edit?id="+id+"'>Edit</a>");
                out.println("</td><td>");
                out.println("<a href='Delete?id="+id+"'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");


            }
            out.println("<table>");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
