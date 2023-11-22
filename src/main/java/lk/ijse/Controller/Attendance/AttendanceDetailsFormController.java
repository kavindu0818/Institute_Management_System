package lk.ijse.Controller.Attendance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.Tm.AttendanceDetailsViewTm;
import lk.ijse.Tm.AttendanceTm;
import lk.ijse.Tm.ClassDetailsViewTm;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.StudentAttendance;
import lk.ijse.model.ClassModel;
import lk.ijse.model.Stu_AttendanceModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AttendanceDetailsFormController {
    public TableView tblAttendanceView;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colAttendnceDate;
    public TableColumn colAttendanceTime;
    public TableColumn ColAttendanceMarkId;
    public TableView tblClassDetailsView;
    public TableColumn colClassId;
    public TableColumn colClassName;
    public DatePicker txtAttendanceDate;
    public TextField txtSerachAttendnceClass;
    public ComboBox cmbSubjectID;

    private Stu_AttendanceModel stu = new Stu_AttendanceModel();
    private ClassModel classModel = new ClassModel();

    public void initialize(){
        setClassIDcmb();
        loadAllStudent();
        setTableclass();
        studentAttendanceDetails();

    }

    public void studentAttendanceDetails(){
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("StuId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("stu_name"));
        colAttendnceDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAttendanceTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        ColAttendanceMarkId.setCellValueFactory(new PropertyValueFactory<>("amId"));
    }

    public void setTableclass(){
        colClassId.setCellValueFactory(new PropertyValueFactory<>("clssId"));
        colClassName.setCellValueFactory(new PropertyValueFactory<>("clssName"));

    }

    public void SearchClassAndDateOnAction(ActionEvent actionEvent) {
        String clId = (String) cmbSubjectID.getValue();
       // Date classDate = (Date) txtAttendanceDate.getDayCellFactory();
        LocalDate classDate = txtAttendanceDate.getValue();


        ObservableList<AttendanceDetailsViewTm> obList = FXCollections.observableArrayList();


        try {
            List<StudentAttendance> dtoList = stu.getClassStudent(clId, classDate);
            for (StudentAttendance dto : dtoList) {
                obList.add(
                        new AttendanceDetailsViewTm(
                                dto.getStu_id(),
                                dto.getName(),
                                dto.getDate(),
                                dto.getTime(),
                                dto.getFull_id()

                        )
                );


                tblAttendanceView.setItems(obList);
                tblAttendanceView.refresh();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllStudent() {
        var model = new Stu_AttendanceModel();

        ObservableList<ClassDetailsViewTm> obList = FXCollections.observableArrayList();

        try {
            List<ClassDto> dtoList = classModel.getAllClass();

            for (ClassDto dto : dtoList) {
                obList.add(
                        new ClassDetailsViewTm(
                                dto.getClass_id(),
                                dto.getClassName()

                        )
                );
            }

            tblClassDetailsView.setItems(obList);
            tblClassDetailsView.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                cmbSubjectID.getItems().add(classDto.getClass_id());

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
