package lk.ijse.model;

import lk.ijse.Tm.CourseDetailsTm;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SetPaymentDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SetPaymentModel {

    public boolean setPaymentDetails(SetPaymentDto dto) throws SQLException {


            Connection con = null;
            try{

                con = DbConnection.getInstance().getConnection();
                con.setAutoCommit(false);

                boolean isOrderSaved = Course_paymentModel.savePayment(String.valueOf(dto.getAmount()), dto.getCusDfull());
                if (isOrderSaved){
                    System.out.println("save ok");
                    boolean isItemUpdated = updatePayment(dto.getAmount(),dto.getCusDfull());
                    if (isItemUpdated){
                        System.out.println("updated");

                            // all 3 queries must be success
                            con.commit();
                            return true;
                        }else {
                            con.rollback();
                        }
                    }else {
                        con.rollback();
                    }
            } catch (SQLException e) {
                if (con != null) con.rollback();
                e.printStackTrace();
            }finally {
                if (con != null) con.setAutoCommit(true);
            }

        return false;
        }

    private boolean updatePayment(double a,String id) throws SQLException {
            boolean isUpdateItem = Course_detailsModel.upateAmount(a,id);
            if (!isUpdateItem){
                return false;
            }

        return true;
    }

}




