package lk.ijse.model;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.Class_paymentDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.StudentfullDetailsDto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {


    public static Image convertBytesToJavaFXImage(byte[] imageData) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            BufferedImage bufferedImage = ImageIO.read(bis);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] imagenToByte(Image imgId) {
        BufferedImage bufferimage = SwingFXUtils.fromFXImage(imgId, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferimage, "jpg", output);
            ImageIO.write(bufferimage, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = output.toByteArray();
        return data;
    }


    public boolean saveEmployee(EmployeeDto emp) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, emp.getEmp_id());
        pstm.setString(2, emp.getName());
        pstm.setString(3, emp.getGmail());
        pstm.setString(4, emp.getContactNo());
        pstm.setString(5, emp.getNic());
        pstm.setString(6, emp.getAddress());
        pstm.setString(7, emp.getPosition());
        pstm.setString(8, emp.getDate());
        pstm.setString(9, emp.getBankAccountNum());
        pstm.setString(10, emp.getBankBranchName());
        pstm.setInt(11, emp.getAge());

        pstm.setString(12, emp.getGendar());

        byte[] imageSr = emp.getImage();
        pstm.setBytes(13, imageSr);
        pstm.setString(14, emp.getEmpAttendanceID());
        boolean isSaved = pstm.executeUpdate() > 0;


        return isSaved;


    }

    public EmployeeDto searchEmployee(String empId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, empId);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empGamil = resultSet.getString(3);
            String empContact = resultSet.getString(4);
            String empNic = resultSet.getString(5);
            String empAddress = resultSet.getString(6);
            String empPosition = resultSet.getString(7);
            String empDate = resultSet.getString(8);
            String empBankAccount = resultSet.getString(9);
            String empBankBranch = resultSet.getString(10);
            Integer empAge = resultSet.getInt(11);
            String empGender = resultSet.getString(12);

            byte[] imageBytes = resultSet.getBytes(13);
            String empAttenID = resultSet.getString(14);


            // Image fxImage = convertBytesToJavaFXImage(imageBytes);


            dto = new EmployeeDto(emp_id, empName, empGamil, empContact, empNic, empAddress, empPosition, empDate, empBankAccount, empBankBranch,empAge, empGender, imageBytes,empAttenID);
        }
        return dto;
    }


    public boolean updateEmployee(EmployeeDto emp) throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "UPDATE employee SET name = ?,gmail = ?,contactNo = ?,nic = ?,address =?,position = ?, registrationDate = ?,bankAccountNum =?,bankBranchName =?,age = ?,gendar = ?,image =?,empAttendnceId =? WHERE emp_id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, emp.getName());
            pstm.setString(2, emp.getGmail());
            pstm.setString(3, emp.getContactNo());
            pstm.setString(4, emp.getNic());
            pstm.setString(5, emp.getAddress());
            pstm.setString(6, emp.getPosition());
            pstm.setString(7, emp.getDate());
            pstm.setString(8, emp.getBankAccountNum());
            pstm.setString(9, emp.getBankBranchName());
            pstm.setInt(10, emp.getAge());
            pstm.setString(11, emp.getGendar());

            byte[] imageSr = emp.getImage();
            pstm.setBytes(12, imageSr);
            pstm.setString(13, emp.getEmpAttendanceID());
            pstm.setString(14, emp.getEmp_id());

      //  System.out.println(toString(emp));


            return pstm.executeUpdate() > 0;

    }

    public EmployeeDto allEmployeeDetails(String empId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, empId);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empGamil = resultSet.getString(3);
            String empContact = resultSet.getString(4);
            String empNic = resultSet.getString(5);
            String empAddress = resultSet.getString(6);
            String empPosition = resultSet.getString(7);
            String empDate = resultSet.getString(8);
            String empBankAccount = resultSet.getString(9);
            String empBankBranch = resultSet.getString(10);
            Integer empAge = resultSet.getInt(11);
            String empGender = resultSet.getString(12);

            byte[] imageBytes = resultSet.getBytes(13);
            String empAttendId = resultSet.getString(14);

            // Image fxImage = convertBytesToJavaFXImage(imageBytes);


            dto = new EmployeeDto(emp_id, empName, empGamil, empContact, empNic, empAddress, empPosition, empDate, empBankAccount,  empBankBranch,empAge, empGender, imageBytes,empAttendId
            );
        }
        return dto;


    }

    public List<EmployeeDto> getAllEmployee() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<EmployeeDto> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new EmployeeDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getString(10),
                            resultSet.getInt(11),
                            resultSet.getString(12),
                            resultSet.getBytes(13),
                            resultSet.getString(14)
                    )
            );
        }

        return dtoList;
    }

    public EmployeeDto loardEmpValues(String aId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee WHERE empAttendnceId  = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, aId);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String empName = resultSet.getString(2);
            String empGamil = resultSet.getString(3);
            String empContact = resultSet.getString(4);
            String empNic = resultSet.getString(5);
            String empAddress = resultSet.getString(6);
            String empPosition = resultSet.getString(7);
            String empDate = resultSet.getString(8);
            String empBankAccount = resultSet.getString(9);
            String empBankBranch = resultSet.getString(10);
            Integer empAge = resultSet.getInt(11);
            String empGender = resultSet.getString(12);

            byte[] imageBytes = resultSet.getBytes(13);
            String empAttendId = resultSet.getString(14);

            // Image fxImage = convertBytesToJavaFXImage(imageBytes);


            dto = new EmployeeDto(emp_id, empName, empGamil, empContact, empNic, empAddress, empPosition, empDate, empBankAccount,  empBankBranch,empAge, empGender, imageBytes,empAttendId
            );
        }
        return dto;


    }
}


