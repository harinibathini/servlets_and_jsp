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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("uname");

        if (name == null) {
            resp.sendRedirect("login.html");
        } else {
            out.println("<h1 style='color:yellow'>Welcome</h1><br><br>");
            out.println("<a href='ProfileServlet'>Profile</a> ");
            out.println("<a href='LogoutServlet'>Logout</a> ");
        }
    }
}
