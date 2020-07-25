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
public class AccountDeleteErrors implements Serializable {
    private String accountSignedInDeleteErr; 

    public AccountDeleteErrors() {
    }

    /**
     * @return the accountSignedInDeleteErr
     */
    public String getAccountSignedInDeleteErr() {
        return accountSignedInDeleteErr;
    }

    /**
     * @param accountSignedInDeleteErr the accountSignedInDeleteErr to set
     */
    public void setAccountSignedInDeleteErr(String accountSignedInDeleteErr) {
        this.accountSignedInDeleteErr = accountSignedInDeleteErr;
    }
    
    
}
