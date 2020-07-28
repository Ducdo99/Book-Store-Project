/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.account;

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
public class AccountDAO implements Serializable {

    private AccountDTO accountInfo = null;

    public AccountDTO getAccountInfo() {
        return accountInfo;
    }
    private List<AccountDTO> accountList = null;

    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "select lastname, isAdmin, username "
                        + "from Account "
                        + "where username = ? and password = ? and status = ?";
                pstm = con.prepareStatement(selectSQL);
                pstm.setString(1, username);
                pstm.setString(2, password);
                pstm.setBoolean(3, true);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("username");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    if (this.accountInfo == null) {
                        this.accountInfo = new AccountDTO();
                    }
                    this.accountInfo.setUsername(userName);
                    this.accountInfo.setLastname(fullname);
                    this.accountInfo.setIsAdmin(role);
                    return true;
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
        return false;
    }

    public void searchAccountByName(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "select username, password, "
                        + "lastname, isAdmin "
                        + "from Account "
                        + "where lastname LIKE ? and status = ?";
                pstm = con.prepareStatement(selectSQL);
                pstm.setString(1, "%" + searchValue + "%");
                pstm.setBoolean(2, true);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    accountInfo
                            = new AccountDTO(username, password, fullname, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(accountInfo);
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
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "update Account "
                        + "set status = ? "
                        + "where username = ?";
                pstm = con.prepareStatement(selectSQL);
                pstm.setBoolean(1, false);
                pstm.setString(2, username);
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

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "update Account "
                        + "set password = ?, isAdmin = ? "
                        + "where username = ?";
                pstm = con.prepareStatement(selectSQL);
                pstm.setString(1, password);
                pstm.setBoolean(2, isAdmin);
                pstm.setString(3, username);
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

    public int getStatusAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "select status "
                        + "from Account "
                        + "where username = ?";
                pstm = con.prepareStatement(selectSQL);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if(status == false) {
                        return 0;
                    }//end if account has been disable
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
        return -1;
    }

    public boolean createNewAccount(String username, String password,
            String fullname, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String selectSQL = "insert into "
                        + "Account(username, password, lastname, isAdmin, status) "
                        + "values(?, ?, ?, ?, ?)";
                pstm = con.prepareStatement(selectSQL);
                pstm.setString(1, username);
                pstm.setString(2, password);
                pstm.setString(3, fullname);
                pstm.setBoolean(4, role);
                pstm.setBoolean(5, true);
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
