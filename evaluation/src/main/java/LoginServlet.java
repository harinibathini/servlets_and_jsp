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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String dbuser = req.getParameter("email");
        String dbpwd = req.getParameter("password");
        int count = 0;

        Connection con = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc","root","root");
            out.println("jdbc Connection Done");

            st = con.createStatement();
            rs = st.executeQuery("select email,password from evaluation7");

            while(rs.next()) {
                String uemail = rs.getString(1);
                String upwd = rs.getString(2);

                if (dbuser.equals("admin") && dbpwd.equals("123")) {
                    out.println("<h2 style='color:green'>Welcome Admin<h2>");
                    out.println("<br>");
                    count++;
                    out.println("<a href='AdminHomePage'>Admin HomePage</a>");
                    break;
                } else if (dbuser.equals(uemail) && dbpwd.equals(upwd)) {
                    //HttpSession session = req.getSession();
                    out.println("<h2 style='color:green'>Welcome Student<h2>");
                    out.println("<br>");
                    count++;
                    out.println("<a href='HomePage'>Student HomePage</a>");
                    break;
                }
            }

                if(count==0){
                    out.println("<h2 style='color:red'>Email or Password is Incorrect!! Try Again..</h2>");
                }



        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
