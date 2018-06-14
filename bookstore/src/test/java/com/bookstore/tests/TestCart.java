package com.bookstore.tests;

import java.math.BigDecimal;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.models.Cart;
import com.bookstore.models.LineItem;

public class TestCart {
	public static void main(String[] args) throws NumberFormatException, BookNotFoundException{
		Cart cart = new Cart();	
		System.out.println("DEBUG: empty, newly created cart: " + cart);
		LineItem lineItem = new LineItem();
		lineItem.setIsbn("2");
		lineItem.setQuantity(1);
		lineItem.setTitle("How To Spend Money");
		lineItem.setAuthor("Rich Bloke");
		System.out.print("Testing to add one book priced 1 000 000.....");
		cart.add(lineItem);
		for(LineItem item : cart.getItems()) {
			assert item.getTotalPriceDouble().compareTo(new BigDecimal(1000000))==0
					 : "Total amount wrong, expected 1000000, got: " + item.getTotalPriceDouble();
			System.out.println("Adding one book, total amount test passed.");
		}
		   
		cart.remove(lineItem);;
		assert lineItem.getTotalPriceDouble().compareTo(new BigDecimal(0))==0
		    : "Total amount wrong, expected 0, got: " + lineItem.getTotalPriceDouble();
		System.out.println("Removing the only book, total amount test passed.");
		System.out.print("Testing to add one book priced 1 000 000 twice.....");
		cart.add(lineItem);
		cart.add(lineItem);
		assert lineItem.getTotalPriceDouble().compareTo(new BigDecimal(2000000))==0
		    : "Total amount wrong, expected 2000000, got: " + lineItem.getTotalPriceDouble();
		System.out.println("Adding one book, total amount test passed.");
	    }

}
