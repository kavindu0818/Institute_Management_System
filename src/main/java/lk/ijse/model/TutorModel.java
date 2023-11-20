package lk.ijse.model;


import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorModel {

    public int howMachTutor() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql ="select count(tut_id) from tutor";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        int a = 0;

        if (resultSet.next()){
            return resultSet.getInt(1);

        }
        return 0;

    }
    }

