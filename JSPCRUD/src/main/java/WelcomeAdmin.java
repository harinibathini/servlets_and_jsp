import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/WelcomeAdmin")
public class WelcomeAdmin extends HttpServlet {

    Connection con = Connectivity.Create();
    ResultSet rs,rs2;
    PreparedStatement ps,ps1;
    Statement st,st2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("<h1>Welcome Admin</h1><br>");

        try {
            st = con.createStatement();
            st2 = con.createStatement();
            rs = st.executeQuery("select * from crudregister");
            rs2 = st2.executeQuery("select count(*) from crudregister");

            while(rs2.next()) {
                out.println("<h2>Total Students:" + rs2.getString(1) + "</h2>");
            }

            out.println("<table border='10px'>" +
                    "<th>ID</th>" +
                    "<th>NAME</th>" +
                    "<th>EMAIL</th>" +
                    "<th>CITY</th>" +
                    "<th>PHONE</th>");

            while (rs.next()){

                String ids = rs.getString(1);

                out.println("<tr><td>"+rs.getString(1)+"</td>");
                out.println("<td>"+rs.getString(2)+"</td>");
                out.println("<td>"+rs.getString(3)+"</td>");
                out.println("<td>"+rs.getString(5)+"</td>");
                out.println("<td>"+rs.getString(6)+"</td>");
                out.println("<td><a href='UpdateServlet?id3="+ids+"'>Update</a></td>");
                out.println("<td><a href='DeleteServlet?id3="+ids+"'>Delete</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<h1><a href='login.html'><button>Logout</button></a></h1><br>");
            out.println("<a href='Edit.html'><button>Edit</button></a>");
            out.println("<a href='Delete.html'><button>Delete</button></a>");
            out.println("<a href='index.html'><button>Add</button></a>");
            out.println("<a href='ShowDeletedServlet'><button>Show Deleted Records</button></a>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
