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
public class CartDTO implements Serializable {

    private String cartID;
    private String receiverName;
    private String receiverAddress;

    public CartDTO() {
    }

    public CartDTO(String cartID, String receiverName, String receiverAddress) {
        this.cartID = cartID;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
    }

    /**
     * @return the cartID
     */
    public String getCartID() {
        return cartID;
    }

    /**
     * @param cartID the cartID to set
     */
    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    /**
     * @return the receiverName
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * @param receiverName the receiverName to set
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * @return the receiverAddress
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * @param receiverAddress the receiverAddress to set
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

}
