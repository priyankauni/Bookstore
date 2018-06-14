package com.bookstore.tests;

import java.math.BigDecimal;

import com.bookstore.models.Book;

public class TestBook{
    public static void main(String[] args){
	Book b = new Book("Pachinko","Min Lee", new BigDecimal(100.50));
	System.out.print("Running price test...");
	assert (b.getPrice().compareTo(new BigDecimal(100.50))==0):
	"Price was wrong. Got: " +
	    b.getPrice()+" Expected: 100.50";
	System.out.println("done!");
	System.out.print("Running title test...");
	assert b.getTitle() != null && b.getTitle().equals("Pachinko"):
	    "Title was wrong. Got: "+
	    b.getTitle() +
	    " Expected: Pachinko";
	System.out.println("done!");
	System.out.print("Running author test...");
	assert b.getAuthor() != null && b.getAuthor().equals("Min Lee"):
	    "Author was wrong. Got: " +
	    b.getAuthor() +
	    " Expected: Min Lee";
	System.out.println("done!");
	System.out.print("Running toString() test...");	
	assert b.toString() != null && b.toString().equals("Pachinko;Min Lee;100.50"):
	    "toString failed: " + b.toString();
	System.out.println("done!");
	System.out.print("Testing toString of large number for formatting");
	b = new Book("Little Fires Everywhere","Celeste", new BigDecimal(10022.50000));
	assert b.toString() !=null && b.toString().equals("Little Fires Everywhere;Celeste;10,022.50"):
	    "toString for large number-price failed. Got: "+b.toString()+ " Expected: "+
	    "Little Fires Everywhere;Celestee;10,022.50";
	System.out.println("done!");	
	b = new Book("Pachinko","Min Lee", new BigDecimal(100.50));
	Book b2 = new Book("Little Fires Everywhere","Celeste", new BigDecimal(100.50));
	System.out.print("Testing equals...");
	assert b.equals(b2) && b2.equals(b)
	    : "Equals test failed.";
	System.out.println(" Equals test passed!");	
	System.err.println("Tests completed successfully.");
    }

}
