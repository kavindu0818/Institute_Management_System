package lk.ijse.Controller.Employee;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.StudentfullDetailsDto;
import lk.ijse.model.EmployeeModel;

import java.sql.SQLException;
import java.util.Date;

public class EmployeeUpdateFormController {
    public ImageView imageViewEmp;
    public Button imgSelectBtn;
    public JFXTextField txtEmpId;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNum;
    public JFXTextField txtGmailEmp;

    public JFXTextField txtBankAccount;
    public JFXTextField txtPosition;
    public JFXTextField txtNic;
    public JFXTextField txtBankBranchName;
    public JFXComboBox cmd1;
    public JFXTextField txtAge;;
    public AnchorPane AncEmployeeUpdate;
    public TextField txtSearchEmpUpdate;
    public JFXTextField txtGender;
    public JFXTextField txtRegistationDate;
    public JFXTextField txtEmpAttendnceMarkID;

    private EmployeeModel empM = new EmployeeModel();

    public void btnSubmitOnAction(ActionEvent actionEvent) {
        String empId = txtEmpId.getText();
        String name = txtFirstName.getText();
        String gmail = txtGmailEmp.getText();
        String contactNum = txtContactNum.getText();
        String nic = txtNic.getText();
        String address = txtAddress.getText();
        String position = txtPosition.getText();
        String date = txtRegistationDate.getText();
        String bankAccont = txtBankAccount.getText();
        Integer age = Integer.parseInt(txtAge.getText());
        String bankName = txtBankBranchName.getText();
        String gendar = txtGender.getText();
        Image image = imageViewEmp.getImage();
        byte[] ima = empM.imagenToByte(image);
        String empAm = txtEmpAttendnceMarkID.getText();


        var emp = new EmployeeDto(empId,name,gmail,contactNum,nic,address,position,date,bankAccont,bankName,age,gendar,ima,empAm);

        try {
            boolean isSaved = empM.updateEmployee(emp);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Save Employee").show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBrowserOpenOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void btnSerachOnAction(ActionEvent actionEvent) {
        String empId = txtSearchEmpUpdate.getText();

        try {
            EmployeeDto employeeDto = empM.searchEmployee(empId);

            if ( employeeDto!= null) {
                txtEmpId.setText(employeeDto.getEmp_id());
                txtFirstName.setText(employeeDto.getName());
                txtAddress.setText(employeeDto.getAddress());
                txtContactNum.setText(employeeDto.getContactNo());
                txtGender.setText(employeeDto.getGendar());
                txtPosition.setText(employeeDto.getPosition());
                txtNic.setText(employeeDto.getNic());
                txtBankAccount.setText(employeeDto.getBankAccountNum());
                txtBankBranchName.setText(employeeDto.getBankBranchName());
                String age = String.valueOf(employeeDto.getAge());
                txtAge.setText(age);
                txtGmailEmp.setText(employeeDto.getGmail());
                Image fxImage = empM.convertBytesToJavaFXImage(employeeDto.getImage());
                imageViewEmp.setImage(fxImage);
                txtRegistationDate.setText(employeeDto.getDate());


            } else {
                new Alert(Alert.AlertType.INFORMATION, "Student not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }




    }
}
