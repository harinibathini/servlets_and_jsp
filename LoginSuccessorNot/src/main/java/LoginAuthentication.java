import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginAuthentication")
public class LoginAuthentication extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String uid = req.getParameter("userid");
      String password = req.getParameter("pwd");
       if(uid.equals("root1") && password.equals("123456")){
          resp.sendRedirect("LoginSuccessful.html");
       }else{
           resp.sendRedirect("LoginUnsuccessful.html");
       }
    }
}
