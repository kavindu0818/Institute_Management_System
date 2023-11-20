package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.CourseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseModel {
    public boolean saveCourseDetails(String courseID, String courseName, String courseFeee, String courseDuration, String date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO course VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,courseID);
        pstm.setString(2,courseName);
        pstm.setString(3,courseFeee);
        pstm.setString(4,date);
        pstm.setString(5,courseDuration);

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<CourseDto> getAllcourse() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT *FROM course ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();


        ArrayList<CourseDto> dtoList = new ArrayList<>();
        // ClassDto dto = null;

        while(resultSet.next()) {
            dtoList.add(
                    new CourseDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)


                    )
            );
        }
        return dtoList;
    }
}
