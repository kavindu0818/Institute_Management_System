package lk.ijse.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lk.ijse.model.StudentfullDetailsModel;
import lk.ijse.model.TutorModel;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardOriginalController {
    public Label lblStudentCount;
    public Label lblTutorCont;
    public Label lbltutorCount2;
    public Label lblTime;
    public Label lblDate;

    private StudentfullDetailsModel sm = new StudentfullDetailsModel();


    public void initialize(){
        setLableStu();
        setLabletutor();
        Time();
        date();
    }

    public void setLableStu(){
        var model = new StudentfullDetailsModel();

        try {
            int count = model.howMachStudent();

            lblStudentCount.setText(String.valueOf(count));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLabletutor(){
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
}

