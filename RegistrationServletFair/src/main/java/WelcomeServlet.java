import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        if(session.getAttribute("name")==null){
            resp.sendRedirect("login.html");
        }
        else{
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");

            out.println("<div align='center'><h1>WELCOME "+name+"!!</h1><br>");
            out.println("<h2><a href='ProfileServlet'>PROFILE</a><h2>");
            out.println("<h2><a href='LogoutServlet'>LOGOUT</a></h2></div>");
        }
    }
}
