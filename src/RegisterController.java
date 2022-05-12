import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private Button SubmittButton;

    @FXML
    private TextField emailFild;

    @FXML
    private TextField firstNameFild;

    @FXML
    private TextField lastNameFild;

    @FXML
    private TextField passwordFild;

    @FXML
    private TextField phoneNumberFild;


    DbConnetion db = new DbConnetion();
   


    @FXML
    void register(ActionEvent event) throws IOException {

        String fname = firstNameFild.getText();
        String lname = lastNameFild.getText();
        String email = emailFild.getText();
        String phone = phoneNumberFild.getText();
        String password = passwordFild.getText();

        if(fname.isBlank()==false && lname.isEmpty()== false&& email.isEmpty()== false && phone.isEmpty()== false && password.isEmpty()== false){
        db.user(fname, lname, email, phone, password);

         displayalert(AlertType.INFORMATION, "Succsess", "succsesfully registered");

        Parent tvp = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        Scene scene =  new Scene(tvp);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
   
        }
        else{
            displayalert(AlertType.WARNING, "WARNING", "Enter value");
         
        }

    }

    private void displayalert(AlertType type, String title,String message){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

