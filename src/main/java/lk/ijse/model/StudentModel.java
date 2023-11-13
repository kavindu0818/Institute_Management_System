package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.StudentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentModel {

    public static boolean saveRegistation(StudentDto sr) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO studant VALUES(?, ?, ?, ?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, sr.getStu_id());
        pstm.setString(2, sr.getName());
        pstm.setString(3, sr.getGmail());
        pstm.setString(4, sr.getContactNo());
        pstm.setString(5, sr.getSub_id());
        pstm.setString(6, sr.getAddress());
        pstm.setString(7, sr.getAge());
        pstm.setString(8, sr.getGrade());
        pstm.setString(9, sr.getReg_id());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }
}
