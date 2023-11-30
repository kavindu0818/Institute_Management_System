import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Tm.ClassDetailsViewTm;
import lk.ijse.Tm.CourseAttendnaceTm;
import lk.ijse.Tm.CourseDetailsViewTm;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.CourseAttendanceJoinDto;
import lk.ijse.dto.CourseDto;
import lk.ijse.model.CourseAttendanceModel;
import lk.ijse.model.CourseModel;
import lk.ijse.model.Stu_AttendanceModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CourseAttendanceDetailsFormController {
    public TableView tblCourseAttendanceDetails;
    public TableColumn colStuID;
    public TableColumn colStuName;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colAttendanceMarkID;
    public TableView tblCourseDetails;
    public TableColumn colCourseID;
    public TableColumn colCourseName;
    public ComboBox cmbCourseID;
    public DatePicker DatePiker;
    public AnchorPane AncCourseAttendance;

    private CourseAttendanceModel ca = new CourseAttendanceModel();

    public void initialize(){
        setDatacmb();
        getAllCourse();
        setViewCourseDetails();
        setViewCourseAttendance();

    }

    private void setViewCourseAttendance() {
        colStuID.setCellValueFactory(new PropertyValueFactory<>("stuID"));
        colStuName.setCellValueFactory(new PropertyValueFactory<>("stuName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colAttendanceMarkID.setCellValueFactory(new PropertyValueFactory<>("attendnceId"));

    }

    private void setViewCourseDetails() {
        colCourseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));


    }

    private void setDatacmb() {
        var cm = new CourseModel();

        try {
            List<CourseDto> dtoList = cm.getAllcourseID();

            for (CourseDto dto : dtoList) {
                cmbCourseID.getItems().add(dto.getCusId());

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getAllCourse(){
        var model = new CourseModel();

        ObservableList<CourseDetailsViewTm> obList = FXCollections.observableArrayList();

        try {
            List<CourseDto> dtoList = model.getAllcourseID();

            for (CourseDto dto : dtoList) {
                obList.add(
                        new CourseDetailsViewTm(
                                dto.getCusId(),
                                dto.getCusName()


                        )
                );
            }

            tblCourseDetails.setItems(obList);
            tblCourseDetails.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void AttendanceDetailsSearchOnAction(ActionEvent actionEvent) {
        String courseID = (String) cmbCourseID.getValue();
        String date = String.valueOf(DatePiker.getValue());

        ObservableList<CourseAttendnaceTm> obList = FXCollections.observableArrayList();

        try {
            List<CourseAttendanceJoinDto> dtoList = ca.getAllCourseAttendance(courseID,date);

            for (CourseAttendanceJoinDto dto : dtoList) {
                obList.add(
                        new CourseAttendnaceTm(
                                dto.getStuID(),
                                dto.getStuName(),
                                dto.getDate(),
                                dto.getTime(),
                                dto.getAttendnceID()

                        )
                );
            }

            tblCourseAttendanceDetails.setItems(obList);
            tblCourseAttendanceDetails.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AncCourseAttendance.getChildren().clear();
        AncCourseAttendance.getChildren().add(FXMLLoader.load(getClass().getResource("/View/AttendanceForm.fxml")));

    }
}
