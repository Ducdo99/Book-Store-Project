/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.cartdetail;

/**
 *
 * @author MinhDuc
 */
public class CartDetailDTO {

    private String cartID;
    private String bookID;
    private String bookName;
    private int quantity;
    private double price;
    private double total;

    public CartDetailDTO() {
    }

    public CartDetailDTO(String cartID, String bookID, String bookName, 
            int quantity, double price, double total) {
        this.cartID = cartID;
        this.bookID = bookID;
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
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
     * @return the bookID
     */
    public String getBookID() {
        return bookID;
    }

    /**
     * @param bookID the bookID to set
     */
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    /**
     * @return the bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param bookName the bookName to set
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

}
