/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.cart;

import ducdm.util.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author MinhDuc
 */
public class CartDAO implements Serializable {

    //get info of receiver, sender and cartID to insert into Cart table
    public boolean insertIntoCart(String cartID, String senderName, String receiverName, 
            String receiverAddress) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String insertSQL = "insert into "
                        + "Cart(cartID, username, receiverName, receiverAddress) "
                        + "values(?, ?, ?, ?)";
                pstm = con.prepareStatement(insertSQL);
                pstm.setString(1, cartID);
                pstm.setString(2, senderName);
                pstm.setString(3, receiverName);
                pstm.setString(4, receiverAddress);
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

    //get max the number cartID in Cart table
    public String getMaxCartID() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "select count(cartID) as maxCartID "
                        + "from Cart";
                pstm = con.prepareStatement(selectSQL);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    String maxCartID = rs.getString("maxCartID");
                    return maxCartID;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
