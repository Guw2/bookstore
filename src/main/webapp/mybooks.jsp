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
	<%! DecimalFormat decimalFormat = new DecimalFormat("#.00"); %>
	<%
		if(session.getAttribute("user") == null){
			response.sendRedirect("login");
		}else{
			
			request.setAttribute("books", orderDao.findSoldBooksByUsername((String) session.getAttribute("user")));
			request.setAttribute("my_books", new BookDAO().getByAuthor((String) session.getAttribute("user")));
			request.setAttribute("decimalFormat", decimalFormat);
		}
		
	%>
	<h2 style="text-align: center;">Library</h2>
	
	<section style="width: 1000px; margin: auto;">
		
		<ul class="ul_mybooks" style="list-style-type: none;">
	
			<c:choose>
			
				<c:when test="${books.size() > 0}">
					<c:forEach items="${books}" var="book">
						<li class="li_overflow">
							<div class="mybooks_div">
								<div><img alt="book" src="${book.getImage()}" width="100%" height="100%" ></div>
								<h5>${book.getTitle()}</h5>
							</div>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
				
					<p style="text-align: center; margin-right: 35px;">Empty.</p>
				
				</c:otherwise>
			
			</c:choose>
			
		
		</ul>
	
	</section>
	<c:if test="${my_books.size() > 0}">
	
		<h2 style="text-align: center;">Your Books</h2>
		
		<section style="width: 1000px; margin: auto;">
			
			<ul class="ul_mybooks" style="list-style-type: none;">
		
				<c:forEach items="${my_books}" var="book">
						<li class="li_overflow">
							<div class="mybooks_div">
								<div><img alt="book" src="${book.getImage()}" width="100%" height="100%" ></div>
								<h5>${book.getTitle()}</h5>
								<p>${orderDao.findOrdersByBookId(book.getId()).size()} sold</p>
								<p style="margin-top: -15px; font-size: 10px;">Profit : R$${decimalFormat.format(orderDao.findOrdersByBookId(book.getId()).size() * book.getPrice())}</p>
							</div>
						</li>
				</c:forEach>
			
			</ul>
		
		</section>
	
	</c:if>
</body>
</html>