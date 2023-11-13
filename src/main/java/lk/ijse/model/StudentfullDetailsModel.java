package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.StudentfullDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentfullDetailsModel {
    public static boolean saveStudentDetails(StudentfullDetailsDto sr) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO studentfull_details VALUES(?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, sr.getStu_id());
        pstm.setString(2, sr.getReg_id());
        pstm.setString(3, sr.getName());
        pstm.setString(4, sr.getRegDate());
        pstm.setString(5, sr.getStudent_gmail());
        pstm.setString(6, sr.getStudent_contactNo());
        pstm.setString(7, sr.getSub_id());
        pstm.setString(8, sr.getAddress());
        pstm.setString(9, sr.getAge());
        pstm.setString(10, sr.getGrade());
        pstm.setString(11, sr.getPerant_id());
        pstm.setString(12, sr.getPerant_Name());
        pstm.setString(13, sr.getPerant_Gmail());
        pstm.setString(14, sr.getPerant_contactNo());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }



    public StudentfullDetailsDto searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM  studentfull_details WHERE stu_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        StudentfullDetailsDto dto = null;

        if(resultSet.next()) {
            String stu_id = resultSet.getString(1);
            String reg_id = resultSet.getString(2);
            String Stuname = resultSet.getString(3);
            String regDate = resultSet.getString(4);
            String stuGmail = resultSet.getString(5);
            String StuContact = resultSet.getString(6);
            String sub_id = resultSet.getString(7);
            String adddress = resultSet.getString(8);
            String age = resultSet.getString(9);
            String grade = resultSet.getString(10);
            String perant_id = resultSet.getString(11);
            String perant_name = resultSet.getString(12);
            String perant_Gmail = resultSet.getString(13);
            String perant_ContactNo = resultSet.getString(14);

            dto = new  StudentfullDetailsDto(stu_id,reg_id,Stuname,regDate,stuGmail,StuContact,sub_id,adddress,age,grade,perant_id,perant_name,perant_Gmail,perant_ContactNo);
        }
        return dto;
}

    public boolean updateSave(StudentfullDetailsDto su) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE studentfull_details SET reg_id = ?,name = ?,regDate = ?,Student_gmail = ?,Student_contactNo =?,sub_id = ?, address = ?,age =?,grade = ?,perant_id = ?,Perant_Name = ?,Perant_Gmail = ?, Perant_contactNo =? WHERE stu_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, su.getReg_id());
        pstm.setString(2, su.getName());
        pstm.setString(3, su.getRegDate());
        pstm.setString(4, su.getStudent_gmail());
        pstm.setString(5, su.getStudent_contactNo());
        pstm.setString(6, su.getSub_id());
        pstm.setString(7, su.getAddress());
        pstm.setString(8, su.getAge());
        pstm.setString(9, su.getGrade());
        pstm.setString(10, su.getPerant_id());
        pstm.setString(11, su.getPerant_Name());
        pstm.setString(12, su.getPerant_Gmail());
        pstm.setString(13, su.getPerant_contactNo());
        pstm.setString(14, su.getStu_id());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteStudent(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM studentfull_details WHERE stu_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }
}

