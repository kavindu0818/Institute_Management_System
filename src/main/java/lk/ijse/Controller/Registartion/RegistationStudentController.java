package lk.ijse.Controller.Registartion;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.dto.*;
import lk.ijse.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class RegistationStudentController {
    public AnchorPane RegistationPage;
    public JFXTextField txtRegisterId;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtGrade;
    public JFXTextField txtPerandtGmail;
    public JFXTextField txtPerantFirstName;
    public JFXTextField txtPerantLastName;
    public JFXTextField txtStudentId;
    public JFXTextField txtPerantContact;
    public JFXComboBox txtSubject;
    public JFXTextField txtContact;
    public JFXTextField txtGmail;
    public JFXTextField txtParentId;
    public JFXTextField txtAddress;
    public JFXTextField txtRegistationDate;
    public JFXTextField txtPerantNic;
    public JFXTextArea txtFieldSubject;
    public JFXTextField txtStuAge;
    public Button imgSelectBtn;
    public FileInputStream fileInputStream;
    public Image image;
    public ImageView img;
    public AnchorPane addRoot1;

    private RegistationModel reg = new RegistationModel();
    private StudentModel stu = new StudentModel();
    private PerantModel pr = new PerantModel();
    private StudentfullDetailsModel studentfullDetailsModel = new StudentfullDetailsModel();

    private StudentfullDetailsModel sfd = new StudentfullDetailsModel();

    public void initialize(){
        setClassIDcmb();
    }

    public void SubmitOnAction(ActionEvent actionEvent) throws SQLException {


    }

    public void UpdateRegistationOnAction(ActionEvent actionEvent) throws IOException {
        RegistationPage.getChildren().clear();
        RegistationPage.getChildren().add(FXMLLoader.load(getClass().getResource("/View/RegistationUpdateform.fxml")));
    }

    public void RegistationDeleteOnAction(ActionEvent actionEvent) throws IOException {
        RegistationPage.getChildren().clear();
        RegistationPage.getChildren().add(FXMLLoader.load(getClass().getResource("/View/RegistationDeleteForm.fxml")));
    }

    public void OpenBrowserOnAction(ActionEvent actionEvent) {

            FileChooser chooser = new FileChooser();
            File file =chooser.showOpenDialog(imgSelectBtn.getScene().getWindow());
            try {
                fileInputStream=new FileInputStream(file);
                image=new Image(fileInputStream);
                img.setImage(image);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
   }
    }

    public void RegisstaationSaveOnAction(ActionEvent actionEvent) {
        String regID = txtRegisterId.getText();
        String stuNameFirst = txtFirstName.getText();
        String stuNameLast = txtLastName.getText();
        String stuId = txtStudentId.getText();
        String registDate = txtRegistationDate.getText();
        String subject = txtFieldSubject.getText();
        String stuGmail = txtGmail.getText();
        String addresss = txtAddress.getText();
        String stuContact = txtContact.getText();
        String stuGrade = txtGrade.getText();
        String perFistname = txtPerantFirstName.getText();
        String perLastname = txtPerantLastName.getText();
        String perantContact = txtPerantContact.getText();
        String perantId = txtParentId.getText();
        String perantGmail = txtPerandtGmail.getText();
        String perantNic = txtPerantNic.getText();
        String age = txtStuAge.getText();
        String fullName = stuNameFirst + stuNameLast;
        String PfullName = perFistname + perLastname;
        Image image = img.getImage();
        byte[] ima = studentfullDetailsModel.imagenToByte(image);

        var sr = new StudentfullDetailsDto(stuId, regID, fullName, registDate, stuGmail, stuContact, subject, addresss, age, stuGrade, perantId, PfullName, perantGmail, perantContact, ima);

        try {
            boolean isSaved = StudentfullDetailsModel.saveStudentDetails(sr);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student Save!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Agin").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void clearFields() {
        txtStudentId.setText("");
        txtRegisterId.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtFieldSubject.setText("");
        txtGmail.setText("");
        txtGrade.setText("");
        txtParentId.setText("");
        txtPerandtGmail.setText("");
        txtPerantLastName.setText("");
        txtRegistationDate.setText("");
        txtPerantContact.setText("");
        txtPerantFirstName.setText("");
        txtPerantNic.setText("");
        txtStuAge.setText("");

    }


    public void AddCourseOnAction(ActionEvent actionEvent) throws IOException {
        addRoot1.getChildren().clear();
        addRoot1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/AddStudentCourse.fxml")));

    }

    public void AddClassOnAction(ActionEvent actionEvent) throws IOException {
        addRoot1.getChildren().clear();
        addRoot1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/AddStudentClassForm.fxml")));

    }

    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                txtSubject.getItems().add(classDto.getClass_id());

            }
            txtSubject.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtFieldSubject.appendText(newValue + "\n"); // Append the selected item to the TextArea
                }
            });


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

