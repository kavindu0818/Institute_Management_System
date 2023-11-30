package lk.ijse.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Tm.ClassDetailsViewTm;
import lk.ijse.Tm.DayShedulTm;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.DaySheduleDto;
import lk.ijse.dto.InstitutMangementDto;
import lk.ijse.model.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DashboardOriginalController {
    public Label lblStudentCount;
    public Label lblTutorCont;
    public Label lbltutorCount2;
    public Label lblTime;
    public Label lblDate;
    public AnchorPane DashboardOriganl;
    public AnchorPane Twonc;
    public TableView tblShedulView;
    public TableColumn colClass;
    public TableColumn colStartTime;
    public TableColumn colEndTime;
    public Label lblsetGmail;
    public Label lblSetFb;
    public Label lblSetContact;
    public Label lblHall;



    public Label lblAllAttendance;
    // String ld = lblDate.getText();
    // String dl = lblDate.getText();
    private StudentfullDetailsModel sm = new StudentfullDetailsModel();
    public void initialize() {
        setLableStu();
        setLabletutor();
        Time();
        date();
        getShedulValue();
        shedulTable();
        setLabContact();
        allAttendance();


    }

    public void shedulTable(){
        colClass.setCellValueFactory(new PropertyValueFactory<>("className"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

    }

    public void setLableStu() {
        var model = new StudentfullDetailsModel();

        try {
            int count = model.howMachStudent();

            lblStudentCount.setText(String.valueOf(count));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLabletutor() {
        var model = new TutorModel();

        try {
            int count = model.howMachTutor();

            lbltutorCount2.setText(String.valueOf(count));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Time() {

        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    lblTime.setText(LocalDateTime.now().format(formatter));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void date() {

        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    lblDate.setText(LocalDateTime.now().format(formatter));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void setLabContact() {
        var model = new InstituteDetailsModel();

        try {
            InstitutMangementDto imd = model.setAllDetails();

            lblsetGmail.setText(imd.getGmail());
            lblSetContact.setText(imd.getContact());
            lblSetFb.setText(imd.getFb());
            lblHall.setText(imd.getHall());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   /* public void getShedulValue() {
        var smt = new DaySheduleModel();

        ObservableList<DayShedulTm> obList = FXCollections.observableArrayList();

        try {
            List<DaySheduleDto> dtoList = smt.getAllShedul();

            for (DaySheduleDto dto : dtoList) {
                String date = dto.getDate();

                if (date.equals(lblDate.getText())) {
                    obList.add(
                            new DayShedulTm(
                                    dto.getClassName(),
                                    dto.getStime(),
                                    dto.getETime()
                            )
                    );
                }
            }

            // Move these lines outside of the loop
            tblShedulView.setItems(obList);
            tblShedulView.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
   public  void getShedulValue() {
       var smt = new DaySheduleModel();

       ObservableList<DayShedulTm> obList = FXCollections.observableArrayList();

       try {
           List<DaySheduleDto> dtoList = smt.getAllShedul();

           for (DaySheduleDto dto : dtoList) {
                   obList.add(new DayShedulTm(
                           dto.getClassName(),
                           dto.getStime(),
                           dto.getETime()
                   )
                   );
               }


           tblShedulView.setItems(obList);
           tblShedulView.refresh();

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

   public void allAttendance(){

       int clsA = 0;
       int cosA = 0;




       var model = new Stu_AttendanceModel();

       try {
           int count = model.howMachStudent();

           clsA =count;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

       var Cmodel = new CourseAttendanceModel();

       try {
           int count = Cmodel.howMachCourseStudent();

           cosA = count;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

       int allA = cosA + clsA;
       lblAllAttendance.setText(String.valueOf(allA));
   }
}

