package lk.ijse.model;


import lk.ijse.db.DbConnection;
import lk.ijse.dto.TutorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorModel {

    public int howMachTutor() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "select count(tut_id) from tutor";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        int a = 0;

        if (resultSet.next()) {
            return resultSet.getInt(1);

        }
        return 0;

    }

    public boolean saveTutor(TutorDto td) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO tutor VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, td.getTut_id());
        pstm.setString(2, td.getTutorName());
        pstm.setString(3, td.getSub_id());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public TutorDto getTutor(String tutId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "select *from tutor WHERE tut_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, tutId);
        ResultSet resultSet = pstm.executeQuery();

        TutorDto doList = null;

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String sub = resultSet.getString(3);

            doList = new TutorDto(id, name, sub);
        }
        return doList;
    }

    public boolean updateTutor(TutorDto td) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE tutor SET tutorName=?,sub_id=?  WHERE tut_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, td.getTutorName());
        pstm.setString(2, td.getSub_id());
        pstm.setString(3,td.getTut_id());



        return pstm.executeUpdate() > 0;

    }
}

