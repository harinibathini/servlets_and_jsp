import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginSuccessful")
public class loginSuccessful extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        String name=req.getParameter("uname");
        out.println("Welcome  " +name +" login Successfull !! " );

       // ServletConfig servletConfig = getServletConfig();
        out.println("<a href=index.html>Back</a>");
    }
}
