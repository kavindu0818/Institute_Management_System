package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpAttendanceModel {
    public boolean saveEmpAttendance(String num, String empAttendanceID, String empId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "INSERT INTO employee_attendance VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,num);
        pstm.setString(2, empAttendanceID);
        pstm.setString(3, empId);
        pstm.setString(4, String.valueOf(sqldate));
        pstm.setString(5, String.valueOf(sqltime));


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }
    public static int generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT attendnceID FROM employee_attendance ORDER BY attendnceID DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(Integer.parseInt(resultSet.getString(1)));
        }
        return splitOrderId(0001);
    }

    private static int splitOrderId(int id) {
        if (id ==0){
            return 1;
        }
        return++id;
    }
}
