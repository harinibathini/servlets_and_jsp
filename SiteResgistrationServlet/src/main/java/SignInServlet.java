import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        String name = "";

        String dbemail = req.getParameter("email");
        String dbpwd = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc","root","81189149@hH");
            out.println("JDBC connection in SignInServlet done");
            out.println("<br>");


            st = con.createStatement();
            rs = st.executeQuery("select email,password from siteregister");

            while(rs.next()){
                String uemail = rs.getString(1);
                String upwd = rs.getString(2);

                if(uemail.equals(dbemail) && upwd.equals(dbpwd)){
                    out.println("<h2 style='color:green'>Login Successful<h2>");
                    out.println("<br>");
                    count++;
//                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("WelcomeServlet");
//                    out.println("Registered Successfully");
//                    requestDispatcher.forward(req,resp);
                    break;
                }
            }

            if(count==0){
                    out.println("<h2 style='color:red'>Email or Password is Incorrect!! Try Again..</h2>");
            }
//            else{
//                HttpSession session = req.getSession();
//                session.setAttribute("uname", name);
//                resp.sendRedirect("WelcomeServlet");
//            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
