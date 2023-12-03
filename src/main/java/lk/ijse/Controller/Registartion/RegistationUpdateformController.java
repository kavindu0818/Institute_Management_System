package lk.ijse.Controller.Registartion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.Controller.regex.Regex;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.StudentfullDetailsDto;
import lk.ijse.model.ClassModel;
import lk.ijse.model.StudentfullDetailsModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;

///import static com.sun.tools.doclint.Entity.image;


public class RegistationUpdateformController {
    public AnchorPane RegistationUpdate;

    public TextField txtUpdateFirstName;
    public TextField txtSearchId;
    public TextField txtUpddateLastName;
    public TextField txtUpdateRegidd;
    public TextField txtUpdateStuGmail;
    public TextField txtUpdateGrade;
    public TextField txtUpdateId;
    public TextField txtUpdateContact;
    public TextField txtUpdateStuCon;
    public TextField txtUpdateRegDate;
    public TextField txtUpdateStuAge;
    public TextArea txtUpdateSubject;
    public TextField txtUpdateAddress;
    public TextField txtUpdatePerantFName;
    public TextField txtUpdatePerantLName;
    public TextField txtUpdatePerantContact;
    public TextField txtUpdatePerantId;
    public TextField txtUpdatePerantGmail;
    public TextField txtUpdateName;
    public TextField txtUpdatePerantName;
    public ImageView imageView;
    public Button openBrowser;
    public FileInputStream fileInputStream;
    public Image image;
    public ComboBox cmbSubjct;
    private StudentfullDetailsModel up = new StudentfullDetailsModel();

    public void initialize() {
        setClassIDcmb();

    }

    public void UpdateBackAction(ActionEvent actionEvent) throws IOException {
        RegistationUpdate.getChildren().clear();
        RegistationUpdate.getChildren().add(FXMLLoader.load(getClass().getResource("/View/RegistationMain.fxml")));
    }

    public void LoarUpdateDetails(ActionEvent actionEvent) {
        String id = txtSearchId.getText();


        try {
            StudentfullDetailsDto studentDto = up.searchCustomer(id);

            if (studentDto != null) {
                txtUpdateId.setText(studentDto.getStu_id());
                txtUpdateRegidd.setText(studentDto.getReg_id());
                txtUpdateName.setText(studentDto.getName());
                txtUpdateContact.setText(studentDto.getStudent_contactNo());
                txtUpdateGrade.setText(studentDto.getGrade());
                txtUpdateStuAge.setText(studentDto.getAge());
                txtUpdateRegDate.setText(studentDto.getRegDate());
                txtUpdateStuGmail.setText(studentDto.getStudent_gmail());
                txtUpdateAddress.setText(studentDto.getAddress());
                txtUpdateSubject.setText(studentDto.getSub_id());
                txtUpdatePerantName.setText(studentDto.getPerant_Name());
                txtUpdatePerantContact.setText(studentDto.getPerant_contactNo());
                txtUpdatePerantGmail.setText(studentDto.getPerant_Gmail());
                Image fxImage = up.convertBytesToJavaFXImage(studentDto.getImage());
                imageView.setImage(fxImage);


            } else {
                new Alert(Alert.AlertType.INFORMATION, "Student not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void SaveUpdateOnAction(ActionEvent actionEvent) {

        if(isCheckValue()){
        String stu_id = txtUpdateId.getText();
        String reg_id = txtUpdateRegidd.getText();
        String Stuname = txtUpdateName.getText();
        String stuContact = txtUpdateContact.getText();
        String stuGrade = txtUpdateGrade.getText();
        String stuAge = txtUpdateStuAge.getText();
        String regDate = txtUpdateRegDate.getText();
        String stuGmail = txtUpdateStuGmail.getText();
        String address = txtUpdateAddress.getText();
        String sub = txtUpdateSubject.getText();
        String perantName = txtUpdatePerantName.getText();
        String perantCon = txtUpdatePerantContact.getText();
        String perantId = txtUpdatePerantId.getText();
        String perantGmail = txtUpdatePerantGmail.getText();
        Image image = imageView.getImage();
        byte[] ima = up.imagenToByte(image);

            if (stu_id.isEmpty() || reg_id.isEmpty() || Stuname.isEmpty() || stuContact.isEmpty() || stuGrade.isEmpty() || stuAge.isEmpty() || stuGmail.isEmpty() || address.isEmpty() || stuContact.isEmpty() ||
                    stuGrade.isEmpty() || perantName.isEmpty() || perantCon.isEmpty() || perantId.isEmpty() ||
                    stuAge.isEmpty() || image.isBackgroundLoading()) {

                new Alert(Alert.AlertType.ERROR, "Field Not found").showAndWait();
                return;
            }


            var su = new StudentfullDetailsDto(stu_id, reg_id, Stuname, regDate, stuGmail, stuContact, sub, address, stuAge, stuGrade, perantName, perantGmail, perantCon, ima);

        try {
            boolean isSaved = up.updateSave(su);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student Save!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Agin").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

     }
    }

    private boolean isCheckValue() {

        if (!(Regex.getRegistrationCodePattern().matcher(txtUpdateRegidd.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING,"Registration Not Valid").show();
            return false;
        }

        if (!(Regex.getNamePattern().matcher(txtUpdateFirstName.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"First Name Not Valid").show();
            return false;

        }
        if (!(Regex.getNamePattern().matcher(txtUpdateName.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Last Name Not Valid").show();
            return false;
        }

        if (!(Regex.getCodePattern().matcher(txtUpdateId.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Student Id Not Valid").show();
            return false;

        }
        if (!(Regex.getEmailPattern().matcher(txtUpdateStuGmail.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Gmail Not Valid").show();
            return false;

        }

        if (!(Regex.getMobilePattern().matcher(txtUpdateContact.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Contact Number Not Valid").show();
            return false;

        }

        if (!(Regex.getNamePattern().matcher(txtUpdatePerantFName.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Perent First Name Not Valid").show();
            return false;
        }

        if (!(Regex.getMobilePattern().matcher(txtUpdatePerantContact.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Perant Contact Nummber Not Valid").show();
            return false;

        }

        if (!(Regex.getEmailPattern().matcher(txtUpdatePerantGmail.getText()).matches())){
            new Alert(Alert.AlertType.WARNING,"Perant Gamil Not Valid").show();
            return false;

        }

        if (!(Regex.getAgePattern().matcher(txtUpdateStuAge.getText()).matches())) {
            new Alert(Alert.AlertType.WARNING, "Student Age Not Valid").show();
            return false;
        }

        return true;

    }
    public void OpenBrowserOnAction(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File file =chooser.showOpenDialog(openBrowser.getScene().getWindow());
        try {
            fileInputStream=new FileInputStream(file);
            image=new Image(fileInputStream);
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setClassIDcmb() {
        var model = new ClassModel();

        try {
            List<ClassDto> dtoList = model.getAllClass();

            for (ClassDto classDto : dtoList) {
                cmbSubjct.getItems().add(classDto.getClass_id());

            }
            cmbSubjct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtUpdateSubject.appendText(newValue + "\n"); // Append the selected item to the TextArea
                }
            });


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
