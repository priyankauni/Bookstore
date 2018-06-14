package com.bookstore.stock;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static com.bookstore.admin.BookDataAccess.INVENTORY_FILE;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.models.Book;
import com.bookstore.models.BookList;

public class BookStock implements BookList {

	private static class BookFileWriter {
		private void writeBooks(Map<Integer, BookStockEntry> stock) {
			try {
				StringBuilder sb = new StringBuilder();
				for (Integer id : stock.keySet()) {
					BookStockEntry se = stock.get(id);
					String line = se.book().toString() + ";" + se.numberInStock();
					sb.append(line).append("\n");
				}
				Path file = Paths.get(INVENTORY_FILE);
				Files.write(file, sb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
			} catch (IOException e) {
				System.err.println("Fatal: could not save inventory: " + e.getMessage());
			}
		}
	}

	private static class BookFileReader {
		private Map<Integer, BookStockEntry> readAllBooks() {
			 Map<Integer, BookStockEntry> books = new HashMap<>();
			    Set<String>linesRead = new HashSet<>(); // To detect duplicate rows
			    try{
				int bookId = 0;
				List<String>lines=Files.lines(Paths.get(INVENTORY_FILE), StandardCharsets.UTF_8).collect(Collectors.toList());
				for(String line : lines){
					if(linesRead.contains(line)){
					System.err.println("Warning: Ignoring duplicate line: " + line);
					continue;
				    }
				    linesRead.add(line);
				    String title = line.split(";")[0];
				    String author= line.split(";")[1];
				    String price = line.split(";")[2];
				    String num   = line.split(";")[3];
				    bookId++; // Id starts at 1;
					try {
						NumberFormat format = NumberFormat.getInstance(Locale.US);
						Number number = format.parse(price);
						double p = number.doubleValue();
						Book b = new Book(title, author, new BigDecimal(p).setScale(2, BigDecimal.ROUND_HALF_UP));
						BookStockEntry se = new BookStockEntry(b, Integer.parseInt(num));
						books.put(new Integer(bookId), se);
					} catch (NumberFormatException | java.text.ParseException | ArrayIndexOutOfBoundsException e) {
						System.err.println("Warning: could not parse INVENTORY_FILE: " + e.getMessage());
						System.err.println("Bad line was: " + line);
					}
				}
			    }catch(IOException e){
				System.err.println("Error reading inventory file: " + e.getMessage());
			    }
			    return books;
		}
	}

	public Map<Integer, BookStockEntry> booksMap;

	/**
	 * Creates a new FileStock (which reads the Books from the inventory upon
	 * creation).
	 */
	public BookStock() {
		this.booksMap = new BookFileReader().readAllBooks();
	}

	/**Returns an array of element type Book matching the 
     * search string parameter. Or all Books, if no search
     * string is given 
	 * @param searchString The String to search for, or null
     * signifying "all books".
     * @return An array of element type Book with the books matching
     * the search string on titles or authors.
	 */
	
	public Book[] list(String searchString) {
		List<Book> result = new ArrayList<>();
		if (searchString == null|| searchString == "" ) {
			for (BookStockEntry se : booksMap.values()) {
				se.book().getPrice().toString();
				result.add(se.book());
			}
			return result.toArray(new Book[0]);
		}
		for (BookStockEntry se : booksMap.values()) {
			if (se.book().getAuthor().toLowerCase().startsWith(searchString.toLowerCase())
					|| se.book().getTitle().toLowerCase().startsWith(searchString.toLowerCase())) {
				result.add(se.book());
			}
		}
		// The interface BookList required a return type using an Array,
		// so, let's convert the list to an array.
		return result.toArray(new Book[0]);
	}

	@Override
	public Book getBookByID(int bookID) throws BookNotFoundException {
		if (!booksMap.containsKey(new Integer(bookID))) {
			throw new BookNotFoundException("Can't find book with ID " + bookID);
		}
		return booksMap.get(bookID).book();
	}

	@Override
	public int getID(Book book) throws BookNotFoundException {
		for (Integer id : booksMap.keySet()) {
			if (booksMap.get(id).book().equals(book)) {
				return id.intValue();
			}
		}
		throw new BookNotFoundException("Couldn't find " + book + " in the stock.");
	}

	
	public boolean add(Book book, int quantity) {
		BookStockEntry bse = new BookStockEntry(book, quantity);
		booksMap.put(booksMap.size() + 1, bse);
		new BookFileWriter().writeBooks(booksMap);
		return true;
	}

	public boolean stockContains(Book b) {
		if (b == null) {
			return false;
		}
		for (BookStockEntry bse : booksMap.values()) {
			if (b.equals(bse.book())) {
				return true;
			}
		}
		return false;
	}

	public BookStockEntry getStockEntry(Book b) {
		for (BookStockEntry bse : booksMap.values()) {
			if (bse.book().equals(b)) {
				return bse;
			}
		}
		return null; 
	}

	@Override
	public int[] buy(Book... books) {
		final int OK = 0;
		final int NOT_IN_STOCK = 1;
		final int DOES_NOT_EXIST = 2;
		int[] statuses = new int[books.length];
		int index = 0;
		/*
		 * The books HashMap has the IDs as keys, and StockEntry as values
		 */
		for (Book book : books) {
			if (stockContains(book)) {
				BookStockEntry se = getStockEntry(book);
				// Book exists, so check quantity
				if (se.numberInStock() != 0) {
					statuses[index] = OK;
					// decrease quantity in stock
					se.decreaseStock();
					// This would be a good place to call
					// some other module, like PlaceOrder/Payment or so.
				} else {
					statuses[index] = NOT_IN_STOCK;
				}
			} else {
				// Unclear how someone tried to buy a non-existing
				// book, but if they do, this is what happens ;-)
				statuses[index] = DOES_NOT_EXIST;
			}
			index++;
		}
		return statuses;
	}
	

}
