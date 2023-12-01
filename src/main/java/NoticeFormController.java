import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import lk.ijse.dto.NoticeDto;
import lk.ijse.model.NoticeModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class NoticeFormController {
    public javafx.scene.control.DatePicker DatePicker;
    public JFXTextArea txtNotice;

    private NoticeModel nm = new NoticeModel();

    public void btnNoticeOnAction(ActionEvent actionEvent) {
        String nt = txtNotice.getText();
        String day = String.valueOf(DatePicker.getValue());

        var nd = new NoticeDto(nt,day);

        try {
            boolean isSave = nm.setNotice(nd);

            if (isSave){
                new Alert(Alert.AlertType.INFORMATION,"Save Notice").show();

            }else{
                new Alert(Alert.AlertType.WARNING,"Not Save Notice").show();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        txtNotice.clear();
    }
}
