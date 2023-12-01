package lk.ijse.model;

import com.mysql.cj.jdbc.interceptors.ResultSetScannerInterceptor;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ClassDto;
import lk.ijse.dto.SubjectDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectModel {

    public boolean subSave(SubjectDto sub) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO subject VALUES(?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,sub.getSub_id());
        pstm.setString(2,sub.getSubjectName());



        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<SubjectDto> getSubject() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT *FROM subject";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();


        ArrayList<SubjectDto> dtoList = new ArrayList<>();
        // ClassDto dto = null;

        while(resultSet.next()) {
            dtoList.add(
                    new SubjectDto(
                            resultSet.getString(1),
                            resultSet.getString(2)


                    )
            );
        }
        return dtoList;
    }
}
