package com.bookstore.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{
	
    private ArrayList<LineItem> items;

	public Cart(ArrayList<LineItem> items) {
		this.items = items;
	}
	/**
     * Creates a new ShoppingCart with no content.
     */
	public Cart() {
		this.items = new ArrayList<LineItem>();
	}

	public ArrayList<LineItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<LineItem> items) {
		this.items = items;
	}
 
    /**
     * Adds a lineitem to this ShoppingCart.
     * @param item The book to be added
     */
   
	public void add(LineItem item) {
	        //If the item already exists in the cart, only the quantity is changed.
	        String code = item.getIsbn();
	        int quantity = item.getQuantity();
	        for (int i = 0; i<items.size(); i++) {
	            LineItem lineItem = items.get(i);
	            if (lineItem.getIsbn().equals(code)) {
	                lineItem.setQuantity(quantity);
	                return;
	            }
	        } 
	        items.add(item);
	    }
	

    /**
     * Removes all lineitem from the ShoppingCart.
     * @param item The book to be added
     */
		
	public void removeAllItems() {
		items = new ArrayList<LineItem>();
	}
    

    /**
     * Removes a lineitem to this ShoppingCart.
     * @param item The item to be added
     */
	
    public void remove(LineItem item) {
        String code = item.getIsbn();
        for (int i = 0; i<items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getIsbn().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
}
