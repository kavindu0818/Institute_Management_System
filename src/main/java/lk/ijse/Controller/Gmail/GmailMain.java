package lk.ijse.Controller.Gmail;

import javafx.scene.control.Alert;

import javax.security.auth.Subject;

public class GmailMain {
    public void addGmailDEtails(String From, String To, String Sub, String Text) {
        GmailSender gmailSender = new GmailSender();
        String to = To;
        String from = From;
        String subject = Sub;
        String text = Text;
        boolean b = gmailSender.sendEmail(to, from, subject, text);
        if (b) {
           new Alert(Alert.AlertType.INFORMATION,"Send Gmail").show();
        } else {
           new Alert(Alert.AlertType.WARNING,"Pleace Try Agin Not Send Mail").show();
        }
    }

    }

