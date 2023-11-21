package lk.ijse.Controller.Employee;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.Controller.regex.Regex;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.model.EmployeeModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAddFormControler {
    public AnchorPane AddEmployeeRoot;
    public ImageView imageViewEmp;
    public Button imgSelectBtn;
    public JFXTextField txtEmpId;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNum;
    public JFXTextField txtGmailEmp;
    public javafx.scene.control.DatePicker DatePicker;
    public JFXTextField txtBankAccount;
    public JFXTextField txtPosition;
    public JFXTextField txtNic;
    public JFXTextField txtBankBranchName;
    public JFXComboBox cmd1;
    public JFXTextField txtAge;
    public FileInputStream fileInputStream;
    public Image image;
    public JFXTextField txtEmpAttendanceID;

    private EmployeeModel empM = new EmployeeModel();

    public void initialize(){
        setData();
    }
    public void btnSubmitOnAction(ActionEvent actionEvent) {

        if (isCheackValue()) {

            String empId = txtEmpId.getText();
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String gmail = txtGmailEmp.getText();
            String contactNum = txtContactNum.getText();
            String nic = txtNic.getText();
            String address = txtAddress.getText();
            String position = txtPosition.getText();
            String date = String.valueOf(DatePicker.getValue());
            String bankAccont = txtBankAccount.getText();
            Integer age = txtAge.getLength();
            String bankName = txtBankBranchName.getText();
            String geand = (String) cmd1.getValue();
            Image image = imageViewEmp.getImage();
            byte[] ima = empM.imagenToByte(image);
            String name = firstName + lastName;
            String empAttenId = txtEmpAttendanceID.getText();

            if (empId.isEmpty() || firstName.isEmpty()|| lastName.isEmpty() || gmail.isEmpty()|| contactNum.isEmpty()|| nic.isEmpty()|| address.isEmpty()|| position.isEmpty()|| date.isEmpty()||
                bankName.isEmpty()|| age != 0||bankName.isEmpty()||geand.isEmpty()||image.isBackgroundLoading()){
            }

            var emp = new EmployeeDto(empId, name, gmail, contactNum, nic, address, position, date, bankAccont, bankName, age, geand, ima, empAttenId);

            try {
                boolean isSaved = empM.saveEmployee(emp);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Save Employee").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private boolean isCheackValue() {
        if (!(Regex.getEmpRegistrationCodePattern().matcher(txtEmpId.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Id Not Valid").show();
            return false;
        }
        if (!(Regex.getNamePattern().matcher(txtFirstName.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee First Name Not Valid").show();
            return false;
        }
        if (!(Regex.getNamePattern().matcher(txtLastName.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Last Name Not Valid").show();
            return false;
        }
        if (!(Regex.getEmailPattern().matcher(txtGmailEmp.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Gmail Not Valid").show();
            return false;
        }
        if (!(Regex.getMobilePattern().matcher(txtContactNum.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Contact Number Not Valid").show();
            return false;
        }
        if (!(Regex.getIdPattern().matcher(txtNic.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Nic Not Valid").show();
            return false;
        }
        if (!(Regex.getNamePattern().matcher(txtPosition.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Position Not Valid").show();
            return false;
        }

        if (!(Regex.getDigitPattern().matcher(txtBankAccount.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Bank Account Not Valid").show();
            return false;
        }
        if (!(Regex.getIntPattern().matcher(txtAge.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Age Not Valid").show();
            return false;
        }
        if (!(Regex.getNamePattern().matcher(txtBankBranchName.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Bank Branch Not Valid").show();
            return false;
        }

        if (!(Regex.getNamePattern().matcher(txtPosition.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Position Not Valid").show();
            return false;
        }
        if (!(Regex.getEmpAttencodePattern().matcher(txtEmpAttendanceID.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Employee Attendance ID Not Valid").show();
            return false;
        }
        return true;

    }

    public void btnBrowserOpenOnAction(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File file =chooser.showOpenDialog(imgSelectBtn.getScene().getWindow());
        try {
            fileInputStream=new FileInputStream(file);
            image=new Image(fileInputStream);
            imageViewEmp.setImage(image);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AddEmployeeRoot.getChildren().clear();
        AddEmployeeRoot.getChildren().add(FXMLLoader.load(getClass().getResource("/View/EmployeeForm.fxml")));

    }
    private void setData(){

        ArrayList<String> list = new ArrayList<>();
        list.add("Male");
        list.add("Female");
        ObservableList<String> dataSet = FXCollections.observableArrayList(list);
        cmd1.setItems(dataSet);
    }
}

