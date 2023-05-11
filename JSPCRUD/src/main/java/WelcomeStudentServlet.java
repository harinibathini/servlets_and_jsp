import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/WelcomeStudentServlet")
public class WelcomeStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");

        out.println("<div align='center'><h1>Welcome "+name);
        out.println("<h1><a href='ProfileServlet'>Profile</a></h1>");
        out.println("<h1><a href='login.html'>Logout</a></h1></div>");
    }
}
