package com.bookstore.models;

public interface BookList {
	
	 public Book[] list(String searchString);
	 public boolean add(Book book, int quantity);
	 public int[] buy(Book... books);
	 
	  /**
	     * Returns the ID of a book. It is implied that books carry
	     * an internal ID in the inventory.
	     * @param b The book for which you want to look up the inventory ID
	     * @return The ID of the book, as an int - the inventory is assumed
	     * to keep IDs for all the books.
	     * @throws com.bookstore.exception.BookNotFoundException; if the Book is not
	     * found the inventory.
	     */
	 
	 public int getID(Book b) throws com.bookstore.exception.BookNotFoundException;
	    /**
	     * Returns the book with the given ID. A Convenience method for
	     * looking up a book from an ID.
	     * @param id the ID for which you want to look up the Book
	     * @return the Book with this ID
	     * @throws com.bookstore.exception.BookNotFoundException; if the ID is not known
	     * by the inventory.
	     */
	    public Book getBookByID(int id) throws com.bookstore.exception.BookNotFoundException;

}
