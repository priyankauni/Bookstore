<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>
<%@ page import="java.util.*, com.bookstore.models.Book, com.bookstore.stock.BookStock" %>
<div class="container">
	<table class="table">
		<thead>
			<th>Add to Cart</th>
			<th>Title</th>
			<th>Author</th>
			<th>Price</th>
		</thead>
		<tbody>
		
		  <% 
		  request.setCharacterEncoding("UTF-8"); 
		  response.setCharacterEncoding("UTF-8"); 
		  response.setContentType("text/html; charset=UTF-8"); 
		  BookStock stock = new BookStock();
		 	Book[] books = (Book[]) request.getAttribute("books");
		 	if(books.length > 0) { 
		 		for(Book book : books)	 {
		 			%>
			<tr style="text-align: left"> 
			<td>
				<form action="./CartUpdate" method="post">
					 <input type="hidden" name="title" id="title" value="<%=book.getTitle() %>" />
					<input type="hidden" name="author" id="author" value="<%=book.getAuthor() %>"/>
					<input type="hidden" name="price" id="price" value="<%=book.getPrice() %>" />
					<input type="hidden" name="isbn" id="isbn" value="<%=stock.getID(book) %>" />
					<input type="hidden" name="quantity" id="quantity" value="1"/>
					<input type="submit" value="Add" />
				</form>

			</td>
			<td><%=book.getTitle() %></td>
        	<td><%=book.getAuthor() %></td>
        	<td><%=book.getPrice() %></td>
        	</tr>
    	<%  } 
    	 %> 
    	</tbody>
	</table>
	<%} else {%>
    	 <div class="alert alert-danger" style="max-width: 330px; margin: 0 auto" role="alert">No search results</div>
    	 <%}%> 
	</div>
<%@ include file="Footer.jsp" %>