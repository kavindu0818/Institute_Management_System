package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane root;
    public TextField txtUserName;
    public TextField txtPassword;

    public void logOnAction(ActionEvent actionEvent) throws IOException {

        /*String userName = txtUserName.getText();
        String passwrd = txtPassword.getText();

        String un = "kavindu";
        String pw = "kmw";

        if (un.equals(userName) && pw.equals(passwrd)) {
            loginDashbord();

        } else {
            new Alert(Alert.AlertType.WARNING, "Cheak Your Password And userName").show();

        }*/
        loginDashbord();
    }

       public void loginDashbord () throws IOException {

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/DashBoard.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Customer Manage");
            stage.centerOnScreen();
            stage.show();
        }



    }

