/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.cart;

import java.io.Serializable;

/**
 *
 * @author MinhDuc
 */
public class CartCheckOutErrors implements Serializable {

    private String receiverNameLengthErr;
    private String receiverAddressLengthErr;

    public CartCheckOutErrors() {
    }

    /**
     * @return the receiverNameLengthErr
     */
    public String getReceiverNameLengthErr() {
        return receiverNameLengthErr;
    }

    /**
     * @param receiverNameLengthErr the receiverNameLengthErr to set
     */
    public void setReceiverNameLengthErr(String receiverNameLengthErr) {
        this.receiverNameLengthErr = receiverNameLengthErr;
    }

    /**
     * @return the receiverAddressLengthErr
     */
    public String getReceiverAddressLengthErr() {
        return receiverAddressLengthErr;
    }

    /**
     * @param receiverAddressLengthErr the receiverAddressLengthErr to set
     */
    public void setReceiverAddressLengthErr(String receiverAddressLengthErr) {
        this.receiverAddressLengthErr = receiverAddressLengthErr;
    }
}
