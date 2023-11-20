package lk.ijse.model;

import javafx.scene.control.Alert;
import lk.ijse.Tm.CourseDetailsTm;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SetPaymentDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SetPaymentModel {
    private Course_paymentModel course_paymentModel = new Course_paymentModel();

    public boolean setPaymentDetails(SetPaymentDto dto) throws SQLException {


            Connection con = null;
            try{

                con = DbConnection.getInstance().getConnection();
                con.setAutoCommit(false);

                boolean isOrderSaved = course_paymentModel.savePayment(dto.getPayId(),dto.getAmount(), dto.getCusDfull(),dto.getStuID());
                if (isOrderSaved){
                    new Alert(Alert.AlertType.WARNING,"Payment Save").show();
                    boolean isItemUpdated = updatePayment(dto.getAmount(),dto.getCusDfull());
                    if (isItemUpdated){
                        new Alert(Alert.AlertType.WARNING,"Update Ok").show();

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




