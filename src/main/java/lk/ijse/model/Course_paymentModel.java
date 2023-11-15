package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Course_paymentModel {
    public static boolean savePayment(String amount, String cusDfull) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "INSERT INTO course_payment VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1,21);
        pstm.setString(2, amount);
        pstm.setString(3, String.valueOf(sqldate));
        pstm.setString(4, String.valueOf(sqltime));
        pstm.setString(5, cusDfull);

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

}

