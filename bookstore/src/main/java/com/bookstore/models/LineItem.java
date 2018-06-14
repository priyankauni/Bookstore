package com.bookstore.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.stock.BookStock;

public class LineItem implements Serializable {
		private int lineItemId;
		private String isbn;
		private int quantity;
		private String title;
		private String author;
		
		public LineItem(int lineItemId, String isbn, int quantity, String title, String author) {
			this.lineItemId = lineItemId;
			this.isbn = isbn;
			this.quantity = quantity;
			this.title = title;
			this.author = author;
		}

		public LineItem() {
			this.lineItemId = 0;
			this.isbn = "";
			this.quantity = 0;
			this.title = "";
			this.author = "";
		}

		public int getLineItemId() {
			return lineItemId;
		}

		public void setLineItemId(int lineItemId) {
			this.lineItemId = lineItemId;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
		
		public BigDecimal getPrice() throws NumberFormatException, BookNotFoundException {
			Book book = new BookStock().getBookByID((Integer.parseInt(isbn)));
			BigDecimal price = book.getPrice();
			return price;
		}
		
		public BigDecimal getTotalPrice() throws NumberFormatException, BookNotFoundException {
			Book book = new BookStock().getBookByID((Integer.parseInt(isbn)));
			BigDecimal price = book.getPrice().multiply(new BigDecimal(this.quantity));
			return price;
		}
		
		public BigDecimal getTotalPriceDouble() throws NumberFormatException, BookNotFoundException {
			Book book = new BookStock().getBookByID((Integer.parseInt(isbn)));
			BigDecimal price = book.getPrice().multiply(new BigDecimal(this.quantity));
			return price;
		}
		
		public boolean enoughInventory() throws NumberFormatException, BookNotFoundException {
			boolean retVal = true;
			BookStock stock = new BookStock();
			Book book = stock.getBookByID((Integer.parseInt(isbn)));
			if (!stock.stockContains(book)) {
				retVal = false;
			}
			return retVal;
		}
}
