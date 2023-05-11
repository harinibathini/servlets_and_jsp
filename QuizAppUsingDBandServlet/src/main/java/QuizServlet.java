import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String paramName, paramValue[];
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int count = 0;
        String ans = "";

        Enumeration paramNames = req.getParameterNames();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc","root","root");
            st = con.createStatement();
            rs = st.executeQuery("select answer from quiz");

            while(rs.next() && paramNames.hasMoreElements()){
                String un = rs.getString(1);
                paramName = (String) paramNames.nextElement();
                paramValue = req.getParameterValues(paramName);
                for(int i=0;i<paramValue.length;i++){
                    ans=paramValue[i];
                }
                if(un.equals(ans))
                    count++;
            }
            out.println("<h1>You have scored "+count+" points out of 3.</h1>");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
