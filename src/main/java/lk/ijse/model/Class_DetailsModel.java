package lk.ijse.model;


import lk.ijse.db.DbConnection;
import lk.ijse.dto.Class_DetailsDto;
import lk.ijse.dto.StudentfullDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Class_DetailsModel {


    public Class_DetailsDto loardValues(String aId) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM  class_Details WHERE full_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, aId);

        ResultSet resultSet = pstm.executeQuery();
        Class_DetailsDto dto = null;

        if(resultSet.next()) {
            String fullId = resultSet.getString(1);
            String stuId = resultSet.getString(2);
            String classId = resultSet.getString(3);
            String stuName = resultSet.getString(4);

            dto = new Class_DetailsDto(fullId,stuId,classId,stuName);
        }
        return dto;

    }
}
