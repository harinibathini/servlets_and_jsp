import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);
        if(session==null){
            resp.sendRedirect("Login");
        }
        try{
            Connection con = ConnectDB.connect();
            String fetchStudents = "select * from student";
            String countStudent = "select count(*) from student";
            String activeStudent = "select count(*) from student where status='Active'";
            PreparedStatement ps = con.prepareStatement(fetchStudents);
            ResultSet rs = ps.executeQuery();
            PreparedStatement ps1 = con.prepareStatement(countStudent);
            ResultSet rs1 = ps.executeQuery();
            rs1.next();
            PreparedStatement ps2 = con.prepareStatement(activeStudent);
            ResultSet rs2 = ps.executeQuery();
            rs2.next();
            out.print("<html>"
                    + "<head><title> Profile </title>"
            + "<style>"
                    + "td,th{padding:14px 30px}"
                    + "body{font-family:arial;}"
                    + "table{border:1px solid black;padding:20px;}"
                    + "a{text-decoration:none;border:1px solid black;padding:10px 10px;}"
                    + "a:hover{color:red;}"
                    + "</style>"
                    + "</head>"
                    + "</body>"
                    + "<center>"
                    + ">h2>Hii Admin</h2>"
                    + "<br>"
                    + "<div >Total Student <h3>"+rs1.getInt(1)+"</h3></div>"
                    + "<div >Total Active Student <h3><a href='SearchResult?Fetch=Active' >"+rs2.getInt(1)+"</a></h3></div>"
                    + "<div >Total Deactive Student <h3><a href='SearchResult?Fetch=Deactive' >"+(rs1.getInt(1)-rs2.getInt(1))+"</a></h3></div>"
                    + "<a href='OperationForm?Id=Add'>Add Student</a>"
                    + "<a href='Logout'>Logout</a></div>"
                    + "<br><br><br>"
                    + "<div ><form action='SearchResult'><input type='text' name='Fetch' placeholder='Search Student' required><input type='submit' value='Search' ></form><br></div>"
                    + "<div ><form action='GeneratePDF'><select name='status'><option>Active</option><option>Deactive</option></select><input type='submit' value='Generate Report'></form></div>"
                    + "<div ><h2>Student Details</h2>");

                    out.print("<table ><tr><th>ID</th>"
                            + "<th>Student</th>"
                            + "<th>Enrollment</th>"
                            + "<th>Date Of Birth</th>"
                            + "<th>Gender</th>"
                            + "<th>Phone</th>"
                            + "<th>Status</th>"
                            + "<th>Update</th>"
                            + "<th>Delete</th></tr>");
                    while(rs.next()){
                        out.print("<tr><td>"+rs.getInt(1)+"</td>"
                        + "<td>"+rs.getString(2)+"</td>"
                        + "<td>"+rs.getString(3)+"</td>"
                        + "<td>"+rs.getString(4)+"</td>"
                        + "<td>"+rs.getString(5)+"</td>"
                        + "<td>"+rs.getString(6)+"</td>"
                        + "<td>"+rs.getString(7)+"</td>"
                        );
                    }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
