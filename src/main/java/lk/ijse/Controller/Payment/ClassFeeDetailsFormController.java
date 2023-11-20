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

    public void initialize(){
        setData();
        setClassIDcmb();
        setTable();
    }

    public void setTable(){
        colStuId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStuName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));


    }

    public void SearchDetailsClassFessOnAction(ActionEvent actionEvent) {
        String classId = (String) cmbClassID.getValue();
        String month = (String) cmbMonth.getValue();

        ObservableList<ClassFeesDetailsTm> obList = FXCollections.observableArrayList();

        try {
            List<Class_paymentDto> dtoList = cfp.getClassStudent(classId, month);

            for (Class_paymentDto dto : dtoList) {
                obList.add(new ClassFeesDetailsTm(
                        dto.getStu_Id(),
                        dto.getName(),
                        dto.getDate(),
                        dto.getAmount()
                ));
            }
            System.out.println(obList.toString());

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
}
