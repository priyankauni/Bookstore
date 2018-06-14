<%@page import="com.bookstore.models.LineItem"%>
<%@ include file="Header.jsp" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<div class="jumbotron">
<table class="table table-striped">
	<col width="300">
  	<col width="300">
  	<col width="300">
  	<col width="300">
		<thead>
			<th>Book</th>
			<th>Status</th>
		</thead>
		<tbody>
		<% Map<String, String> orderMap =  (HashMap<String,String>)(request.getAttribute("orderMap"));
		Set<String> keySet = orderMap.keySet();
		Iterator<String> keySetIterator = keySet.iterator();
		while (keySetIterator.hasNext()) {
	  	 String key = keySetIterator.next();
		  %>
		<tr style="text-align: left">
			<td><%=key%></td>
			<td><%=orderMap.get(key)%></td>
		</tr>
	</tbody>
	<%} %>
	</table> 
</div>

<%@ include file="Footer.jsp" %>