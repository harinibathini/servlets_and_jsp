import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Authentication")
public class Authentication extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String userName = "";
        String passWord = "";
        int count=0;
        userName = req.getParameter("uname");
        passWord = req.getParameter("pwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/javajdbc";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            while(resultSet.next()){
                String un = resultSet.getString(1);
                String up = resultSet.getString(2);
                if(userName.equals(un) && passWord.equals(up)){
                    out.println("<h1>Welcome!!! "+un+" </h1>");
                    count++;
                    break;
                }
            }
            if(count==0){
                out.println("<h1>Sorry!!! Try Again!! </h1>");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
