package lk.ijse.Controller.Gmail;

import javafx.scene.control.Alert;

import javax.security.auth.Subject;
import java.io.File;

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

   public static void sendOrderConformMailFile(String to,String subject, File file) {
        GmailSender gEmailSender = new GmailSender();
        String from = "kavindumaduranga184@gmail.com";

        boolean b = gEmailSender.sendEmailFile(to, from, subject, file);
        if (b) {
            System.out.println("Email is sent successfully");
        } else {
            System.out.println("There is problem in sending email");
        }
    }


}

