import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.Class_DetailsDto;
import lk.ijse.model.ClassModel;
import lk.ijse.model.Class_DetailsModel;

import java.sql.SQLException;
import java.util.List;

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
}
