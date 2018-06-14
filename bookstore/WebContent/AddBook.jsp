<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>
<h1>Add a new book!</h1>

<form action="./AddBook" method="post" >
	
	<p>
	<label for="title">Title:</label>
	<input type="text" name="title" id="title" required />
	</p>

	<p>
	<label for="author">Author:</label>
	<input type="text" name="author" id="author" required />
	</p>

	<p>
	<label for="price">Price:</label>
	<input type="text" name="price" id="price" required />
	</p>

	<p>
	<label for="quantity">Quantity:</label>
	<input type="text" name="quantity" id="quantity" required />
	</p>
	
	<input type="submit" />
</form>

<%@ include file="Footer.jsp" %>