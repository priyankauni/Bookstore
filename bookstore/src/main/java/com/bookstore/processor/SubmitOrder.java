package com.bookstore.processor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.models.Book;
import com.bookstore.models.Cart;
import com.bookstore.models.LineItem;
import com.bookstore.stock.BookStock;

/**
 * Servlet implementation class SubmitOrder
 */
@WebServlet("/SubmitOrder")
public class SubmitOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if (cart == null) {
			response.sendError(404);
			return;
		}
		ArrayList<LineItem> lineItems = cart.getItems();
		Book book = null;
		String title = "";
		String author = "";
		BigDecimal totalPrice = BigDecimal.ZERO;
		List<Book> bookArr = new ArrayList<>();
		for(LineItem lineitem : lineItems) {
			 title = lineitem.getTitle();
			 author = lineitem.getAuthor();
			 try {
				try {
					totalPrice = lineitem.getTotalPriceDouble();
				} catch (BookNotFoundException e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			 book = new Book(title, author, totalPrice);
			 bookArr.add(book);
		}
		Book[] books = bookArr.toArray(new Book[bookArr.size()]);

		BookStock stock = new BookStock();
		// Get the status from buy
		Map<String, String> orderMap = new HashMap<>();
		for(int i = 0; i<lineItems.size(); i++) {
			String bookStatus = Arrays.toString(stock.buy(books[i]));
			if(bookStatus.equalsIgnoreCase("[0]"))
				bookStatus = "OK"+bookStatus;
			if(bookStatus.equalsIgnoreCase("[1]"))
				bookStatus = "NOT_IN_STOCK"+bookStatus;
			if(bookStatus.equalsIgnoreCase("[2]"))
				bookStatus = "DOES_NOT_EXIST"+bookStatus;
			orderMap.put(lineItems.get(i).getTitle(), bookStatus);
		}
		
		cart.removeAllItems();
		
		request.setAttribute("orderMap", orderMap);
		request.setAttribute("totalPrice", totalPrice);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderConfirmation.jsp");
        dispatcher.forward(request, response);
	}
}
