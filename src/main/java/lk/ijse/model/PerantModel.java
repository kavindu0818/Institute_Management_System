package lk.ijse.model;


import lk.ijse.db.DbConnection;
import lk.ijse.dto.PerantDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PerantModel {

    public static boolean perantRegistrtion(PerantDto pr) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO parent VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, pr.getPer_id());
        pstm.setString(2, pr.getName());
        pstm.setString(3, pr.getGmail());
        pstm.setString(4, pr.getContactNo());
        pstm.setString(5, pr.getStu_id());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    ;
}
