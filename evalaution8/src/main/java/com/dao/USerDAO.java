package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.model.User;

import javax.servlet.http.Part;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 *
 * @author Ramesh Fadatare
 *
 */
public class USerDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/javajdbc?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country, img) VALUES "
            + " (?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,country,img from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =?,img =? where id = ?;";



    public USerDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.

//        Part file = req.getPart("img");
//        String imageFileName = file.getSubmittedFileName();
//        String uploadPath = "C:/Users/coditas/IdeaProjects/RegistrationForm/src/main/webapp/Images/"+imageFileName;
//
//    FileOutputStream fos = new FileOutputStream(uploadPath);
//    InputStream is = file.getInputStream();
//
//    byte[] data = new byte[is.available()];
//        is.read(data);
//        fos.write(data);
//        fos.close();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setString(4, user.getImg());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String img = rs.getString("img");
                user = new User(id, name, email, country,img);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String img = rs.getString("img");
                users.add(new User(id, name, email, country, img));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }



    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            System.out.println("updated USer:"+statement);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setString(4,user.getImg());
            statement.setInt(5, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

//    public boolean loginUser(User user) throws SQLException {
//        boolean login;
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement("select email,country,id from users");) {
//            ResultSet rs = statement.executeQuery();
//            String name="";
//            String email = req.getParameter("email");
//            String pwd = req.getParameter("pwd");
//            HttpSession session = req.getSession();
//            int count=0;
//
//
//
//
////            rs = ps.executeQuery();
////            String name="";
////
//            while (rs.next()){
//                if(email.equals(rs.getString(1)) && country.equals(rs.getString(2))){
//                    name = rs.getString(3);
//                    count++;
//                    break;
//                }
//            }
////
////            if(count==1){
////                session.setAttribute("name",name);
////                session.setAttribute("email",email);
////                resp.sendRedirect("WelcomeServlet");
////            }
////            else {
////                out.println("<h2 style='color:red'>Invalid Credentials!</h2><br>");
////                RequestDispatcher rd = req.getRequestDispatcher("login.html");
////                rd.include(req,resp);
////            }
////
////
////
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
//
//        System.out.println("Login USer:"+statement);
//            statement.setString(1, user.getEmail());
//            statement.setString(2, user.getCountry());
//            statement.setInt(3, user.getId());
//
//            login = statement.executeUpdate() > 0;
//        }
//        return login;
//    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

