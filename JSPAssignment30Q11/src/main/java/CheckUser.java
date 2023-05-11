import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/CheckUser")
public class CheckUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try{
            String uname = req.getParameter("username");
            String pwd = req.getParameter("password");
            Connection con = ConnectDB.connect();
            String q = "select * from users where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1,uname);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();

            String user,pass;
            if(rs.next()){
                user = rs.getString(1);
                pass = rs.getString(2);
                if(uname.equalsIgnoreCase(user)&&pwd.equalsIgnoreCase(pass)){
                    HttpSession session = req.getSession();
                    session.setAttribute("Username", user);
                    RequestDispatcher rd = req.getRequestDispatcher("Profile");
                    rd.forward(req, resp);

                }
            }
            else{
                out.print("<center><h3 style='color:red;'>Sorry, Username or Password does not match></h3></center> ");
                RequestDispatcher rd = req.getRequestDispatcher("Login");
                rd.include(req, resp);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
