package lk.ijse.Controller.Payment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Tm.ClassDetailsViewTm;
import lk.ijse.Tm.ClassFeesDetailsTm;
import lk.ijse.Tm.CourseDetailsTm;
import lk.ijse.Tm.CoursePaymentDetailsTm;
import lk.ijse.dto.*;
import lk.ijse.model.ClassModel;
import lk.ijse.model.Class_PaymentModel;
import lk.ijse.model.CourseModel;
import lk.ijse.model.Course_paymentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentFormController {
    public AnchorPane PaymentAnc1;
    public AnchorPane PaymentAnc2;
    public TableView tblCoursePaymentDetails;
    public TableColumn colStudentID;
    public TableColumn colStudentName;
    public TableColumn colSudentDate;
    public TableColumn colCourseAmount;
    public ComboBox cmbCourseID;
    public ComboBox cmbMonthCouurse;
    public DatePicker DatePickerCourse;
    public TableView tblClassDetails;
    public TableColumn colstuIDClass;
    public TableColumn colStuNameClass;
    public TableColumn colDateeClass;
    public TableColumn colAmountClass;
    public ComboBox cmbClassD;
    public ComboBox cmbMonthClass;

    private Course_paymentModel cp = new Course_paymentModel();
    private Class_PaymentModel cpm = new Class_PaymentModel();

    public void initialize(){
        setData();
        setcourseID();
        setPaymentTable();
        setClassIDcmb();
        setPaymentClass();
    }

    public void setPaymentTable(){

        colStudentID.setCellValueFactory(new PropertyValueFactory<>("stuID"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("stuName"));
        colSudentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCourseAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    public void setPaymentClass(){
        colstuIDClass.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStuNameClass.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colDateeClass.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colAmountClass.setCellValueFactory(new PropertyValueFactory<>("amount"));


    }
    public void ClassFeesOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc1.getChildren().clear();
        PaymentAnc1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/ClassFeeForm.fxml")));

    }

    public void CourseFeeOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc1.getChildren().clear();
        PaymentAnc1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/CourseFeeForm.fxml")));

    }

    public void ClassFeeDetailsOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc2.getChildren().clear();
        PaymentAnc2.getChildren().add(FXMLLoader.load(getClass().getResource("/View/ClassFeeDetailsForm.fxml")));

    }

    public void CourseFeeDetailsOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc2.getChildren().clear();
        PaymentAnc2.getChildren().add(FXMLLoader.load(getClass().getResource("/View/CourseFeeDetailsForm.fxml")));

    }

    public void CourseFeesOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc1.getChildren().clear();
        PaymentAnc1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/CourseFeeForm.fxml")));


    }

    public void btnSearchCourseDetailsOnAction(ActionEvent actionEvent) {
        String cusID = (String) cmbCourseID.getValue();
        String date = String.valueOf(DatePickerCourse.getValue());

        ObservableList<CoursePaymentDetailsTm> obList = FXCollections.observableArrayList();

        try {
            List<CoursePaymentJoinDto> dtoList = cp.getAllPayment(cusID,date);

            for (CoursePaymentJoinDto dto : dtoList) {
                obList.add(
                        new CoursePaymentDetailsTm(
                                dto.getStuID(),
                                dto.getStuName(),
                                dto.getDate(),
                                dto.getAmount()

                        )
                );
            }

            tblCoursePaymentDetails.setItems(obList);
            tblCoursePaymentDetails.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }






    }

   public void setData() {

        ArrayList<String> list = new ArrayList<>();
        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("Augest");
        list.add("September");
        list.add("Octomber");
        list.add("November");
        list.add("December");
        ObservableList<String> dataSet = FXCollections.observableArrayList(list);
        cmbMonthClass.setItems(dataSet);
    }

    public void setcourseID(){
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

    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                cmbClassD.getItems().add(classDto.getClass_id());
               // cmbClassName.getItems().add(classDto.getClassName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchClassOnAction(ActionEvent actionEvent) {
        String clssID = (String) cmbClassD.getValue();
        String month = (String) cmbMonthClass.getValue();

        ObservableList<ClassFeesDetailsTm> obList = FXCollections.observableArrayList();

        try {
            List<Class_paymentDto> dtoList = cpm.getAllClassPayment(clssID,month);

            for (Class_paymentDto dto : dtoList) {
                obList.add(
                        new ClassFeesDetailsTm(
                                dto.getStu_Id(),
                                dto.getName(),
                                dto.getDate(),
                                dto.getAmount()

                        )
                );
            }

            tblClassDetails.setItems(obList);
            tblClassDetails.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DetailsOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/CoursePaymentForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Course Payment Details");
        stage.centerOnScreen();
        stage.show();

    }
}
