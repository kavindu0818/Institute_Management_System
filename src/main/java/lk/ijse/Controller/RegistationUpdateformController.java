package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.StudentfullDetailsDto;
import lk.ijse.model.StudentfullDetailsModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class RegistationUpdateformController {
    public AnchorPane RegistationUpdate;

    public TextField txtUpdateFirstName;
    public TextField txtSearchId;
    public TextField txtUpddateLastName;
    public TextField txtUpdateRegidd;
    public TextField txtUpdateStuGmail;
    public TextField txtUpdateGrade;
    public TextField txtUpdateId;
    public TextField txtUpdateContact;
    public TextField txtUpdateStuCon;
    public TextField txtUpdateRegDate;
    public TextField txtUpdateStuAge;
    public TextArea txtUpdateSubject;
    public TextField txtUpdateAddress;
    public TextField txtUpdatePerantFName;
    public TextField txtUpdatePerantLName;
    public TextField txtUpdatePerantContact;
    public TextField txtUpdatePerantId;
    public TextField txtUpdatePerantGmail;
    public TextField txtUpdateName;
    public TextField txtUpdatePerantName;
    private StudentfullDetailsModel up = new StudentfullDetailsModel();

    public void initialize() {

    }

    public void UpdateBackAction(ActionEvent actionEvent) throws IOException {
        RegistationUpdate.getChildren().clear();
        RegistationUpdate.getChildren().add(FXMLLoader.load(getClass().getResource("/View/RegistationMain.fxml")));
    }

    public void LoarUpdateDetails(ActionEvent actionEvent) {
        String id = txtSearchId.getText();


        try {
            StudentfullDetailsDto studentDto = up.searchCustomer(id);

            if (studentDto != null) {
                txtUpdateId.setText(studentDto.getStu_id());
                txtUpdateRegidd.setText(studentDto.getReg_id());
                txtUpdateName.setText(studentDto.getName());
                txtUpdateContact.setText(studentDto.getStudent_contactNo());
                txtUpdateGrade.setText(studentDto.getGrade());
                txtUpdateStuAge.setText(studentDto.getAge());
                txtUpdateRegDate.setText(studentDto.getRegDate());
                txtUpdateStuGmail.setText(studentDto.getStudent_gmail());
                txtUpdateAddress.setText(studentDto.getAddress());
                txtUpdateSubject.setText(studentDto.getSub_id());
                txtUpdatePerantName.setText(studentDto.getPerant_Name());
                txtUpdatePerantContact.setText(studentDto.getPerant_contactNo());
                txtUpdatePerantId.setText(studentDto.getPerant_id());
                txtUpdatePerantGmail.setText(studentDto.getPerant_Gmail());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void SaveUpdateOnAction(ActionEvent actionEvent) {
        String stu_id = txtUpdateId.getText();
        String reg_id = txtUpdateRegidd.getText();
        String Stuname =txtUpdateName.getText();
        String stuContact = txtUpdateContact.getText();
        String stuGrade = txtUpdateGrade.getText();
        String stuAge = txtUpdateStuAge.getText();
        String regDate = txtUpdateRegDate.getText();
        String stuGmail = txtUpdateStuGmail.getText();
        String address = txtUpdateAddress.getText();
        String sub = txtUpdateSubject.getText();
        String perantName = txtUpdatePerantName.getText();
        String perantCon = txtUpdatePerantContact.getText();
        String perantId = txtUpdatePerantId.getText();
        String perantGmail = txtUpdatePerantGmail.getText();

        var su = new StudentfullDetailsDto(stu_id,reg_id,Stuname,regDate,stuGmail,stuContact,sub,address,stuAge,stuGrade,perantId,perantName,perantGmail,perantCon);

        try {
            boolean isSaved = up.updateSave(su);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student Save!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Agin").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
