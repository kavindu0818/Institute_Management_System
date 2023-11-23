package lk.ijse.Controller.Shadule;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.DaySheduleDto;
import lk.ijse.model.ClassModel;
import lk.ijse.model.DaySheduleModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DaySheduleClassController {
    public TextField txtStartTime;
    public ComboBox cmbClassName;
    public DatePicker dateDatePic;
    public TextField txtEndTime;

    private DaySheduleModel ds = new DaySheduleModel();
    public void initialize() {
        setClassIDcmb();
    }

    public void btnAddSheduleOnAction(ActionEvent actionEvent) throws SQLException {
        String className = (String) cmbClassName.getValue();
        String sTime = txtStartTime.getText();
        String eTime = txtEndTime.getText();
        String date = String.valueOf(dateDatePic.getValue());

        var day = new DaySheduleDto(className,date,sTime,eTime);
        boolean isSave = ds.saveValues(day);
        if (isSave){
            new Alert(Alert.AlertType.INFORMATION,"Save shedule").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Not Save shedule").show();
        }

    }

    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                cmbClassName.getItems().add(classDto.getClass_id());
                // cmbClassName.getItems().add(classDto.getClassName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
