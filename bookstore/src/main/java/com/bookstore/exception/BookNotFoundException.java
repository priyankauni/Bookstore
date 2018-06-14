package com.bookstore.exception;

/**
 * Represents the case that a Book could not be found
 * in the inventory.
 */
public class BookNotFoundException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String msg){
	super(msg);
    }
}
