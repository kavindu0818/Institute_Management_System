package lk.ijse.Controller.QrGenarator;

import com.google.zxing.WriterException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import lk.ijse.Controller.Gmail.GmailMain;
import lk.ijse.Controller.QrGenarator.QrGenerator;
import lk.ijse.Controller.Registartion.RegistationStudentController;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QrGenaratorFormController {
    public TextField txtText;
    public Button btnGenerate;
    public ImageView pic;
    public Button btnClear;

    private String stuMail;

    private static String filePath;

    public void onAction(ActionEvent actionEvent) {
        if (!txtText.getText().isEmpty()) {
            QrGenerator qrGenerator = new QrGenerator();
            qrGenerator.setData(txtText.getText());
            try {
                qrGenerator.getGenerator(txtText.getText());
            } catch (IOException | WriterException e) {
                new Alert(Alert.AlertType.ERROR, String.valueOf(e)).show();
            };
            String path = qrGenerator.getPath();
            String data=qrGenerator.getData();
            File file = new File(path);
            Image image = new Image(file.toURI().toString());
            pic.setImage(image);

            stuMail=RegistationStudentController.returnStuMail();
            String subject="Qr Generate Success";


            GmailMain.sendOrderConformMailFile(stuMail,subject,new File(path + data +".png"));


        } else {
            new Alert(Alert.AlertType.ERROR, "Input Data First! ").show();
        }




    }

    public void onKeyReleased(KeyEvent keyEvent) {
        if (!txtText.getText().isEmpty()) {
            btnController(false);
        } else {
            btnController(true);
        }


    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtText.clear();
        btnController(true);
        pic.setImage(new Image(new File("src/asserts/9358350_4152212.jpg").toURI().toString()));


    }
    public void initialize(URL location, ResourceBundle resources) {
        btnController(true);
    }

    void btnController(boolean flag) {
        btnGenerate.setDisable(flag);
        btnClear.setDisable(flag);
    }
}
