package lk.ijse.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.Tm.AttendanceTm;
import lk.ijse.Tm.CourseDetailsTm;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.Course_detailsDto;
import lk.ijse.dto.StudentAttendance;
import lk.ijse.model.Course_detailsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseFeeDetailsFormController {
    public static TableView<CourseDetailsTm> tblCourseDetails;
    public TableColumn colCusDetilStuId;
    public TableColumn colCusDetilStuName;
    public TableColumn colCusDetilPayMonth;
    public TableColumn colCusDetilDate;
    public TableColumn colCusDetilPayAmont;
    public JFXTextField txtCurseDetailsSearchCusId;

    private Course_detailsModel cdMdel = new Course_detailsModel();
    // public static ObservableList<CourseDetailsTm> obList;

    public void initialize() {
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colCusDetilStuId.setCellValueFactory(new PropertyValueFactory<>("Cus_Id"));
        colCusDetilStuName.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        colCusDetilPayMonth.setCellValueFactory(new PropertyValueFactory<>("stu_Name"));
        colCusDetilDate.setCellValueFactory(new PropertyValueFactory<>("cus_Name"));
        colCusDetilPayAmont.setCellValueFactory(new PropertyValueFactory<>("amount"));


    }


    public void CurseSearchOnAction(ActionEvent actionEvent) throws SQLException {
      String cd = txtCurseDetailsSearchCusId.getText();
        System.out.println(cd);

        ObservableList<CourseDetailsTm> obList  = FXCollections.observableArrayList();

        try {
            List<Course_detailsDto> dtoList = cdMdel.getAllCourseValue(cd);
            System.out.println(dtoList.toString());
            for (Course_detailsDto dto : dtoList) {
                obList.add(
                        new CourseDetailsTm(
                                dto.getCusDfull(),
                                dto.getCusId(),
                                dto.getStuId(),
                                dto.getStuName(),
                                dto.getCusName(),
                                dto.getPaidCusFee()

                        )
                );
                tblCourseDetails.setItems(obList);
                tblCourseDetails.refresh();

            }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

        public void btnCloseOnAction(ActionEvent actionEvent) {

    }

            // Use try-with-resources to automatically close resources






    }


