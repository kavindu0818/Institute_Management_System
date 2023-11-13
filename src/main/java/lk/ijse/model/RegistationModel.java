package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.RegistationDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistationModel {


    public static boolean resgistaionSave(RegistationDto rt) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO registration VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, rt.getReg_id());
        pstm.setString(2, rt.getName());
        pstm.setString(3, rt.getRegDate());
        pstm.setString(4, rt.getStu_id());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }
}

