package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.Class_paymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Class_PaymentModel {
   /* public boolean stuPaymentSave(Class_paymentDto cp) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "INSERT INTO class_payment VALUES(?, ?, ?, ?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,splitOrderId(null));

        pstm.setString(2, cp.getClass_Id());
        pstm.setString(3, cp.getStu_Id());
        pstm.setString(4, cp.getName());
        pstm.setString(5, cp.getPaymentMonth());
        pstm.setString(6, String.valueOf(sqldate));
        pstm.setString(7, cp.getFull_Id());
        pstm.setDouble(8, cp.getAmount());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }*/
    public static int generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT payment_Id FROM class_payment ORDER BY payment_Id DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(Integer.parseInt(resultSet.getString(1)));
        }
        return splitOrderId(0001);
    }

    private static int splitOrderId(int id) {
        if (id ==0){
            return 1;
        }
        return++id;
}
   /* private static String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "PO00" + id;
        } else {
            return "PO001";
        }
    }*/

    public List<Class_paymentDto> getClassStudent(String classId, String month) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM class_payment WHERE class_Id= ? AND paymentMonth = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, classId);
        pstm.setString(2, month);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Class_paymentDto> dList = new ArrayList<>();
        while (resultSet.next()) {
            dList.add(
                    new Class_paymentDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6),
                            resultSet.getString(7),
                            resultSet.getDouble(8)
                    )
            );
        }
        resultSet.close();
        pstm.close();
        connection.close();

        return dList;
        }

    public boolean stuPaymentSave(String num,String classId, String stuId, String name, String month, String stuFullId, double amount) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        java.sql.Timestamp sqltime = new java.sql.Timestamp(date.getTime());

        String sql = "INSERT INTO class_payment VALUES(?, ?, ?, ?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, num);

        pstm.setString(2, classId);
        pstm.setString(3, stuId);
        pstm.setString(4, name);
        pstm.setString(5, month);
        pstm.setString(6, String.valueOf(sqldate));
        pstm.setString(7, stuFullId);
        pstm.setDouble(8, amount);

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<Class_paymentDto> getStudentAllPayment(String iD) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM class_payment WHERE stu_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,iD);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Class_paymentDto> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new Class_paymentDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6),

                            resultSet.getString(7),
                            resultSet.getDouble(8)
                    )
            );
        }

        return dtoList;
    }

    public List<Class_paymentDto> readyClassFessDetails(String classId, String month) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM class_payment WHERE class_Id= ? AND paymentMonth =?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,classId);
        pstm.setString(2,month);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Class_paymentDto> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            System.out.println("aaaaaaaaaaaaaaaa");
            dtoList.add(
                    new Class_paymentDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6),

                            resultSet.getString(7),
                            resultSet.getDouble(8)
                    )
            );
        }

        return dtoList;

    }

    public List<Class_paymentDto> getAllClassPayment(String clssID, String month) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM class_payment WHERE Class_Id = ? AND paymentMonth = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, clssID);
            pstm.setString(2, month);

            ResultSet resultSet = pstm.executeQuery();
                ArrayList<Class_paymentDto> dtoList = new ArrayList<>();
                while (resultSet.next()) {
                    dtoList.add(new Class_paymentDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getDate(6),
                            resultSet.getString(7),
                            resultSet.getDouble(8)
                    ));
                }
                return dtoList;
            }
        }




