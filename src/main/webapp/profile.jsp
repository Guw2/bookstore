<%@page import="java.text.DecimalFormat"%>
<%@page import="com.lorian.models.User"%>
<%@page import="com.lorian.DAOs.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile | Book Store</title>
</head>
<body>
	<h2 style="text-align:center;">Your Information</h2>
	<%! 
		UserDAO dao = new UserDAO();
	%>
	
	<%
	
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");	
		
		if(session.getAttribute("user") == null){
			response.sendRedirect("login");
		}else{
			User userModel = dao.getByUsername((String) session.getAttribute("user"));
			request.setAttribute("user_model", userModel);
		}
		
	%>
	
	<div style="width:500px; margin: auto; text-align: center;">
		Username<br>
		<input disabled="disabled" type="text" value="${user_model.getName()}"><br>
		<br>
		E-mail<br>
		<input disabled="disabled" type="text" value="${user_model.getEmail()}"><br>
		<br>
		NIN (CPF)<br>
		<input disabled="disabled" type="text" value="${user_model.getRegister()}"><br>
		<br>
		Address<br>
		<input disabled="disabled" type="text" value="${user_model.getAddress()}"><br>
		<br>
		Balance
		<div style="border: 2px solid green; width: 150px; margin-left: 174px;">R$${decimalFormat.format(user_model.getBalance())}</div><br>
		
		<!--<a href="" style="text-decoration: none; border: 2px solid white;
		border-radius: 4px; padding: 7px; background-color: green; color: white;">Deposite</a>-->
		
	</div>
</body>
</html>