package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.DaySheduleDto;
import lk.ijse.dto.StudentAttendance;

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

        String sql = "SELECT * FROM dayshedule";
        PreparedStatement pstm = connection.prepareStatement(sql);
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
