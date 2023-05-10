import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Calculate")
public class Calculate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int first,second;
        String str = "";
        first = Integer.parseInt(req.getParameter("fno"));
        second = Integer.parseInt(req.getParameter("sno"));
        str = req.getParameter("operation");
        if(str.equals("add"))
            out.println("<h1>Result of addition is: "+(first+second)+"</h1>");
        else if(str.equals("sub"))
            out.println("<h1>Result of subtraction is: "+(first-second)+"</h1>");
        else if(str.equals("mul"))
            out.println("<h1>Result of multiplication is: "+(first*second)+"</h1>");
        else if(str.equals("div"))
            out.println("<h1>Result of division is: "+(first/second)+"</h1>");
        else if(str.equals("mod"))
            out.println("<h1>Result of modulus is: "+(first%second)+"</h1>");
        else
            out.println("<h1>You can select from above operations only");
    }
}
