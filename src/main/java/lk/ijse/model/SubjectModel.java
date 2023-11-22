package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.SubjectDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
