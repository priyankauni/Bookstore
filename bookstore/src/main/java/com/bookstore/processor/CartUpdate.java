package com.bookstore.processor;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;

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
 * Servlet implementation class CartUpdate
 */
@WebServlet("/CartUpdate")
public class CartUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartUpdate() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		Cart cart = (Cart) session.getAttribute("cart");;
		if(cart == null) {
			cart = new Cart();
		}
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		String isbn = request.getParameter("isbn");
		int quantityStr = Integer.parseInt(request.getParameter("quantity"));
		String removeButtonVal = request.getParameter("remove");
		String addButtonVal = request.getParameter("add");
		String quantityError = request.getParameter("quanErr");
		
		if(quantityError == null) {
		int quantity = 0;
		
		if (addButtonVal != null) {
			quantity = 1;
		} else if (removeButtonVal != null) {
			quantity = 0;
		} else {
			quantity = quantityStr;
		}
		
		LineItem lineItem = new LineItem();
		lineItem.setIsbn(isbn);
		lineItem.setQuantity(quantity);
		if (title != null) {
			lineItem.setTitle(title);
		}
		if (author != null) {
			lineItem.setAuthor(author);
		}
		
		cart.add(lineItem);
		 
		if(quantity > 0) {
				
			cart.add(lineItem);
		} else {
			cart.remove(lineItem);
		}
		}
		
		BigDecimal totalCartPrice = BigDecimal.ZERO;
		for (LineItem item : cart.getItems()) {
				try {
					totalCartPrice = totalCartPrice.add(item.getTotalPriceDouble());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (BookNotFoundException e) {
					e.printStackTrace();
				}
		}
		
		session.setAttribute("cart", cart);
		session.setAttribute("totalCartPrice", totalCartPrice.toString());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
		dispatcher.forward(request, response);
	}

}
