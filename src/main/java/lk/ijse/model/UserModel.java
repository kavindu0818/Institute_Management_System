package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {
    public boolean setUserDetails(UserDto ud) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO user VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, ud.getPassword());
        pstm.setString(2, ud.getUserName());

        byte[] imageSr = ud.getImage();
        pstm.setBytes(3, imageSr);


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
}
