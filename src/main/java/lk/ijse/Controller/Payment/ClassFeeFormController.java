package lk.ijse.Controller.Payment;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import lk.ijse.Controller.Gmail.GmailMain;
import lk.ijse.Tm.AttendanceTm;
import lk.ijse.Tm.ClassDetailsViewTm;
import lk.ijse.dto.*;
import lk.ijse.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClassFeeFormController {
    public AnchorPane ClassFeeAnc;
    public TextField txtSearchStudentID;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentID;
    public JFXComboBox cmbMonth;
    public JFXTextField txtAmount;
    public JFXComboBox cmbClass;
    public Label lblDate;
    public TextArea txtFieldClassID;
    public JFXComboBox cmbClassName;
    public TextArea txtFieldClassName;
    public JFXComboBox txtStudentFullID;
    private Class_DetailsModel fmodel = new Class_DetailsModel();
    private Class_PaymentModel cpm = new Class_PaymentModel();
    private StudentfullDetailsModel st = new StudentfullDetailsModel();

    private String num;

    public String togmail;
    public String name;
    public String amount;
    public String Gmonth;
    public String date;
    public String toStugmail;
    public String className;
    public String fromGmail ="kavindumaduranga184@gmail.com";

    private GmailMain gm = new GmailMain();

    public void initialize(){
        setData();
        setClassIDcmb();
        // setLblDate();
        setDateAndTime();
        generateNextOrderId();
    }

    public void PayOnAction(ActionEvent actionEvent) {
        String stuId = txtSearchStudentID.getText();
        String name = txtStudentName.getText();
        String stuFullId  = (String) txtStudentFullID.getValue();
        String classId = txtFieldClassID.getText();
        String className = txtFieldClassName.getText();
        String month = (String) cmbMonth.getValue();
        double amount = Double.parseDouble(txtAmount.getText());

       // var cp = new Class_paymentDto(classId,stuId,name,month,stuFullId,amount);

        try {
            boolean isSaved = cpm.stuPaymentSave(num,classId,stuId,name,month,stuFullId,amount);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Payment Save").show();
                 sendMail(stuId);
            }else {
                new Alert(Alert.AlertType.WARNING,"Not Sucsesus Payment").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMail(String Id) throws SQLException {

        StudentfullDetailsDto mail = st.searchCustomer(Id);


        if (mail != null){
            togmail = mail.getPerant_Gmail();
            toStugmail =mail.getStudent_gmail();
            name = mail.getName();
        }

       amount = txtAmount.getText();
        Gmonth = (String) cmbMonth.getValue();
        className = txtFieldClassName.getText();

        date = lblDate.getText();
            String text ="Your student has rs for " + className +" today "+amount +" was paid as class fees on " + date;
            String text2 ="Your Payment Sucsesfull";
            String topic = "Pay Class Fees";
            gm.addGmailDEtails(fromGmail,togmail,topic,text);
            gm.addGmailDEtails(fromGmail,toStugmail,topic,text2);

    }

    private void generateNextOrderId() {
        try {
            int orderID = cpm.generateNextOrderId();
            num="00"+orderID;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
        cmbMonth.setItems(dataSet);
    }

    public void setLblDate() {
        LocalDate currentDate = LocalDate.now();

        // Format the date using a specific pattern (you can adjust the pattern as needed)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        // Set the formatted date to the label
        lblDate.setText(formattedDate);
        StackPane root = new StackPane(lblDate);
        Scene scene = new Scene(root, 300, 200);
    }

    private void setDateAndTime() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    lblDate.setText(LocalDateTime.now().format(formatter));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                cmbClass.getItems().add(classDto.getClass_id());
                cmbClassName.getItems().add(classDto.getClassName());
            }
            cmbClass.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtFieldClassID.appendText(newValue + "\n"); // Append the selected item to the TextArea
                }
            });
            cmbClassName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtFieldClassName.appendText(newValue + "\n"); // Append the selected item to the TextArea
                }
            });

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void StudentIDOnAction(ActionEvent actionEvent) {
        String sID = txtSearchStudentID.getText();

        try {
            List<Class_DetailsDto> dtoList = fmodel.getFullId(sID);

            for (Class_DetailsDto classDto : dtoList) {
                txtStudentFullID.getItems().add(classDto.getFull_id());
                txtStudentName.setText(classDto.getStu_name());
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}


