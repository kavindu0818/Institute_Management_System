package lk.ijse.Controller.Payment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.Tm.AttendanceDetailsViewTm;
import lk.ijse.Tm.ClassFeesDetailsTm;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.Class_paymentDto;
import lk.ijse.dto.StudentAttendance;
import lk.ijse.model.ClassModel;
import lk.ijse.model.Class_PaymentModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassFeeDetailsFormController {
    public TableView tblClassFeesDetailsView;
    public TableColumn colStuId;
    public TableColumn colStuName;
    public TableColumn colDate;
    public TableColumn colAmount;
    public ComboBox cmbClassID;
    public ComboBox cmbMonth;

    public ClassModel model = new ClassModel();
    public Class_PaymentModel cfp = new Class_PaymentModel();
    public TableView tblClassPaymentDetails;
    public TableColumn colStuId1;
    public TableColumn colStuName2;
    public TableColumn colDate1;
    public TableColumn colAmount1;

    public void initialize(){
        setData();
        setClassIDcmb();

    }

    public void setTable(){
        colStuId1.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStuName2.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colDate1.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colAmount1.setCellValueFactory(new PropertyValueFactory<>("amount"));


    }

    public void SearchDetailsClassFessOnAction(ActionEvent actionEvent) {
        // Ensure that ComboBox items are properly initialized
        String classId = (String) cmbClassID.getValue();
        String month = (String) cmbMonth.getValue();

        System.out.println(classId);
        System.out.println(month);

        ObservableList<ClassFeesDetailsTm> obList = FXCollections.observableArrayList();

        try {
            // Ensure that the class providing getClassStudent is properly initialized (cfp)
            List<Class_paymentDto> dtoList = cfp.getClassStudent(classId, month);
            System.out.println(dtoList.toString());

            for (Class_paymentDto dto : dtoList) {
                obList.add(new ClassFeesDetailsTm(
                        dto.getStu_Id(),
                        dto.getName(),
                        dto.getDate(),
                        dto.getAmount()
                ));
            }
            System.out.println(obList.toString());

            // Ensure that the TableView (tblClassFeesDetailsView) and its columns are properly defined
            // and associated with corresponding properties in ClassFeesDetailsTm.

            // Set the items to the table after the loop is complete
            tblClassFeesDetailsView.setItems(obList);

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
        cmbMonth.setItems(dataSet);
    }

    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                cmbClassID.getItems().add(classDto.getClass_id());

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        setTable();
        String classId = (String) cmbClassID.getValue();
        String month = (String) cmbMonth.getValue();

        ObservableList<ClassFeesDetailsTm> obList = FXCollections.observableArrayList();

        try {
            List<Class_paymentDto> dtoList = cfp.readyClassFessDetails(classId, month);
            for (Class_paymentDto dto : dtoList) {
                System.out.println("aaaaaaa");
                obList.add(
                        new ClassFeesDetailsTm(
                                dto.getStu_Id(),
                                dto.getName(),
                                dto.getDate(),
                                dto.getAmount()
                        )
                );


                tblClassPaymentDetails.setItems(obList);
                tblClassPaymentDetails.refresh();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
