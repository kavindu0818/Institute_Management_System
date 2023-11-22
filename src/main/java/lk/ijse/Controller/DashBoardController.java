package lk.ijse.Controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashBoardController {
    public AnchorPane OneAnc;
    public AnchorPane root;
    public static AnchorPane TwoAncMain;
    public AnchorPane Twonc;
    public AnchorPane MainDashobordAncPage;
    public ImageView userImageView;
    public JFXTextArea txtuserName;

    public void initialize() throws IOException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/DashboardOriginal.fxml")));

    }

    public void RegistationOnAction(ActionEvent actionEvent) throws IOException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/RegistationMain.fxml")));
        // Stage stage = (Stage) Twonc.getScene().getWindow();
        // stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/" +"RegistationStudent"+ ".fxml"))));

    }

    public void AttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/AttendanceForm.fxml")));

    }

    public void DashboardOnAction(ActionEvent actionEvent) throws IOException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/View/DashboardOriginal.fxml")));
    }

    public void StudnetDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/StudentDetailsForm.fxml")));
    }

    public void EmployeeMainOnAction(ActionEvent actionEvent) throws IOException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/EmployeeForm.fxml")));
    }

    public void PaymentOnAction(ActionEvent actionEvent) throws IOException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/PaymentForm.fxml")));
    }

    public void btnShaduleOnAction(ActionEvent actionEvent) throws IOException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/InstituteShaduleForm.fxml")));

    }

    public void btnOnReportOnAction(ActionEvent actionEvent) throws IOException {

        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/ReportForm.fxml")));

    }

    public void btnAddUserOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/UserForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("User Manage");
        stage.centerOnScreen();
        stage.show();

    }
}


