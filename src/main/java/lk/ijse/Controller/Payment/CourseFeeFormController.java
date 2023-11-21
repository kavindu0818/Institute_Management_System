package lk.ijse.Controller.Payment;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.Controller.Gmail.GmailMain;
import lk.ijse.Controller.Payment.CourseFeeDetailsFormController;
import lk.ijse.Tm.CourseDetailsTm;
import lk.ijse.dto.*;
import lk.ijse.model.Course_detailsModel;
import lk.ijse.model.Course_paymentModel;
import lk.ijse.model.SetPaymentModel;
import lk.ijse.model.StudentfullDetailsModel;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private StudentfullDetailsModel st = new StudentfullDetailsModel();
    private Course_paymentModel course_paymentModel = new Course_paymentModel();

    String num;
    public String togmail;
    public String name;
    public String amount;
    public String Gmonth;
    public String date;
    public String toStugmail;
    public String className;
    public String fromGmail ="kavindumaduranga184@gmail.com";
    private GmailMain gm = new GmailMain();

    private Course_detailsModel cd = new Course_detailsModel();


    public void initialize(){
        generateNextOrderId();
        setDateAndTime();
    }


    public void PaymentSubmitOnAction(ActionEvent actionEvent) {
        double amount = Double.parseDouble(txtAmountCourse.getText());
        String cusDfull = (String) cmbCourseDetailsID.getValue();
        String stuId = stuiDCursePayment.getText();
        System.out.println(num);

        SetPaymentDto setPaymentDto = new SetPaymentDto(num,amount, cusDfull,stuId);
        try {
            boolean isSuccess = setPaymentModel.setPaymentDetails(setPaymentDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Change Success!").show();
                sendMail(stuiDCursePayment.getText());
                clearField();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMail(String Id) throws SQLException {

        String cf = (String) cmbCourseDetailsID.getValue();
        StudentfullDetailsDto mail = st.searchCustomer(Id);
        Course_detailsDto cMail = cd.courseName(cf);

//        System.out.println(togmail);
//        System.out.println(toStugmail);

        if (mail != null){
            togmail = mail.getPerant_Gmail();
            toStugmail =mail.getStudent_gmail();
            name = mail.getName();
            //payDate = mail.
        }

        if(cMail !=null){
            className = cMail.getCusName();
        }

        amount = txtAmountCourse.getText();

       date = lblDateAndTime.getText();
        String text ="Your student has rs for " + className +" today "+amount +" was paid as Course fees on " + date;
        String text2 ="Your Payment Sucsesfull";
        String topic = "Pay Class Fees";
        gm.addGmailDEtails(fromGmail,togmail,topic,text);
        gm.addGmailDEtails(fromGmail,toStugmail,topic,text2);

    }

    private void setDateAndTime() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    lblDateAndTime.setText(LocalDateTime.now().format(formatter));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


  /*private void generateNextOrderId() {
       try {
           int orderID = cpm.generateNextOrderId();
           num = ORDER_ID_PREFIX + String.format("%0" + ORDER_ID_LENGTH + "d", orderID);
       } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }
   }*/

    private void generateNextOrderId() {
        try {
            int orderID = course_paymentModel.generateNextCourseFeeId();
            num="00"+orderID;
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
