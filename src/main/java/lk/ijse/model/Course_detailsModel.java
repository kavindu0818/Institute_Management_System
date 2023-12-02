package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.Course_detailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.Controller.Payment.CourseFeeFormController.ORDER_ID_PREFIX;

public class Course_detailsModel {
    public static boolean upateAmount(double amont, String studentId) throws SQLException {
        System.out.println(amont + studentId);
        Connection connection = DbConnection.getInstance().getConnection();
        String bsql = "UPDATE course_details SET paidCou_fee = (paidCou_fee - ?) WHERE cusDfull_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(bsql);
        preparedStatement.setDouble(1, amont);
        preparedStatement.setString(2, studentId);
        ;

        return preparedStatement.executeUpdate() > 0;


    }

    public Course_detailsDto getAllValuesCd(String a) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM course_details WHERE stu_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, a);
        ResultSet resultSet = pstm.executeQuery();

        Course_detailsDto dto = null;
        if (resultSet.next()) {
            String cusDfull = resultSet.getString(1);
            String cusdId = resultSet.getString(2);
            String stuId = resultSet.getString(3);
            String stuName = resultSet.getString(4);
            String cusName = resultSet.getString(5);
            double paidCusFee = resultSet.getDouble(6);

            dto = new Course_detailsDto(cusDfull, cusdId, stuId, stuName, cusName, paidCusFee);
        }
        return dto;

    }

    public List<Course_detailsDto> getAllCourseValue(String sd) throws SQLException {
        System.out.println(sd);
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT *FROM course_details WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, sd);
        ResultSet resultSet = pstm.executeQuery();
        System.out.println(resultSet.toString());

        ArrayList<Course_detailsDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {

            dtoList.add(
                    new Course_detailsDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        System.out.println(dtoList.toString());
        return dtoList;

    }


    public boolean saveCourseDetails(String paymentID,String courseId,String stuId,String stuName, String courseName, Double amount) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

    String sql = "INSERT INTO course_details VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,paymentID );
        pstm.setString(2, courseId);
        pstm.setString(3, stuId);
        pstm.setString(4, stuName);
        pstm.setString(5, courseName);
        pstm.setDouble(6, amount);

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public int generateNextOrderId() throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT cusDfull_id FROM course_details ORDER BY cusDfull_id DESC LIMIT 1";
            try (PreparedStatement pstm = connection.prepareStatement(sql);
                 ResultSet resultSet = pstm.executeQuery()) {

                if (resultSet.next()) {
                    return extractOrderId(resultSet.getString(1)) + 1;
                }
                return extractOrderId("P002");
            } finally {
                // Close resources in the finally block
                if (connection != null) {

                }
            }
        }


        private int extractOrderId(String currentOrderId) {
            if (currentOrderId != null) {
                try {
                    return Integer.parseInt(currentOrderId.substring(ORDER_ID_PREFIX.length()));
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    // Handle the case where parsing or substring fails
                    return 0;
                }
            } else {
                return 0;
            }

    }

   private String splitOrderId(String currentOrderId) {
       if (currentOrderId != null) {
           String[] split = currentOrderId.split("P0");

           if (split.length > 1) {
               int id = Integer.parseInt(split[1]);
               id++;
               return "P00" + id;
           } else {
               // Handle the case where the split array has insufficient elements
               return "P001";
           }
       } else {
           return "P001";
       }
   }

    public List<Course_detailsDto> getCourseDetailsID(String a) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM course_details WHERE stu_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, a);
            ResultSet resultSet = pstm.executeQuery();

        ArrayList<Course_detailsDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new Course_detailsDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDouble(6)

                    )
            );
        }
        return dtoList;
    }

    public Course_detailsDto courseName(String cf) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM course_details WHERE cusDfull_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cf);
        ResultSet resultSet = pstm.executeQuery();

        Course_detailsDto dto = null;
        if (resultSet.next()) {
            String cusDfull = resultSet.getString(1);
            String cusdId = resultSet.getString(2);
            String stuId = resultSet.getString(3);
            String stuName = resultSet.getString(4);
            String cusName = resultSet.getString(5);
            double paidCusFee = resultSet.getDouble(6);

            dto = new Course_detailsDto(cusDfull, cusdId, stuId, stuName, cusName, paidCusFee);
        }
        return dto;


    }

    public Course_detailsDto getAllDetails(String id, String cusID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM course_details WHERE stu_id = ? AND cus_id=? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2, cusID);
        ResultSet resultSet = pstm.executeQuery();

        Course_detailsDto dto = null;
        if (resultSet.next()) {
            String cusDfull = resultSet.getString(1);
            String cusdId = resultSet.getString(2);
            String stuId = resultSet.getString(3);
            String stuName = resultSet.getString(4);
            String cusName = resultSet.getString(5);
            double paidCusFee = resultSet.getDouble(6);

            dto = new Course_detailsDto(cusDfull, cusdId, stuId, stuName, cusName, paidCusFee);
        }
        return dto;



    }

    public Course_detailsDto getsendMailValue(String csId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM course_details WHERE cusDfull_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, csId);

        ResultSet resultSet = pstm.executeQuery();

        Course_detailsDto dto = null;
        if (resultSet.next()) {
            String cusDfull = resultSet.getString(1);
            String cusdId = resultSet.getString(2);
            String stuId = resultSet.getString(3);
            String stuName = resultSet.getString(4);
            String cusName = resultSet.getString(5);
            double paidCusFee = resultSet.getDouble(6);

            dto = new Course_detailsDto(cusDfull, cusdId, stuId, stuName, cusName, paidCusFee);
        }
        return dto;

    }
}

