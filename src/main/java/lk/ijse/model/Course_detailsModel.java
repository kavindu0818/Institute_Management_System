package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.Course_detailsDto;
import lk.ijse.dto.StudentAttendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Course_detailsModel {
    public static boolean upateAmount(double amont, String studentId) throws SQLException {
        System.out.println(amont+studentId);
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

        Course_detailsDto dto=null;
        if (resultSet.next()){
            String cusDfull = resultSet.getString(1);
            String cusdId = resultSet.getString(2);
            String stuId = resultSet.getString(3);
            String stuName = resultSet.getString(4);
            String cusName = resultSet.getString(5);
            double paidCusFee = resultSet.getDouble(6);


                dto = new Course_detailsDto(cusDfull,cusdId,stuId,stuName,cusName,paidCusFee);
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
        System.out.println(dtoList.toString());
        return dtoList;

    }


}
