<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home | Book Store</title>
</head>
<body>
	<%
	
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");	
		
		if(session.getAttribute("user") == null){
			response.sendRedirect("login");
		}
	
	%>
</body>
</html>