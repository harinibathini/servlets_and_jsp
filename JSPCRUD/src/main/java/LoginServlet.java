import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    Connection con = Connectivity.Create();
    ResultSet rs;
    PreparedStatement ps;
    Statement st;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String pass = req.getParameter("passlogin");
        String email = req.getParameter("emaillogin");

        if(email.equals("admin") && pass.equals("admin")){
            resp.sendRedirect("WelcomeAdmin");
        }

        else {

            int countstu = 0;

            try {
                ps = con.prepareStatement("select * from loginauth");
                rs = ps.executeQuery();

                while (rs.next()) {
                    String username = rs.getString(1);
                    String password = rs.getString(2);

                    if (username.equals(email) && password.equals(pass)) {
                        countstu++;
                        break;
                    }
                }


                if (countstu == 1) {

                    String name = "";

                    ps = con.prepareStatement("select name,email from crudregister where email=?");
                    ps.setString(1, email);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        name = rs.getString(1);
                    }

                    HttpSession session = req.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("name", name);
                    resp.sendRedirect("WelcomeStudentServlet");
                }
                else {
                    out.println("<div align='center'><h1>Invalid Credentials!!</h1></div>");
                    RequestDispatcher rd = req.getRequestDispatcher("login.html");
                    rd.include(req, resp);
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
