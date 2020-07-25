/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.cart;

import ducdm.book.BookDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MinhDuc
 */
public class CartObject implements Serializable {

    private Map<String, BookDTO> items;

    public Map<String, BookDTO> getItems() {
        return items;
    }

    public void addToCart(BookDTO book) {
        if (book == null) {
            return;
        }//end if don't have any book  

        //1. Check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }//end if items is not existed 

        String bookName = book.getBookName();
        if (bookName.trim().isEmpty()) {
            return;
        }//end if the values of bookName is empty

        //default quantity of the book
        int quatity = 1;
        if (this.items.containsKey(bookName)) {
            quatity = this.items.get(bookName).getQuantity();
            //update quantity of the book
            quatity = quatity + 1;
        }//end if book name is existed in items
        book.setQuantity(quatity);
        this.items.put(bookName, book);
    }

    public void removeFromCart(String bookName) {
        if (this.items == null) {
            return;
        }//end if don't have any book in items 
        
        if (this.items.containsKey(bookName)) {
            //get value of key in Map
            BookDTO bookInItems = this.items.get(bookName);
            //remove key and value 
            this.items.remove(bookName, bookInItems);
            
            if (this.items.isEmpty()) {
                this.items = null;
            }//end if items don't have any keys and values
        }//end if book name is existed in items
    }
}
