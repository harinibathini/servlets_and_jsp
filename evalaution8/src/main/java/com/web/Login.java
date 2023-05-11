package com.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Connection con = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajdbc","root","root");

            String email = req.getParameter("email");
            String country = req.getParameter("country");

            HttpSession session = req.getSession(true);
            int count = 0;

            st = con.createStatement();
            rs = st.executeQuery("select email,country,name from users");
            String name = "";

            while(rs.next()){
                if(email.equals(rs.getString(1)) && country.equals(rs.getString(2))){
                    name = rs.getString(3);
                    count++;
                    break;
                }
            }
            if(count == 1){
                session.setAttribute("name", name);
                session.setAttribute("email", email);
                resp.sendRedirect("UserServlet");
            }else{
                out.println("<h2 style='color:red'>INVALID CREDENTIALS!! TRY AGAIN..</h2><br>");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                requestDispatcher.include(req, resp);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
