package lk.ijse.Controller.Registartion;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.StudentfullDetailsDto;
import lk.ijse.model.StudentfullDetailsModel;

import java.io.IOException;
import java.sql.SQLException;

public class RegistationDeleteFormController {
    public AnchorPane RegistationDelete;
    public AnchorPane RegistationDelete2;
    public TextField txtDSname;
    public TextField txtxRSid;
    public TextField txtDSGmail;
    public TextField txtDSGrade;
    public TextField txtDRid;
    public TextField txtDSContact;
    public TextField txtDSAge;
    public TextField txtDRdate;
    public TextArea txtDSubject;
    public TextField txtDAddress;
    public TextField txtDPName;
    public TextField txtDPContact;
    public TextField txtDPid;
    public TextField txtDPGmail;
    public TextField txtDeleteSerachStuId;
    public ImageView ImageViewD;

    private StudentfullDetailsModel sdModel = new StudentfullDetailsModel();

    public void DeleteBackAction(ActionEvent actionEvent) throws IOException {
        RegistationDelete.getChildren().clear();
        RegistationDelete.getChildren().add(FXMLLoader.load(getClass().getResource("/View/RegistationMain.fxml")));
    }

    public void DeleteSearchOnAction(ActionEvent actionEvent) {
        LoarUpdateDetails();
    }

    public void RegistationDeleteOnAction(ActionEvent actionEvent) {
        String id = txtDeleteSerachStuId.getText();


        try {
            boolean isDeleted = sdModel.deleteStudent(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Student not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    public void LoarUpdateDetails() {
        String id = txtDeleteSerachStuId.getText();


        try {
            StudentfullDetailsDto studentDto = sdModel.searchCustomer(id);

            if (studentDto != null) {
                txtxRSid.setText(studentDto.getStu_id());
                txtDRid.setText(studentDto.getReg_id());
                txtDSname.setText(studentDto.getName());
                txtDSContact.setText(studentDto.getStudent_contactNo());
                txtDSGrade.setText(studentDto.getGrade());
                txtDSAge.setText(studentDto.getAge());
                txtDRdate.setText(studentDto.getRegDate());
                txtDSGmail.setText(studentDto.getStudent_gmail());
                txtDAddress.setText(studentDto.getAddress());
                txtDSubject.setText(studentDto.getSub_id());
                txtDPName.setText(studentDto.getPerant_Name());
                txtDPContact.setText(studentDto.getPerant_contactNo());
                txtDPGmail.setText(studentDto.getPerant_Gmail());
                Image fxImage = sdModel.convertBytesToJavaFXImage(studentDto.getImage());
                ImageViewD.setImage(fxImage);
               // ImageViewD.setImage(studentDto.getImage());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Studnet not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
