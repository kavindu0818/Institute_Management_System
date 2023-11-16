package lk.ijse.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.*;
import lk.ijse.model.PerantModel;
import lk.ijse.model.RegistationModel;
import lk.ijse.model.StudentModel;
import lk.ijse.model.StudentfullDetailsModel;

import java.io.IOException;
import java.sql.SQLException;

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

    private RegistationModel reg = new RegistationModel();
    private StudentModel stu = new StudentModel();
    private PerantModel pr = new PerantModel();

    private StudentfullDetailsModel sfd = new StudentfullDetailsModel();

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

        var sr = new StudentfullDetailsDto(stuId, regID, fullName, registDate, stuGmail, stuContact, subject, addresss, age, stuGrade, perantId, PfullName, perantGmail, perantContact);

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


    }

