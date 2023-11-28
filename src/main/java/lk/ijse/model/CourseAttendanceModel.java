package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.AttendanceJoinDto;
import lk.ijse.dto.CourseAttendanceDto;
import lk.ijse.dto.StudentAttendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseAttendanceModel {
    public boolean saveAttendnceDetails(String aId, String num1) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "INSERT INTO course_attendance VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, num1 );
        pstm.setString(2, aId);
        pstm.setString(3, String.valueOf(sqldate));
        pstm.setString(4, String.valueOf(sqltime));


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public int generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT attendanceID FROM course_attendance ORDER BY attendanceID DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitOrderId(Integer.parseInt(resultSet.getString(1)));
        }
        return splitOrderId(0001);
    }



    private static int splitOrderId(int id) {
        if (id == 0) {
            return 1;
        }
        return ++id;


    }

    public List<AttendanceJoinDto>getAllAttndance() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());

        String sql = "SELECT course_attendance.cusfull_id, course_attendance.date, course_attendance.time, " +
                "course_details.cus_id, course_details.stu_name, course_details.stu_id " +
                "FROM course_attendance " +
                "INNER JOIN course_details ON course_attendance.cusfull_id = course_details.cusDfull_id " +
                "WHERE date = ?";

           PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setDate(1, sqldate);
            ResultSet resultSet = pstm.executeQuery();

            ArrayList<AttendanceJoinDto> dtoList = new ArrayList<>();

            while (resultSet.next()) {
                dtoList.add(
                        new AttendanceJoinDto(
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
    }




