package lk.ijse.model;


import lk.ijse.db.DbConnection;
import lk.ijse.dto.Class_DetailsDto;
import lk.ijse.dto.StudentAttendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stu_AttendanceModel {


    public boolean saveAttendnceDetails(Class_DetailsDto dtoList) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "INSERT INTO stu_attendance VALUES(?, ?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dtoList.getStu_name());
        pstm.setString(2, String.valueOf(sqldate));
        pstm.setString(3, dtoList.getFull_id());
        pstm.setString(4, dtoList.getStu_id());
        pstm.setString(5, dtoList.getClass_id());
        pstm.setString(6, String.valueOf(sqltime));

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }


    public List<StudentAttendance> getAllStudent() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "SELECT * FROM stu_attendance WHERE date = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, String.valueOf(sqldate));
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StudentAttendance> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new StudentAttendance(
                            resultSet.getString(1),
                            resultSet.getDate(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return dtoList;

    }

    public List<StudentAttendance> getClassStudent(String clId, LocalDate classDate) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM stu_attendance WHERE class_id = ? AND date = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,clId);
        pstm.setString(2, String.valueOf(classDate));
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StudentAttendance> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new StudentAttendance(
                            resultSet.getString(1),
                            resultSet.getDate(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
            return dtoList;
    }

    public List<StudentAttendance> getStudentAllAttendnce(String iD) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM stu_attendance WHERE stu_id =?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,iD);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<StudentAttendance> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new StudentAttendance(
                            resultSet.getString(1),
                            resultSet.getDate(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return dtoList;

    }

    public int howMachStudent() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());


        String sql ="select count(stu_id) from stu_attendance WHERE date=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1,sqldate);
        ResultSet resultSet = pstm.executeQuery();

        int a = 0;

        if (resultSet.next()){
            return resultSet.getInt(1);

        }
        return 0;

    }


}

