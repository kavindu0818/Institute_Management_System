import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Controller.Gmail.GmailMain;

import java.io.IOException;

public class InstituteShaduleFormController {

    public AnchorPane AncShedul2;
    public TextField txtGmailFrom;
    public TextField txtGmailTo;
    public TextArea txtGmailMessage;
    public TextField txtGmailSubject;

    private GmailMain gmail = new GmailMain();

    public void AddClassOnAction(ActionEvent actionEvent) throws IOException {
        AncShedul2.getChildren().clear();
        AncShedul2.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Add_ClassForm.fxml")));
    }

    public void AddCourseOnAction(ActionEvent actionEvent) throws IOException {
        AncShedul2.getChildren().clear();
        AncShedul2.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Add _CourseForm.fxml")));
    }

    public void btnSendGmailOnAction(ActionEvent actionEvent) {
        String from = txtGmailFrom.getText();
        String to = txtGmailTo.getText();
        String sub = txtGmailSubject.getText();
        String msg = txtGmailMessage.getText();

        gmail.addGmailDEtails(from,to,sub,msg);

    }
}
