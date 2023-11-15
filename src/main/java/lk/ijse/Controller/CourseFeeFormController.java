package lk.ijse.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.Tm.CourseDetailsTm;
import lk.ijse.dto.Course_PaymentDto;
import lk.ijse.dto.Course_detailsDto;
import lk.ijse.dto.SetPaymentDto;
import lk.ijse.model.Course_detailsModel;
import lk.ijse.model.SetPaymentModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.Controller.CourseFeeDetailsFormController.tblCourseDetails;

public class CourseFeeFormController {
    public TextField StuNameCourse;
    public TextField courseDfullid;
    public TextField txtAmountCourse;
    public TextField CoursePayStuIDSarch;
    public TextField stuiDCursePayment;

    CourseFeeDetailsFormController courseFeeDetailsFormController;





   // Course_detailsDto cpd = new Course_detailsDto(amount, cusDfull, cusDetilList);
    Course_detailsModel cpm = new Course_detailsModel();

    private ObservableList<CourseDetailsTm> obList = null;
    private SetPaymentModel setPaymentModel =new SetPaymentModel();



    public void PaymentSubmitOnAction(ActionEvent actionEvent) {
        double amount = Double.parseDouble(txtAmountCourse.getText());
        String cusDfull = courseDfullid.getText();

       /* List<CourseDetailsTm> cusDetilList = new ArrayList<>();
        for (int i = 0; i < tblCourseDetails.getItems().size(); i++) {
            CourseDetailsTm courseDetailsTm = obList.get(i);

            cusDetilList.add(courseDetailsTm);*/

            SetPaymentDto setPaymentDto = new SetPaymentDto(amount,cusDfull);
            try {
                boolean isSuccess = setPaymentModel.setPaymentDetails(setPaymentDto);
                if (isSuccess) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



        }







    public void txtCourseStuIdSearchIdOnAction(ActionEvent actionEvent) {
        String a = CoursePayStuIDSarch.getText();
        try {
            Course_detailsDto dtop = cpm.getAllValuesCd(a);
            if (dtop != null){
                    stuiDCursePayment.setText(dtop.getStuId());
                    StuNameCourse.setText(dtop.getStuName());
                    courseDfullid.setText(dtop.getCusDfull());

            }else {
                new Alert(Alert.AlertType.INFORMATION, "student not found").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
