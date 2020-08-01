/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.cartdetail;

import ducdm.util.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author MinhDuc
 */
public class CartDetailDAO implements Serializable {

    //insert the info of books which user checked out
    public boolean insertCartDetail(CartDetailDTO dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String insertSQL = "insert into "
                        + "CartDetail(cartID, bookID, bookName, "
                        + "quantity, price, total) "
                        + "values(?,?,?,?,?,?)";
                pstm = con.prepareStatement(insertSQL);
                pstm.setString(1, dto.getCartID());
                pstm.setString(2, dto.getBookID());
                pstm.setString(3, dto.getBookName());
                pstm.setInt(4, dto.getQuantity());
                pstm.setDouble(5, dto.getPrice());
                pstm.setDouble(6, dto.getTotal());
                int row = pstm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
