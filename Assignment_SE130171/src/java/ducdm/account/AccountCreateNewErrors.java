/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.account;

import java.io.Serializable;

/**
 *
 * @author MinhDuc
 */
public class AccountCreateNewErrors implements Serializable {

    private String usernameLengthErr;
    private String passwordLengthErr;
    private String confirmNotMatchedErr;
    private String fullnameLengthErr;
    private String usernamIsExisted;
    private String usernamBeDisable;

    public AccountCreateNewErrors() {
    }

    /**
     * @return the usernameLengthErr
     */
    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    /**
     * @param usernameLengthErr the usernameLengthErr to set
     */
    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the confirmNotMatchedErr
     */
    public String getConfirmNotMatchedErr() {
        return confirmNotMatchedErr;
    }

    /**
     * @param confirmNotMatchedErr the confirmNotMatchedErr to set
     */
    public void setConfirmNotMatchedErr(String confirmNotMatchedErr) {
        this.confirmNotMatchedErr = confirmNotMatchedErr;
    }

    /**
     * @return the fullnameLengthErr
     */
    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    /**
     * @param fullnameLengthErr the fullnameLengthErr to set
     */
    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }

    /**
     * @return the usernamIsExisted
     */
    public String getUsernamIsExisted() {
        return usernamIsExisted;
    }

    /**
     * @param usernamIsExisted the usernamIsExisted to set
     */
    public void setUsernamIsExisted(String usernamIsExisted) {
        this.usernamIsExisted = usernamIsExisted;
    }

    /**
     * @return the usernamBeDisable
     */
    public String getUsernamBeDisable() {
        return usernamBeDisable;
    }

    /**
     * @param usernamBeDisable the usernamBeDisable to set
     */
    public void setUsernamBeDisable(String usernamBeDisable) {
        this.usernamBeDisable = usernamBeDisable;
    }
}
