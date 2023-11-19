import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import lk.ijse.dto.ClassDto;
import lk.ijse.model.ClassModel;
import lk.ijse.model.CourseModel;

import java.sql.SQLException;

public class AddCourseFormController {
    public JFXTextField txtCourseID;
    public JFXTextField txtCourseName;
    public JFXTextField txtTutorID;
    public JFXTextField txtCourseFees;
    public JFXTextField txtxCourseTimeDuration;
    public JFXTextField txtCourseStartDay;
    public javafx.scene.control.DatePicker DatePicker;

    private CourseModel cm = new CourseModel();
    private ClassModel cdm = new ClassModel();

    public void btnCourseAddOnAction(ActionEvent actionEvent) {
        String courseID = txtCourseID.getText();
        String courseName = txtCourseName.getText();
        String tutor = txtTutorID.getText();
        String courseFeee = txtTutorID.getText();
        String courseDuration = txtxCourseTimeDuration.getText();
        String date = String.valueOf(DatePicker.getValue());

        try {
            boolean isSaved = cm.saveCourseDetails(courseID, courseName, courseFeee, courseDuration, date);


            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Add Course").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Not add Cousrse Pleace Try Agin");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        var cd = new ClassDto(courseID,tutor,courseName);

        try {
            boolean saved = cdm.saveCoursesClass(cd);
            if (saved){
                new Alert(Alert.AlertType.CONFIRMATION,"Add Class table").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Pleace Try again Not add class Table");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
