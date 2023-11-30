import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import lk.ijse.dto.UserDto;
import lk.ijse.model.StudentfullDetailsModel;
import lk.ijse.model.UserModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public Button imageSelectbtn;
    public FileInputStream fileInputStream;
    public Image image;
    public ImageView imageView;
    public Label lbl1;
    public JFXTextField txtUpName;
    public JFXTextField txtUpuPass;
    public ImageView updateUserImage;
    public Button imageSelectbtn1;
    public JFXTextField txtSearchUser;

    private StudentfullDetailsModel studentfullDetailsModel = new StudentfullDetailsModel();
    private UserModel um = new UserModel();

    public void initialize(){
        setDate();
    }

    public void btnOpenOnAction(ActionEvent actionEvent) throws SQLException {

        String password = txtPassword.getText();
        String userName = txtUserName.getText();
        Image image = imageView.getImage();
        byte[] ima = studentfullDetailsModel.imagenToByte(image);

        var ud = new UserDto(password, userName, ima);

        boolean isSave = um.setUserDetails(ud);

        if (isSave) {
            new Alert(Alert.AlertType.CONFIRMATION, "User Add").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "User not add").show();
        }


    }


    public void btnOpenBrowserOnAction(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(imageSelectbtn.getScene().getWindow());
        try {
            fileInputStream = new FileInputStream(file);
            image = new Image(fileInputStream);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnAddUseronAction(ActionEvent actionEvent) throws SQLException {
        String password = txtPassword.getText();
        String userName = txtUserName.getText();
        Image image = imageView.getImage();
        byte[] ima = studentfullDetailsModel.imagenToByte(image);

        var ud = new UserDto(password, userName, ima);

        boolean isSave = um.setUserDetails(ud);

        if (isSave) {
            new Alert(Alert.AlertType.CONFIRMATION, "User Add").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "User not add").show();
        }

    }

    public void setDate() {


    Timeline time = new Timeline(
            new KeyFrame(Duration.ZERO, e -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                lbl1.setText(LocalDateTime.now().format(formatter));
            }),
            new KeyFrame(Duration.seconds(1))
    );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
}

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        String name = txtUpName.getText();
        String pas = txtUpuPass.getText();
        Image image = updateUserImage.getImage();
        byte[] ima = studentfullDetailsModel.imagenToByte(image);

        var up = new UserDto(pas,name,ima);

        System.out.println(up.getPassword() + up.getUserName());
        try {
             boolean isSave = um.updateUser(up);
             if (isSave){
                new Alert(Alert.AlertType.INFORMATION,"Update Save").show();

             }else{
                    new Alert(Alert.AlertType.WARNING,"Update Not Save").show();
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnOpenUpdate(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(imageSelectbtn.getScene().getWindow());
        try {
            fileInputStream = new FileInputStream(file);
            image = new Image(fileInputStream);
            updateUserImage.setImage(image);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtButtonUpdateSSerachOnActon(ActionEvent actionEvent) throws SQLException {
        String us = txtSearchUser.getText();

        UserDto dto = um.getUserValue(us);
        if (dto != null) {
            txtUpName.setText(dto.getPassword());
            txtUpuPass.setText(dto.getUserName());
            Image fxImage = um.convertBytesToJavaFXImage(dto.getImage());
            updateUserImage.setImage(fxImage);
        }



    }
}
