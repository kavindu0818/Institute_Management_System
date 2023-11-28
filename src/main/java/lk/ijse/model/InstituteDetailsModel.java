package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.InstitutMangementDto;
import lk.ijse.dto.StudentfullDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstituteDetailsModel {
    public boolean saveDetails(InstitutMangementDto details) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO institute_details VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,details.getGmail());
        pstm.setString(2,details.getContact());
        pstm.setString(3,details.getFb());
        pstm.setString(4,details.getHall());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public InstitutMangementDto allDetails() throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM  institute_details ";
            PreparedStatement pstm = connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            InstitutMangementDto dto = null;

            if (resultSet.next()) {
                String gmail = resultSet.getString(1);
                String contact= resultSet.getString(2);
                String facebook = resultSet.getString(3);
                String hall = resultSet.getString(4);

                dto = new InstitutMangementDto(gmail, contact, facebook, hall);
            }
            return dto;
        }

    public boolean updateSaveDetails(InstitutMangementDto ui) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE institute_details SET gmail=?, contact=?, facebook=?,hall=? WHERE details_No=1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, ui.getGmail());
        pstm.setString(2, ui.getContact());
        pstm.setString(3, ui.getFb());
        pstm.setString(4,ui.getHall());


        return pstm.executeUpdate() > 0;

    }

    public InstitutMangementDto setAllDetails() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM  institute_details ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        InstitutMangementDto dto = null;

        if (resultSet.next()) {
            String gmail = resultSet.getString(1);
            String contact= resultSet.getString(2);
            String facebook = resultSet.getString(3);
            String hall = resultSet.getString(4);

            dto = new InstitutMangementDto(gmail, contact, facebook, hall);
        }
        return dto;

    }
}

