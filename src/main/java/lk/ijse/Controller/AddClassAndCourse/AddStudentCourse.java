package lk.ijse.Controller.AddClassAndCourse;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Controller.regex.Regex;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.Class_DetailsDto;
import lk.ijse.dto.CourseDto;
import lk.ijse.dto.Course_detailsDto;
import lk.ijse.model.ClassModel;
import lk.ijse.model.Class_DetailsModel;
import lk.ijse.model.CourseModel;
import lk.ijse.model.Course_detailsModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddStudentCourse {
    public JFXComboBox cmbClassCID;
    public JFXTextField txtStudentCourseID;
    public JFXComboBox cmbCourseID;
    public JFXTextField txtAttendnce;
    public JFXComboBox cmbClasCIName;
    public JFXComboBox cmbClourseName1;
    public JFXTextField txtAmount;
    public JFXTextField txtStudentName;
    public JFXTextField txtCoursePayment;
    public JFXComboBox cmbCourseName;
    public JFXTextField txtStudentPaymentID;

    private Course_detailsModel courseDetailsModel = new Course_detailsModel();
    private Class_DetailsModel classDetailsModel =new Class_DetailsModel();

    public void initialize(){

         setCoursecmb();
       // generateNextOrderId();
    }

    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                cmbClassCID.getItems().add(classDto.getClass_id());
                //cmbClasCIName.getItems().add(classDto.getClassName());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void setCoursecmb() {
        var model = new CourseModel();

        try {
            List<CourseDto> dtoList = model.getAllcourse();

            for (CourseDto classDto : dtoList) {
                cmbCourseID.getItems().add(classDto.getCusId());
                cmbCourseName.getItems().add(classDto.getCusName());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void AddStudentCorse(ActionEvent actionEvent) {

        if (isCheckValue()) {
            String courseId = (String) cmbCourseID.getValue();
            String courseName = (String) cmbCourseName.getValue();
            String stuId = txtStudentCourseID.getText();
            String stuName = txtStudentName.getText();
            Double amount = Double.valueOf(txtAmount.getText());
           // String attendance = txtAttendnce.getText();
           // String classID = (String) cmbClassCID.getValue();
            // String className = (String) cmbClasCIName.getValue();
            String paymentID = txtStudentPaymentID.getText();
            try {
                boolean isSaved = courseDetailsModel.saveCourseDetails(paymentID, courseId, stuId, stuName, courseName, amount);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Add Course Details").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Not Add Course Details").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

       /* try{
            boolean save = classDetailsModel.saveValue(attendance,stuId,classID,stuName);
            if (save){
                new Alert(Alert.AlertType.INFORMATION,"Save Class Details").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Not Save Class Details").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    private boolean isCheckValue() {
        if (!(Regex.getCodePattern().matcher(txtStudentCourseID.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Student ID Not Valid").show();
            return false;
        }
        if (!(Regex.getNamePattern().matcher(txtStudentName.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Student ID Not Valid").show();
            return false;
        }
        if (!(Regex.getCourseIDAttencodePattern().matcher(txtStudentPaymentID.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Student ID Not Valid").show();
            return false;
        }
        return true;
    }

    public void btnQrGenarator(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/QrGenarateForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("User Manage");
        stage.centerOnScreen();
        stage.show();

    }
   /* private void generateNextOrderId() {
        try {
            int orderID = courseDetailsModel.generateNextOrderId();
            txtCoursePayment.setText(String.valueOf("P000" + orderID));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }*/
  /* private void generateNextOrderId() {
       try {
           int orderID = courseDetailsModel.generateNextOrderId();
           txtCoursePayment.setText(String.valueOf(orderID));
       } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }
   }*/
}
