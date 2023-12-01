package lk.ijse.Controller.Employee;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.Controller.regex.Regex;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.SubjectDto;
import lk.ijse.dto.TutorDto;
import lk.ijse.model.SubjectModel;
import lk.ijse.model.TutorModel;

import java.sql.SQLException;
import java.util.List;

public class ToutorAddFormController {
    public JFXTextField txtTutorID;
    public JFXTextField txtTutorName;
    public JFXComboBox cmbSSubjectID;
    public JFXTextField txtUpdateTutorID;
    public JFXTextField txtUpTutorName;
    public JFXComboBox cmbUpSubjectID;
    public JFXTextField txtSearchTutorID;
    public JFXTextField txtUpdateSubID;

    private TutorModel tm = new TutorModel();

    public void initialize() throws SQLException {
        setSubId();

    }

    public void btnAddTutorOnAction(ActionEvent actionEvent) {
        if (isCheckValue()) {
            String tutID = txtTutorID.getText();
            String tutName = txtTutorName.getText();
            String subID = (String) cmbSSubjectID.getValue();

            if (tutID.isEmpty()||tutName.isEmpty()||subID.isEmpty()){
                new Alert(Alert.AlertType.ERROR, "Field Not found").showAndWait();
                return;
            }

            var td = new TutorDto(tutID, tutName, subID);

            try {
                boolean isSave = tm.saveTutor(td);

                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION, "Save Tutor").show();

                } else {
                    new Alert(Alert.AlertType.WARNING, "Tutor Not Save Try Again").show();

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean isCheckValue() {
        if (!(Regex.getToutorcodePattern().matcher(txtTutorID.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Tutor ID Not Valid").show();
            return false;
        }
        if (!(Regex.getNamePattern().matcher(txtTutorName.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Tutor Name Not Valid").show();
            return false;
        }
        if (!(Regex.getSubjectCodePattern().matcher(txtUpdateSubID.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Subject ID Not Valid").show();
            return false;
        }
        return true;
    }

    public void btnUpddateOnAction(ActionEvent actionEvent) {
        String tId = txtUpdateTutorID.getText();
        String tName = txtUpTutorName.getText();
        String sId = txtUpdateSubID.getText();

        var td = new TutorDto(tId,tName,sId);
        try{
            boolean isSave = tm.updateTutor(td);

            if (isSave){
                    new Alert(Alert.AlertType.INFORMATION,"Update Save").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Not Update Try Agin").show();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void setSubId() throws SQLException {
        var sub = new SubjectModel();

        List<SubjectDto> dtoList = sub.getSubject();

        for(SubjectDto dto : dtoList ){
            cmbSSubjectID.getItems().add(dto.getSub_id());

        }

    }

   /* public void setUpSubId() throws SQLException {
        var sub = new SubjectModel();

        List<SubjectDto> dtoList = sub.getSubject();

        for(SubjectDto dto : dtoList ){
            cmbUpSubjectID.getItems().add(dto.getSub_id());

        }

    }*/



    public void txtSearchTutorOnAction(ActionEvent actionEvent) {
        String tutId = txtSearchTutorID.getText();
        try{
            TutorDto td = tm.getTutor(tutId);

            if(td != null){
                txtUpdateTutorID.setText(td.getTut_id());
                txtUpTutorName.setText(td.getTutorName());
                txtUpdateSubID.setText(td.getSub_id());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
