import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Controller.QrGenarator.QrGenerator;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.Class_DetailsDto;
import lk.ijse.model.ClassModel;
import lk.ijse.model.Class_DetailsModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddStudentClassFormController {
    public JFXTextField txtStudentID;
    public JFXComboBox cmbClassID;
    public JFXComboBox cmbClassName;
    public JFXTextField txtAttenAndPay;
    public JFXTextField txtStudentName;

    private Class_DetailsModel cm = new Class_DetailsModel();

    public void initialize(){
        setClassIDcmb();
    }


    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                cmbClassID.getItems().add(classDto.getClass_id());
             //   cmbClassName.getItems().add(classDto.getClassName());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void AddClassStudentOnAction(ActionEvent actionEvent) {
        String stuID = txtStudentID.getText();
        String classID = (String) cmbClassID.getValue();
        String stuName = txtStudentName.getText();
        String fullID = txtAttenAndPay.getText();

        var ad = new Class_DetailsDto(fullID,stuID,classID,stuName);

        try{
            boolean isSaved = cm.saveClassDetails(ad);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Add class Details").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Not Add Details").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void btnQrGenaratorOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/QrGenarateForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("User Manage");
        stage.centerOnScreen();
        stage.show();

    }

}

