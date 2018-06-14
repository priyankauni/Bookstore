<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>

<%@ page
	import="com.bookstore.admin.BookDataAccess, com.bookstore.models.Book, com.bookstore.stock.BookStock, java.text.NumberFormat"%>
<head>
<title>ATA!</title>
</head>
<h1>ATA: All The Awesome!</h1>

<div class="row">

	<table class="table">
		<thead>
			
		</thead>
		<tbody>
		
<%-- 		 <% for(Book book : books) { --%>
			<tr style="text-align: left"> 
			<td>
				<form action="./Search.jsp" method="post">
				
					<%-- <input type="hidden" name="title" id="title" value="${book.title}" />
					<input type="hidden" name="price" id="price" value="${book.price}" />
					<input type="submit" value="View" /> --%>
				</form>
			</td>
        	</tr>
        <%-- 	<tr><td>
<%=  request.getAttribute("books")  %>
</td></tr> --%>
    	<%-- <%} %> --%>
    	
    	</tbody>
	</table>
</div>

<%@ include file="Footer.jsp"%>