<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="Header.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="cart" scope="session" class="com.bookstore.models.Cart"></jsp:useBean>
<!DOCTYPE jsp:useBean PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:setProperty name="cart" property="*"></jsp:setProperty>
 <%request.setCharacterEncoding("UTF-8"); 
		  response.setCharacterEncoding("UTF-8"); 
		  response.setContentType("text/html; charset=UTF-8"); 
		  %>	 		 
	<c:if test="${not empty cart.items}">
	<table class="table table-striped">
	<col width="300">
  	<col width="300">
  	<col width="300">
  	<col width="300">
		<thead>
			<th>Quantity</th>
			<th >Title</th>
			<th>Price</th>
			<th>Total Amount</th>
			<th></th>
		</thead>
		<tbody>
		<c:forEach var="item" items="${cart.items}">
		<form action="./CartUpdate" method="post">
		<tr style="text-align: left">
			<td> 
					<input type="hidden" name="isbn" value="${item.isbn}"/>
					<input type="number" size="2" name="quantity" min="1" value="${item.quantity}" oninput="return FilterInput(event)"/>  
					<input type="submit" value="Update" />
					
			</td>
			<td>${item.title}</td>
			<td>${item.price}</td>
			<td>${item.totalPrice}</td> 
			<td><input type="submit" name="remove" value="Remove"></td>
		</tr>
	</form>
	</c:forEach>
	<% String totalCartPrice = (String) (session.getAttribute("totalCartPrice"));%>
	<tr> <td>Total Cart Amount: <%=totalCartPrice %></td> </tr>
	</tbody>
	</table>
	<form action="./submitOrder" method="post">
		<input type="submit" class="btn btn-default" value="Checkout">
	</form>
	</c:if>
	<c:if test="${empty cart.items}">
		<h2>Cart is empty</h2> 
	</c:if>
<form action="./index.jsp" method="post">
	<input type="submit" class="btn btn-default" value="Continue Shopping">
</form>
<script type="text/javascript">
function FilterInput(event) {
    var keyCode = ('which' in event) ? event.which : event.keyCode;

    isNotWanted = (keyCode == 69 || keyCode == 101);
    return !isNotWanted;
};
</script>
 <%@ include file="Footer.jsp" %>