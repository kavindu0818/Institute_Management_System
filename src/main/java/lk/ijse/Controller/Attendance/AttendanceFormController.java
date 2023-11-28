package lk.ijse.Controller.Attendance;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Tm.AttendanceTm;
import lk.ijse.dto.*;
import lk.ijse.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.ijse.model.Class_PaymentModel.generateNextOrderId;

public class AttendanceFormController {


    public AnchorPane mainPane;
    public AnchorPane miniPane;
    public TextField txtScannerValue;
    public Label ScannerLable;
    public TableView<AttendanceTm> AttendanceViewTable;
    public TableColumn AttendIdCol;
    public TableColumn stuIdcol;
    public TableColumn stuNamecol;
    public TableColumn Datecol;
    public TableColumn Timecol;
    public TableColumn clssIdcol;
    public AnchorPane Ancrootattrndnce;
    public TableView tblEmployeeAttendance;
    public TableColumn colEmpID;
    public TableColumn colEmployeeName;
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private boolean isReading = false;
    private String num;
    private String num1;

    private Class_DetailsModel clModel = new Class_DetailsModel();
    private Stu_AttendanceModel stModdel = new Stu_AttendanceModel();
    private EmployeeModel em = new EmployeeModel();
    private EmpAttendanceModel ea = new EmpAttendanceModel();

    private CourseAttendanceModel ca = new CourseAttendanceModel();

    public void initialize() {
        setCellValueFactory();
        loadAllAttendnce();
        generateNextOrderId();
        generateAttendanceID();
        loadAllCourseAttendance();

    }

    private void setCellValueFactory() {
        AttendIdCol.setCellValueFactory(new PropertyValueFactory<>("regId"));
        stuIdcol.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        stuNamecol.setCellValueFactory(new PropertyValueFactory<>("stuName"));
        Datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        Timecol.setCellValueFactory(new PropertyValueFactory<>("time"));
        clssIdcol.setCellValueFactory(new PropertyValueFactory<>("classId"));


    }


    private void loadAllAttendnce() {
        var model = new Stu_AttendanceModel();

        ObservableList<AttendanceTm> obList = FXCollections.observableArrayList();

        try {
            List<StudentAttendance> dtoList = model.getAllStudent();

            for (StudentAttendance dto : dtoList) {
                obList.add(
                        new AttendanceTm(
                                dto.getFull_id(),
                                dto.getStu_id(),
                                dto.getName(),
                                dto.getDate(),
                                dto.getTime(),
                                dto.getClass_id()
                        )
                );
            }

            AttendanceViewTable.setItems(obList);
            AttendanceViewTable.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCourseAttendance() {
        var model = new CourseAttendanceModel();

        ObservableList<AttendanceTm> obList = FXCollections.observableArrayList();

        try {
            List<AttendanceJoinDto> dtoList = model.getAllAttndance();

            for (AttendanceJoinDto dto : dtoList) {
                obList.add(
                        new AttendanceTm(
                                dto.getAttendanceID(),
                                dto.getStuID(),
                                dto.getStuName(),
                                dto.getDate(),
                                dto.getTime(),
                                dto.getCusID()

                        )
                );
            }

            AttendanceViewTable.setItems(obList);
            AttendanceViewTable.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private boolean startWebcam() {
        if (webcam == null) {
            Dimension size = WebcamResolution.QVGA.getSize();
            webcam = Webcam.getDefault();
            webcam.setViewSize(size);

            webcamPanel = new WebcamPanel(webcam);
            webcamPanel.setPreferredSize(size);
            webcamPanel.setFPSDisplayed(true);
            webcamPanel.setMirrored(true);

            SwingNode swingNode = new SwingNode();
            swingNode.setContent(webcamPanel);

            miniPane.getChildren().clear();
            miniPane.getChildren().add(swingNode);
        }

        Thread thread = new Thread(() -> {
            while (isReading) {
                try {
                    Thread.sleep(1000);
                    BufferedImage image = webcam.getImage();
                    if (image != null) {
                        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                        Result result = new MultiFormatReader().decode(binaryBitmap);
                        Platform.runLater(() -> {
                            if (result != null) {
                                webcam.close();
                                txtScannerValue.setText(result.getText() + "\n");
                                new Alert(Alert.AlertType.INFORMATION, "Data Scanned Successfully!").showAndWait();
                            } else {
                                new Alert(Alert.AlertType.ERROR, "No Data Found!").showAndWait();
                            }
                        });
                    }
                } catch (NotFoundException | InterruptedException | RuntimeException ignored) {
                    // ignored
                }
            }
        });
        thread.start();
        return true;
    }

    private boolean stopWebcam() {
        if (webcamPanel != null) {
            webcamPanel.stop();
            webcamPanel = null;
        }
        if (webcam != null) {
            webcam.close();
            webcam = null;
        }
        return false;
    }

    public void AttendneStarOnAction(ActionEvent actionEvent) {
        isReading = (!isReading) ? startWebcam() : stopWebcam();
    }

    public void AttendanceOffSOnAction(ActionEvent actionEvent) {
        stopWebcam();
    }

    public void txtScannerValueOnAction(ActionEvent actionEvent) {
        String aId = txtScannerValue.getText();

        String input = aId;
        String stuRegex = "^SA[0-9]{4,10}$";
        String empRegex = "^EA[0-9]{4,10}$";
        String cRegex = "^CSA[0-9]{4,10}$";

        Pattern Spattern = Pattern.compile(stuRegex);
        Pattern Epattern = Pattern.compile(empRegex);
        Pattern Cpattern = Pattern.compile(cRegex);

        Matcher Smatcher = Spattern.matcher(input);
        Matcher Ematcher = Epattern.matcher(input);
        Matcher Cmatcher = Cpattern.matcher(input);

        if (Smatcher.matches()) {
            try {
                Class_DetailsDto dtoList = clModel.loardValues(aId);
                boolean isSaved = stModdel.saveAttendnceDetails(dtoList);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Save attendance").show();
                    loadAllAttendnce();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Not value saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else if (Ematcher.matches()) {
            try {
                EmployeeDto dtoList = em.loardEmpValues(aId);

                String empAttendanceID = null;
                String empId = null;
                if (dtoList != null) {
                    empAttendanceID = dtoList.getEmpAttendanceID();
                    empId = dtoList.getEmp_id();
                }

                boolean isSaved = ea.saveEmpAttendance(num, empAttendanceID, empId);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Employee Attendance Save").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Employee Attendance Not Saved").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else if (Cmatcher.matches()) {
            try {
                boolean isSaved = ca.saveAttendnceDetails(aId, num1);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Course Attendance Save").show();
                    loadAllCourseAttendance();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Course Attendance Not Value Saved!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Not Valid ID").show();
        }
    }


    private void generateNextOrderId() {
        try {
            int orderID =ca.generateNextOrderId();
            num1="000"+orderID;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void generateAttendanceID() {
        try {
            int orderID =ea.generateNextOrderId();
            num="000"+orderID;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnAttendanceDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Ancrootattrndnce.getChildren().clear();
        Ancrootattrndnce.getChildren().add(FXMLLoader.load(getClass().getResource("/View/AttendanceDetailsForm.fxml")));

    }


}
