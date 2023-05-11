import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/DetailsServlet")
public class DetailsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Output: </h1>");
        Enumeration pNames = req.getParameterNames();
        while(pNames.hasMoreElements()){
            String name = (String) pNames.nextElement();
            out.print("<h3>"+name+" : ");
            String[] pValue = req.getParameterValues(name);
            if(pValue.length==1){
                String p = pValue[0];
                if(p.length()==0)
                    out.println("No value");
                else
                    out.println(p);
            }
            else{
                for(int i=0;i<pValue.length;i++){
                    out.println(pValue[i]);
                }
            }
        }
    }
}
