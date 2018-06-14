package com.bookstore.tests;

import java.math.BigDecimal;
import java.util.Arrays;

import com.bookstore.models.Book;
import com.bookstore.stock.BookStock;

public class TestBookStock {
	
	public static void main(String[] args){
		BookStock stock = new BookStock();
		System.out.println("All books:");
		System.out.println(Arrays.toString(stock.list(null)));
		System.out.println("All books with title or author on 'r':");
		System.out.println(Arrays.toString(stock.list("r")));
		System.out.println("All books with title or author on 'ra':");
		System.out.println(Arrays.toString(stock.list("ra")));
		System.out.println("All books with title or author on 'ri':");
		System.out.println(Arrays.toString(stock.list("ri")));

		//Adding book
		System.out.println("Adding a book" );
		Book b = new Book("Pachinko","Min Lee", new BigDecimal(100.50));
		stock.add(b,15);
		assert stock.list("Pachinko").length>0 : "Could not find newly added book";
		System.out.println("Adding book passed");
	    }
}
