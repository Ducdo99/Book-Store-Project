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
public class AccountSignInErrors implements Serializable{
    private String incorrectUsernameOrPasswordErr;
    private String doNotClickOnReCaptchaErr; 

    public AccountSignInErrors() {
    }

    /**
     * @return the incorrectUsernameOrPasswordErr
     */
    public String getIncorrectUsernameOrPasswordErr() {
        return incorrectUsernameOrPasswordErr;
    }

    /**
     * @param incorrectUsernameOrPasswordErr the incorrectUsernameOrPasswordErr to set
     */
    public void setIncorrectUsernameOrPasswordErr(String incorrectUsernameOrPasswordErr) {
        this.incorrectUsernameOrPasswordErr = incorrectUsernameOrPasswordErr;
    }

    /**
     * @return the doNotClickOnReCaptchaErr
     */
    public String getDoNotClickOnReCaptchaErr() {
        return doNotClickOnReCaptchaErr;
    }

    /**
     * @param doNotClickOnReCaptchaErr the doNotClickOnReCaptchaErr to set
     */
    public void setDoNotClickOnReCaptchaErr(String doNotClickOnReCaptchaErr) {
        this.doNotClickOnReCaptchaErr = doNotClickOnReCaptchaErr;
    }
}
