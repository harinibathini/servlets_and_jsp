import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javajsp","root","root");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            //throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
