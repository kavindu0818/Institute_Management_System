package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CoursePaymentJoinDto;
import lk.ijse.dto.StudentAttendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Course_paymentModel {
    public static boolean savePayment(String payId, double amount, String cusDfull, String stuID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "INSERT INTO course_payment VALUES(?, ?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        // return splitOrderId(null);

        pstm.setString(1, payId);
        pstm.setDouble(2, amount);
        pstm.setString(3, String.valueOf(sqldate));
        pstm.setString(4, String.valueOf(sqltime));
        pstm.setString(5, cusDfull);
        pstm.setString(6, stuID);

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
   /* private static String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("P0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "P00" + id;
            }else if(id < 100){
                return "P0" + id;
            }else{
                return "C" + id;
            }
        } else {
            return "C001";
        }
    }*/

    public static int generateNextCourseFeeId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT coursePayment_Id FROM course_payment ORDER BY coursePayment_Id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitOrderId(Integer.parseInt(resultSet.getString(1)));
        }
        return splitOrderId(0);
    }

    private static int splitOrderId(int id) {
        if (id == 0) {
            return 1;
        }
        return ++id;

    }

    public List<CoursePaymentJoinDto> getAllPayment(String cusID, String date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT course_payment.stu_id,course_details.stu_name,course_payment.Date,course_payment.payment\n" +
                "FROM course_details INNER JOIN course_payment ON course_payment.cusDfull_id= course_details.cusDfull_id WHERE cus_id = ? AND Date=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,cusID);
        pstm.setString(2, String.valueOf(date));
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<CoursePaymentJoinDto> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new CoursePaymentJoinDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)

                    )
            );
        }
        return dtoList;
    }
}

