package lk.ijse.Controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class AttendanceFormController {
    public TextField txtScannerValue;
    public Label ScannerLable;

    public void AttendanceOnStartOnAction(ActionEvent actionEvent) {

        Webcam webcam = Webcam.getDefault();   //Generate Webcam Object
        webcam.setViewSize(new Dimension(320, 240));
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setMirrored(false);
        JFrame jFrame = new JFrame();
        jFrame.add(webcamPanel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        do {
            try {
                BufferedImage image = webcam.getImage();
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result = new MultiFormatReader().decode(bitmap);
                String s = result.getText();
                if (result.getText() != null) {
                 //   String s = result.getText();
                   ScannerLable.setText(s);
                    System.out.println(s);

                  //  System.out.println(result);

                  //txtScannerValue.setText(String.valueOf(result));
                    /*JOptionPane.showMessageDialog(null, result.getText(), "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                    jFrame.setVisible(false);
                    jFrame.dispose();
                    webcam.close();*/
                    break;
                }

            } catch (Exception e) {
            }
        } while (true);
    }


    public void AttendanceOffStartOnAction(ActionEvent actionEvent) {
    }
}
