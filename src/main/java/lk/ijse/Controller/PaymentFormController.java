package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PaymentFormController {
    public AnchorPane PaymentAnc1;
    public AnchorPane PaymentAnc2;

    public void ClassFeesOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc1.getChildren().clear();
        PaymentAnc1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/ClassFeeForm.fxml")));

    }

    public void CourseFeeOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc1.getChildren().clear();
        PaymentAnc1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/CourseFeeForm.fxml")));

    }

    public void ClassFeeDetailsOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc2.getChildren().clear();
        PaymentAnc2.getChildren().add(FXMLLoader.load(getClass().getResource("/View/ClassFeeDetailsForm.fxml")));

    }

    public void CourseFeeDetailsOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc2.getChildren().clear();
        PaymentAnc2.getChildren().add(FXMLLoader.load(getClass().getResource("/View/CourseFeeDetailsForm.fxml")));

    }

    public void CourseFeesOnAction(ActionEvent actionEvent) throws IOException {
        PaymentAnc1.getChildren().clear();
        PaymentAnc1.getChildren().add(FXMLLoader.load(getClass().getResource("/View/CourseFeeForm.fxml")));


    }
}
