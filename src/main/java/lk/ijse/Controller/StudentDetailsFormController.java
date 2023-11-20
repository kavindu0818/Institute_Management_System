package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.Tm.*;
import lk.ijse.dto.Class_paymentDto;
import lk.ijse.dto.StudentAttendance;
import lk.ijse.dto.StudentfullDetailsDto;
import lk.ijse.model.Class_PaymentModel;
import lk.ijse.model.Stu_AttendanceModel;
import lk.ijse.model.StudentfullDetailsModel;

import java.sql.SQLException;
import java.util.List;

public class StudentDetailsFormController {
    public TextField txtSearchStudentID;
    public TableView tblStudenDetails;
    public TableColumn colSDstuID;
    public TableColumn colSDregID;
    public TableColumn colSDstuName;
    public TableColumn colSDstuGmail;
    public TableColumn colSDstuContact;
    public TableColumn colSDstuAddress;
    public TableView tblPerantDetails;
    public TableColumn colPDperName;
    public TableColumn colPDperGmail;
    public TableColumn colPDperContact;
    public TableView tblAttendanceDetails;
    public TableColumn colADdate;
    public TableColumn colADsubject;
    public TableColumn colADtime;
    public TableView tblPaymentDetails;
    public TableColumn colPeyDmonth;
    public TableColumn colPaysubject;
    public TableColumn colPayDay;
    public ImageView imageViewStudent;

    private StudentfullDetailsModel stu = new StudentfullDetailsModel();
    private Stu_AttendanceModel stuAttendanceModel = new Stu_AttendanceModel();
    private Class_PaymentModel classPaymentModel = new Class_PaymentModel();
    private StudentfullDetailsModel up = new StudentfullDetailsModel();

    public void initialize(){
        colSDstuID.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colSDregID.setCellValueFactory(new PropertyValueFactory<>("regId"));
        colSDstuName.setCellValueFactory(new PropertyValueFactory<>("stuName"));
        colSDstuGmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
        colSDstuContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSDstuAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        setParentTableValue();
        setAttendanceTableValue();
        setPaymentTableValue();

    }

    public void setParentTableValue(){
        colPDperName.setCellValueFactory(new PropertyValueFactory<>("Pname"));
        colPDperGmail.setCellValueFactory(new PropertyValueFactory<>("Pgmail"));
        colPDperContact.setCellValueFactory(new PropertyValueFactory<>("Pcontact"));

    }
    public void setAttendanceTableValue(){
        colADdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colADsubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colADtime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    public void setPaymentTableValue(){
        colPeyDmonth.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colPaysubject.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colPayDay.setCellValueFactory(new PropertyValueFactory<>("day"));

    }

    public void btnSearchStudent(ActionEvent actionEvent) {
        String iD = txtSearchStudentID.getText();

        ObservableList<StudentDetailsTm> obList = FXCollections.observableArrayList();
        ObservableList<ParentDetails> obList2 = FXCollections.observableArrayList();

        try {
            List<StudentfullDetailsDto> dtoList = stu.getClassStudent(iD);
            for (StudentfullDetailsDto dto : dtoList) {
                obList.add(
                        new StudentDetailsTm(
                                dto.getStu_id(),
                                dto.getReg_id(),
                                dto.getName(),
                                dto.getStudent_gmail(),
                                dto.getStudent_contactNo(),
                                dto.getAddress()
                        )
                );
                tblStudenDetails.setItems(obList);
                tblStudenDetails.refresh();

            }


                for (StudentfullDetailsDto dto : dtoList) {
                    obList2.add(
                            new ParentDetails(
                                    dto.getPerant_Name(),
                                    dto.getPerant_Gmail(),
                                    dto.getPerant_contactNo()

                            )
                    );
                    tblPerantDetails.setItems(obList2);
                    tblPerantDetails.refresh();

                }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<AttendanceDetailsTm> obList3 = FXCollections.observableArrayList();

        try{
            List<StudentAttendance> dtoList = stuAttendanceModel.getStudentAllAttendnce(iD);
            for (StudentAttendance dto : dtoList) {
                obList3.add(
                        new AttendanceDetailsTm(
                                dto.getDate(),
                                dto.getClass_id(),
                                dto.getTime()
                        )
                );
                tblAttendanceDetails.setItems(obList3);
                tblAttendanceDetails.refresh();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<PaymentDetailsTm> obList4 = FXCollections.observableArrayList();

        try{
            List<Class_paymentDto> dtoList = classPaymentModel.getStudentAllPayment(iD);
            for (Class_paymentDto dto : dtoList) {
                obList4.add(
                        new PaymentDetailsTm(
                                dto.getClass_Id(),
                                dto.getAmount(),
                                dto.getDate()
                        )
                );
                tblPaymentDetails.setItems(obList4);
                tblPaymentDetails.refresh();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try{
            StudentfullDetailsDto studentDto = up.searchCustomer(iD);
            if (studentDto != null) {

                Image fxImage = up.convertBytesToJavaFXImage(studentDto.getImage());
                imageViewStudent.setImage(fxImage);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
