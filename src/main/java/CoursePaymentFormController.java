import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.CourseDto;
import lk.ijse.dto.Course_detailsDto;
import lk.ijse.model.CourseModel;
import lk.ijse.model.Course_detailsModel;

import java.sql.SQLException;
import java.util.List;

public class CoursePaymentFormController {
    public JFXTextField txtSearchStuID;
    public Label lblStuName;
    public Label lblCourseID;
    public Label lblCourse;
    public Label lblCourseDetailsID;
    public Label lblAmount;
    public Label lblStuID;
    public Button OkOnAction;
    public JFXComboBox cmbCourseID;
    public AnchorPane AncCourse3;

    private Course_detailsModel cd = new Course_detailsModel();

    public void initialize(){
        setComboBoxValue();
    }

    public void OkOnAction(ActionEvent actionEvent) {
        txtSearchStuID.clear();


    }

    public void BackOnAction(ActionEvent actionEvent) {
        AncCourse3.getChildren().clear();

    }

    public void txtOnAction(ActionEvent actionEvent) {


    }

    public void SearchOnAction(ActionEvent actionEvent) {
        String id = txtSearchStuID.getText();
        String cusID = (String) cmbCourseID.getValue();

        try {
            Course_detailsDto dto = cd.getAllDetails(id,cusID);

            if(dto != null){
                lblStuID.setText(dto.getStuId());
                lblStuName.setText(dto.getStuName());
                lblCourseID.setText(dto.getCusId());
                lblCourse.setText(dto.getCusName());
                lblCourseDetailsID.setText(dto.getCusDfull());
                lblAmount.setText(String.valueOf(dto.getPaidCusFee()));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setComboBoxValue(){
        var course = new CourseModel();
        try {
            List<CourseDto> dtoList = course.getCourseID();

            for (CourseDto dto : dtoList) {
                cmbCourseID.getItems().add(dto.getCusId());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
