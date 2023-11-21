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
import lk.ijse.Controller.regex.Regex;
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
        if (isCheckValue()) {
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

            if (regID.isEmpty() || stuNameFirst.isEmpty() || stuNameLast.isEmpty() || stuId.isEmpty() || registDate.isEmpty() || subject.isEmpty() || stuGmail.isEmpty() || addresss.isEmpty() || stuContact.isEmpty() ||
                    stuGrade.isEmpty() || perFistname.isEmpty() || perLastname.isEmpty() || perantContact.isEmpty() || perantId.isEmpty() ||
                    age.isEmpty() || fullName.isEmpty() || PfullName.isEmpty() || image.isBackgroundLoading()) {

                new Alert(Alert.AlertType.ERROR, "Field Not found").showAndWait();
                return;
            }

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
    }

    private boolean isCheckValue() {
        if (!(Regex.getRegistrationCodePattern().matcher(txtRegisterId.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Registration Not Valid").show();
             return false;
        }

        if (!(Regex.getNamePattern().matcher(txtFirstName.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"First Name Not Valid").show();
            return false;

        }
        if (!(Regex.getNamePattern().matcher(txtLastName.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Last Name Not Valid").show();
            return false;
        }

        if (!(Regex.getCodePattern().matcher(txtStudentId.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Student Id Not Valid").show();
            return false;

        }
        if (!(Regex.getEmailPattern().matcher(txtGmail.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Gmail Not Valid").show();
            return false;

        }

        if (!(Regex.getMobilePattern().matcher(txtContact.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Contact Number Not Valid").show();
            return false;

        }
        if (!(Regex.getGradePattern().matcher(txtGrade.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Grade Not Valid").show();
            return false;

        }
        if (!(Regex.getNamePattern().matcher(txtPerantFirstName.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Perent First Name Not Valid").show();
            return false;
        }
        if (!(Regex.getNamePattern().matcher(txtPerantLastName.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Perant Last Name Not Valid").show();
            return false;

        }
        if (!(Regex.getMobilePattern().matcher(txtPerantContact.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Perant Contact Nummber Not Valid").show();
            return false;

        }

        if (!(Regex.getEmailPattern().matcher(txtPerandtGmail.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Perant Gamil Not Valid").show();
            return false;

        }
        if (!(Regex.getOldIDPattern().matcher(txtPerantNic.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Perant Nic Not Valid").show();
            return false;

        }
        if (!(Regex.getAgePattern().matcher(txtStuAge.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING, "Student Age Not Valid").show();
            return false;
        }


        if (!(Regex.getDatePattern().matcher(txtRegistationDate.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING, "Registation Date Not Valid").show();
            return false;
        }

        return true;

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

