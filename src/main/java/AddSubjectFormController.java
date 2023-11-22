import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.dto.SubjectDto;
import lk.ijse.model.SubjectModel;

import java.sql.SQLException;

public class AddSubjectFormController {
    public JFXTextField txtSubId;
    public JFXTextField txtSubName;

    private SubjectModel sm = new SubjectModel();

    public void btnAddSubjectOnAction(ActionEvent actionEvent) throws SQLException {

        String subID = txtSubId.getText();
        String subName = txtSubName.getText();

        var sub = new SubjectDto(subID,subName);


        boolean isSave = sm.subSave(sub);

        if (isSave){
            new Alert(Alert.AlertType.INFORMATION,"Subject Save").show();;

        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again Not Save Subject").show();
        }

    }
}
