import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        req.getRequestDispatcher("Logout").include(req, resp);

        out.print("<html>"
        + "<head><title> Login </title>"
        + "<style>"
        + "body{font-family(arial);}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<center>"
                + "<h1> Login </h1>"
                + "<form method='post' action='CheckUser' >"
                + "<input type='text' name='username' placeholder='Username' required>"
                + "<input type='password' name='password' placeholder='Password' required>"
                + "<input type='submit' value='Go'"
                + "</form>"
                + "</center>"
                + "</body></html>"
        );
    }
}
