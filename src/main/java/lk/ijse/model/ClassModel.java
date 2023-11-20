package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClassDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassModel {

    public List<ClassDto> getAllClass() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT *FROM class ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();


        ArrayList<ClassDto> dtoList = new ArrayList<>();
       // ClassDto dto = null;

        while(resultSet.next()) {
            dtoList.add(
                    new ClassDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)

                    )
            );
        }
        return dtoList;
    }


    public List<ClassDto> getAllStudent() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT *FROM class ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();


        ArrayList<ClassDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new ClassDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)

                    )
            );
        }
        return dtoList;
    }

    public boolean saveCoursesClass(String courseID, String courseName, String tutor) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO class VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,courseID);
        pstm.setString(2,courseName);
        pstm.setString(3,tutor);


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean saveCoursesClass(ClassDto cd) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO class VALUES(?,?,?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, cd.getClass_id());
            pstm.setString(2, cd.getTut_id());
            pstm.setString(3, cd.getClassName());

            return pstm.executeUpdate() > 0;
        }
    }

    public boolean classSave(ClassDto cms) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO class VALUES(?,?,?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, cms.getClass_id());
            pstm.setString(2, cms.getTut_id());
            pstm.setString(3, cms.getClassName());

            return pstm.executeUpdate() > 0;
        }

    }
}
