package lk.ijse.Controller.DashBoard;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Controller.LoginFormController;
import lk.ijse.dto.UserDto;
import lk.ijse.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class DashBoardController {
    public AnchorPane OneAnc;
    public AnchorPane root;
    public static AnchorPane TwoAncMain;
    public AnchorPane Twonc;
    public AnchorPane MainDashobordAncPage;
    public Label lblUserName;
    public ImageView imageView2;
    public Label lbl5;
    public AnchorPane shortAnc;
    public Label lbl10;
    public ImageView UserImage;


    @FXML
    private JFXTextArea txtuserName;
    @FXML
    private ImageView imageView;

    private String us;

    private UserModel um = new UserModel();

    public void initialize() throws IOException, SQLException {
        Twonc.getChildren().clear();
        Twonc.getChildren().add(FXMLLoader.load(getClass().getResource("/view/DashboardOriginal.fxml")));


        us = LoginFormController.getpasw();
        setPic();
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

    public void setUserDetails(String pw) throws SQLException {
        //us = pw;



    }

    public void setPic() throws SQLException {
        UserDto dto = um.getUserValueUser(us);
        if (dto != null) {

            lbl10.setText(dto.getUserName());
            Image fxImage = um.convertBytesToJavaFXImage(dto.getImage());
            UserImage.setImage(fxImage);
        }
    }








}



