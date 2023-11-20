package lk.ijse.Controller.Payment;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.Controller.Payment.CourseFeeDetailsFormController;
import lk.ijse.Tm.CourseDetailsTm;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.Course_PaymentDto;
import lk.ijse.dto.Course_detailsDto;
import lk.ijse.dto.SetPaymentDto;
import lk.ijse.model.Course_detailsModel;
import lk.ijse.model.SetPaymentModel;

import java.sql.SQLException;
import java.util.List;

public class CourseFeeFormController {
    public TextField StuNameCourse;
    public TextField courseDfullid;
    public TextField txtAmountCourse;
    public TextField CoursePayStuIDSarch;
    public TextField stuiDCursePayment;
    public TextField txtPaymentId;
    public Label lblDateAndTime;
    public ComboBox cmbCourseDetailsID;

    CourseFeeDetailsFormController courseFeeDetailsFormController;


    // Course_detailsDto cpd = new Course_detailsDto(amount, cusDfull, cusDetilList);
    Course_detailsModel cpm = new Course_detailsModel();

    private ObservableList<CourseDetailsTm> obList = null;
    private SetPaymentModel setPaymentModel = new SetPaymentModel();
    private Course_detailsModel courseDetailsModel = new Course_detailsModel();
    public static final String ORDER_ID_PREFIX = "P";
    private static final int ORDER_ID_LENGTH = 3;

    String num;

    public void initialize(){
        generateNextOrderId();
    }


    public void PaymentSubmitOnAction(ActionEvent actionEvent) {
        double amount = Double.parseDouble(txtAmountCourse.getText());
        String cusDfull = (String) cmbCourseDetailsID.getValue();
        String stuId = stuiDCursePayment.getText();

        SetPaymentDto setPaymentDto = new SetPaymentDto(num,amount, cusDfull,stuId);
        try {
            boolean isSuccess = setPaymentModel.setPaymentDetails(setPaymentDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Change Success!").show();
                clearField();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


  private void generateNextOrderId() {
       try {
           int orderID = cpm.generateNextOrderId();
           num = ORDER_ID_PREFIX + String.format("%0" + ORDER_ID_LENGTH + "d", orderID);
       } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }
   }


    public void txtCourseStuIdSearchIdOnAction(ActionEvent actionEvent) {
        String a = CoursePayStuIDSarch.getText();
        try {
            Course_detailsDto dtop = cpm.getAllValuesCd(a);
            if (dtop != null) {
                stuiDCursePayment.setText(dtop.getStuId());
                StuNameCourse.setText(dtop.getStuName());


            } else {
                new Alert(Alert.AlertType.INFORMATION, "student not found").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            List<Course_detailsDto> dtoList = courseDetailsModel.getCourseDetailsID(a);

            for (Course_detailsDto courseDetailsDto : dtoList) {
                cmbCourseDetailsID.getItems().add(courseDetailsDto.getCusDfull());
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void clearField(){
          txtAmountCourse.clear();
          StuNameCourse.clear();
          CoursePayStuIDSarch.clear();
          stuiDCursePayment.clear();
          txtPaymentId.clear();

    }
}
