import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Connection con = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

//        Part file = req.getPart("image");
//        String imageFileName = file.getSubmittedFileName();
//        String uploadPath = ;
//        FileOutputStream fos = new FileOutputStream(uploadPath);
//        InputStream is = file.getInputStream();
//        byte[] data = new byte[is.available()];
//        is.read(data);
//        fos.write(data);
//        fos.close();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc","root","root");
            out.println("jdbc connection done");

            ps = con.prepareStatement("insert into registerfair(firstname,lastname,dob,email,password,image,address,phone) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, req.getParameter("firstname"));
            ps.setString(2, req.getParameter("lastname"));
            ps.setString(3, req.getParameter("dob"));
            ps.setString(4, req.getParameter("email"));
            ps.setString(5, req.getParameter("password"));
            ps.setString(6, req.getParameter("image"));
            ps.setString(7, req.getParameter("address"));
            ps.setString(8, req.getParameter("phone"));

            ps.executeUpdate();

            out.println("<div align='center'><h2 style='color:green'>REGISTERED SUCCESSFULLY!!!</h2><br>");
            out.println("<h2><a href=>LOGIN</a></h2><br></div>");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.html");
            requestDispatcher.include(req, resp);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
