package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.DaySheduleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaySheduleModel {
    public boolean saveValues(DaySheduleDto day) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO dayshedule VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,day.getClassName());
        pstm.setString(2,day.getDate());
        pstm.setString(3,day.getStime());
        pstm.setString(4,day.getETime());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<DaySheduleDto> getAllShedul() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "SELECT * FROM dayshedule WHERE Date = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, String.valueOf(sqldate));
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<DaySheduleDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new DaySheduleDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)

                    )
            );
        }
        return dtoList;


    }
}
