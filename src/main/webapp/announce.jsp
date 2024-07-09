<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Announce | Book Store</title>
</head>
<body>

	<%
	
		if(session.getAttribute("user") == null){
			response.sendRedirect("login");
		}
	
	%>
	<form action="createbook" method="post">
		
		<h2 style="text-align: center;">Announce Your Book</h2>
		<div style="text-align: center;">
		
			<input type="text" name="title" placeholder="Book Title...">
			<input type="text" name="description" placeholder="Book Description..."><br>
			<br>
			<input type="number" step="0.01" name="price" placeholder="Book Price...">
			<input type="number" name="stock" placeholder="Stock..."><br>
			<br>
			<input type="text" name="image" placeholder="Cover Link..."><br>
			<br>
			<input type="submit">
		
		</div>
		
	</form>
</body>
</html>