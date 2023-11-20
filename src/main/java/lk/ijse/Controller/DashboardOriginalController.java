package lk.ijse.Controller;

import javafx.scene.control.Label;
import lk.ijse.model.StudentfullDetailsModel;
import lk.ijse.model.TutorModel;

import java.sql.SQLException;

public class DashboardOriginalController {
    public Label lblStudentCount;
    public Label lblTutorCont;
    public Label lbltutorCount2;

    private StudentfullDetailsModel sm = new StudentfullDetailsModel();


    public void initialize(){
        setLableStu();
        setLabletutor();

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
}

