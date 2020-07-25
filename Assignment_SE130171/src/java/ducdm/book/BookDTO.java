/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.book;

import java.io.Serializable;

/**
 *
 * @author MinhDuc
 */
public class BookDTO implements Serializable { 
    private String bookID; 
    private String bookName; 
    private int quantity; 
    private double price;

    public BookDTO() {
    }

    public BookDTO(String bookID, String bookName, int quantity, double price) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
    }

    public BookDTO(String bookName, int quantity, double price) {
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
    }
    public BookDTO(String bookName, double price) {
        this.bookName = bookName;
        this.price = price;
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
    
    
}
