package lk.ijse.model;


import lk.ijse.db.DbConnection;
import lk.ijse.dto.Class_DetailsDto;

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

    public List<Class_DetailsDto> getFullId(String sID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM  class_Details WHERE stu_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, sID);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<Class_DetailsDto> dtoList = new ArrayList<>();
        // ClassDto dto = null;

        while(resultSet.next()) {
            dtoList.add(
                    new Class_DetailsDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)

                    )
            );
        }
        return dtoList;

    }

    public boolean saveClassDetails(Class_DetailsDto ad) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO class_details VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, ad.getFull_id());

        pstm.setString(2, ad.getStu_id());
        pstm.setString(3, ad.getClass_id());
        pstm.setString(4, ad.getStu_name());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean saveValue(String attendance, String stuId, String classID, String stuName) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO class_details VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, attendance);
        pstm.setString(2, stuId);
        pstm.setString(3, classID);
        pstm.setString(4,stuName);


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public Class_DetailsDto getsendMailValue(String atId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM  class_Details WHERE full_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, atId);

        ResultSet resultSet = pstm.executeQuery();

        Class_DetailsDto dto = null;

        if(resultSet.next()) {
            String fullId = resultSet.getString(1);
            String stuId = resultSet.getString(2);
            String clasID = resultSet.getString(3);
            String name = resultSet.getString(4);

            dto = new Class_DetailsDto(fullId,stuId,clasID,name);

    }
        return dto;

}
}
