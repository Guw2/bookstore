<%@page import="com.lorian.DAOs.BookDAO"%>
<%@page import="com.lorian.DAOs.OrderDAO"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">

	.ul {
		text-align: center;
		padding: 0;
		list-style-type: none;
	}
	
	.ul li {
		display: inline-block;
		border-radius: 5px;
	}
	
	.ul div{
		width: 90px;
		margin: 10px;
		padding: 10px;
		background-color: rgb(210, 210, 210);
	}
	
	.ul div:hover{
		background-color: rgb(190, 190, 190);
	}
	
	.ul a{
		text-decoration: none;
		color: black;
	}

</style>

</head>
<body style="font-family: arial; zoom: 120%;">

	<h1 style="text-align: center;">Book Store</h1>
	<%! 
	
	DecimalFormat decimalFormat = new DecimalFormat("#.00"); 
	OrderDAO orderDao = new OrderDAO();
	BookDAO bookDao = new BookDAO();
	
	%>
	
	<% 
	
		request.setAttribute("decimalFormat", decimalFormat);
		request.setAttribute("orderDao", orderDao);
		
	
	%>
	
	<header>
	
		<ul class="ul">
			<li><a href="home.jsp"><div>Home</div></a></li>
			<li><a href="store.jsp"><div>Store</div></a></li>
			<li><a href="profile.jsp"><div>Profile</div></a></li>
			<li><a href="mybooks.jsp"><div>My Books</div></a></li>
			<li><a href="cart.jsp"><div>Cart</div></a></li>
			<li><a href="logout"><div>Logout</div></a></li>
		</ul>
		
	</header>
	
	<hr>
	
</body>
</html>