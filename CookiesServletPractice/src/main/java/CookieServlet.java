import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if(req.getCookies()==null){
            out.println("no cookies..so 1 cookie is created now");
            Cookie c = new Cookie("ctr", "1");
            resp.addCookie(c);
        }
        else{
            int noctr = 0;
            Cookie[] all = req.getCookies();
            for(int i=0;i<all.length;i++){
                if(all[i].getName().equals("ctr")){  //check for our cookie
                    int r = Integer.parseInt(all[i].getValue()) + 1;
                    out.print(r +" is new count");
                    //out.print(all[i].getValue() + " previous cookie value");
                    Cookie c = new Cookie("ctr", r+"");
                    resp.addCookie(c);
                    noctr = 1; // if we find our cookie
                    break;
                }
            }
            if(noctr == 0){
                Cookie c = new Cookie("ctr", "1"); //there are other ccokies but our cookie is not there,so we are creating 1 here
                resp.addCookie(c); //our cookie creation
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");
        requestDispatcher.include(req, resp);
    }
}
