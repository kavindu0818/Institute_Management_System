package lk.ijse.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.InstitutMangementDto;
import lk.ijse.dto.UserDto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public UserDto selectUserValue() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = " SELECT *FROM user ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        UserDto dto = null;

        if (resultSet.next()) {
            String password = resultSet.getString(1);
            String userName = resultSet.getString(2);
            byte[] imageBytes = resultSet.getBytes(3);


            dto = new UserDto(password, userName, imageBytes);
        }
        return dto;
    }

    public UserDto getUserValue(String pw) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = " SELECT *FROM user WHERE password=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,pw);
        ResultSet resultSet = pstm.executeQuery();

        UserDto dto = null;

        if (resultSet.next()) {
            String password = resultSet.getString(1);
            String userName= resultSet.getString(2);
            byte[] imageBytes = resultSet.getBytes(3);


            dto = new UserDto(password,userName,imageBytes);
        }
        return dto;

    }
    public static Image convertBytesToJavaFXImage(byte[] imageData) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            BufferedImage bufferedImage = ImageIO.read(bis);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
