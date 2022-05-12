import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DbConnetion {

    private static final String URL = "jdbc:mysql://localhost:/Book";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
     private PreparedStatement insertNewUser;

    DbConnetion(){
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
             insertNewUser = connection.prepareStatement("INSERT INTO users (firstName,lastName,email,phone_Number,password) VALUES (?, ?, ?,?,?);");

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        
    }

    
    public int user(String firstName, String lastName, String email, String phoneNumber, String password){

        try {
            insertNewUser.setString(1, firstName);
            insertNewUser.setString(2, lastName);
            insertNewUser.setString(3, email);
            insertNewUser.setString(4, phoneNumber);
            insertNewUser.setString(5, password);


            return insertNewUser.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

public Connection getconnection(){
    final String URL = "jdbc:mysql://localhost:/Book";
    final String USER = "root";
    final String PASSWORD = "root";
    try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return connection;

}


  public void close() {
      try {
          connection.close();
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
  }



    }