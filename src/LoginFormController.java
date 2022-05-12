import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginFormController {

    @FXML
    private TextField emailFild;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordFild;

    @FXML
    private Button registerButton;

   

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {

  if(emailFild.getText().isBlank() == false && passwordFild.getText().isBlank() == false){
            
    DbConnetion con = new DbConnetion();
    Connection connectDb = con.getconnection();
    String Varifylogin = "SELECT count(1) FROM users WHERE email = '"+emailFild.getText()+"' AND password = '"+passwordFild.getText()+"';";
   
    try {
        Statement  statement = connectDb.createStatement();
        ResultSet queryResult = statement.executeQuery(Varifylogin);
        while(queryResult.next()){
            if(queryResult.getInt(1) ==1){
                displayalert(AlertType.INFORMATION,"login","login sucscess");
 
                Parent tvp = FXMLLoader.load(getClass().getResource("wellcome.fxml"));
                Scene scene =  new Scene(tvp);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            }else{
                 displayalert(AlertType.WARNING,"login","incorrect entery");
            }
        }
   
   }catch (SQLException e) {
    e.printStackTrace();
  }
}else{
    displayalert(AlertType.WARNING,"WARNING","provide email and password");

  }

  }

    private void displayalert(AlertType type, String title,String message){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void reg1(ActionEvent event) throws IOException {
       
        Parent tvp = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene =  new Scene(tvp);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
   
       
    }
}
