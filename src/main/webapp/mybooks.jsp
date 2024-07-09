<%@page import="java.text.DecimalFormat"%>
<%@page import="com.lorian.DAOs.BookDAO"%>
<%@page import="com.lorian.models.User"%>
<%@page import="com.lorian.DAOs.OrderDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Books | Book Store</title>
<style type="text/css">

	.mybooks_div{
	
		border: 2px solid black;
		width: 150px;
		height: 200px;
		padding: 15px;
		text-align: center;
	}
	
	.mybooks_div:hover{
	
		background-color: black;
		color: white;
	
	}
	
	.mybooks_div:hover div{
	
		border: 2px solid white;
	
	}
	
	.mybooks_div div{
	
		border: 2px solid black;
		width: 145px;
		height: 100px;
		text-align: center;
		overflow: hidden;
	}
	
	.ul_mybooks li{
	
		display: inline-block;
	
	}
	
	.li_overflow{
	
		overflow: hidden;
	
	}

</style>
</head>
<body>
	<%! 
	
		OrderDAO dao = new OrderDAO();
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
	%>
	<%
		if(session.getAttribute("user") == null){
			response.sendRedirect("login");
		}else{
			
			session.setAttribute("decimalFormat", decimalFormat);
			session.setAttribute("sold", new OrderDAO());
			session.setAttribute("books", dao.findOrderedBooksByUsername((String) session.getAttribute("user")));
			session.setAttribute("my_books", new BookDAO().getByAuthor((String) session.getAttribute("user")));
		}
		
	%>
	<h2 style="text-align: center;">Library</h2>
	
	<section style="width: 1000px; margin: auto;">
		
		<ul class="ul_mybooks" style="list-style-type: none;">
	
			<c:forEach items="${books}" var="book">
					<li class="li_overflow">
						<div class="mybooks_div">
							<div><img alt="book" src="${book.getImage()}" width="100%" height="100%" ></div>
							<h5>${book.getTitle()}</h5>
						</div>
					</li>
			</c:forEach>
		
		</ul>
	
	</section>
	<h2 style="text-align: center;">Your Books</h2>
	
	<section style="width: 1000px; margin: auto;">
		
		<ul class="ul_mybooks" style="list-style-type: none;">
	
			<c:forEach items="${my_books}" var="book">
					<li class="li_overflow">
						<div class="mybooks_div">
							<div><img alt="book" src="${book.getImage()}" width="100%" height="100%" ></div>
							<h5>${book.getTitle()}</h5>
							<p>${sold.findOrdersByBookId(book.getId()).size()} sold</p>
							<p style="margin-top: -15px; font-size: 10px;">Profit : R$${decimalFormat.format(sold.findOrdersByBookId(book.getId()).size() * book.getPrice())}</p>
						</div>
					</li>
			</c:forEach>
		
		</ul>
	
	</section>
	
</body>
</html>