package lk.ijse.Controller.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.Tm.ClassDetailsViewTm;
import lk.ijse.Tm.EmployeeViewTm;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.StudentfullDetailsModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {
    public AnchorPane EmployeeMain;
    public Pane AncEmployeeRoot;
    public AnchorPane AncEmployeeRoot1;
    public TableView tblEmployeeview;
    public TableColumn colEmpID;
    public TableColumn colEmpName;
    public Label lblEmpCount;

    public void initialize() {

        colEmpID.setCellValueFactory(new PropertyValueFactory<>("EmpId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("EmpName"));
        AllEmployee();
        setLableEmp();

    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AncEmployeeRoot1.getChildren().clear();
        AncEmployeeRoot1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/EmployeeAddForm.fxml")));

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        AncEmployeeRoot1.getChildren().clear();
        AncEmployeeRoot1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/EmployeeUpdateForm.fxml")));
    }

    public void btnEmployeeDetailsOnAction(ActionEvent actionEvent) throws IOException {
        AncEmployeeRoot1.getChildren().clear();
        AncEmployeeRoot1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/EmployeeDetailsForm.fxml")));

    }

    public void AllEmployee() {

        var model = new EmployeeModel();


        ObservableList<EmployeeViewTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> dtoList = model.getAllEmployee();

            for (EmployeeDto dto : dtoList) {
                obList.add(
                        new EmployeeViewTm(
                                dto.getEmp_id(),
                                dto.getName()

                        )
                );
            }

            tblEmployeeview.setItems(obList);
            tblEmployeeview.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLableEmp() {
        var model = new EmployeeModel();

        try {
            int count = model.howMachEmployee();

            lblEmpCount.setText(String.valueOf(count));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddTutorOnAction(ActionEvent actionEvent) throws IOException {
        AncEmployeeRoot1.getChildren().clear();
        AncEmployeeRoot1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/ToutorAddForm.fxml")));


    }
}

