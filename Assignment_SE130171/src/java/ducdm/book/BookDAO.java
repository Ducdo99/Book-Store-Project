/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.book;

import ducdm.util.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author MinhDuc
 */
public class BookDAO implements Serializable {

    private BookDTO bookInfo = null;

    public BookDTO getBookInfo() {
        return bookInfo;
    }

    private List<BookDTO> bookList = null;

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public boolean loadProductData()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "select bookName, price "
                        + "from Book "
                        + "where status = ?";
                pstm = con.prepareStatement(selectSQL);
                pstm.setBoolean(1, true);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String bookName = rs.getString("bookName");
                    double price = rs.getDouble("price");
                    this.bookInfo = new BookDTO(bookName, price);
                    if (this.bookList == null) {
                        this.bookList = new ArrayList<>();
                    }
                    this.bookList.add(this.bookInfo);
                }
                return true;
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
        return false;
    }

    public boolean getBookID(BookDTO dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "select bookID "
                        + "from Book "
                        + "where bookName = ? and price = ?";
                pstm = con.prepareStatement(selectSQL);
                pstm.setString(1, dto.getBookName());
                pstm.setDouble(2, dto.getPrice());
                rs = pstm.executeQuery();
                if (rs.next()) {
                    String bookID = rs.getString("bookID");
                    this.bookInfo = new BookDTO();
                    this.bookInfo.setBookID(bookID);
                }
                return true;
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
        return false;
    }
    
    public boolean getBookQuantityAfterUpdate(String bookID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "select quantity "
                        + "from Book "
                        + "where bookID = ?";
                pstm = con.prepareStatement(selectSQL);
                pstm.setString(1, bookID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    int bookQuantity = rs.getInt("quantity");
                    this.bookInfo = new BookDTO();
                    this.bookInfo.setQuantity(bookQuantity);
                }
                return true;
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
        return false;
    }
    
    

    public boolean updateStatus(String bookID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String updateSQL = "update Book "
                        + "set status = ? "
                        + "where bookID = ?";
                pstm = con.prepareStatement(updateSQL);
                pstm.setBoolean(1, false);
                pstm.setString(2, bookID);
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

    public boolean updateQuantity(String bookID, int orederedQuantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String updateSQL = "update Book "
                        + "set quantity = "
                        + "(select quantity "
                        + "from Book "
                        + "where bookID = ? ) - ? "
                        + "where bookID = ?";
                pstm = con.prepareStatement(updateSQL);
                pstm.setString(1, bookID);
                pstm.setInt(2, orederedQuantity);
                pstm.setString(3, bookID);
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
