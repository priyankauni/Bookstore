<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*"%>
<%@ include file="Header.jsp"%>
<%@ page import="com.bookstore.stock.BookStock, com.bookstore.models.Book"%>

<h1>
	Book confirmation. Success:
	<%=request.getParameter("success")%></h1>
<tbody>
	<tr style="text-align: left">
		<td>
			<li><a href="./Header.jsp">Continue to Books</a></li>
		</td>
	</tr>
</tbody>

<%@ include file="Footer.jsp"%>