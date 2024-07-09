<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.lorian.models.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.lorian.DAOs.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Store | Book Store</title>
<style type="text/css">

	.store_div{
	
		border: 2px solid black;
		width: 150px;
		height: 200px;
		padding: 15px;
		text-align: center;
	}
	
	.store_div:hover{
	
		background-color: black;
		color: white;
	
	}
	
	.store_div:hover div{
	
		border: 2px solid white;
	
	}
	
	.store_div div{
	
		border: 2px solid black;
		width: 145px;
		height: 100px;
		text-align: center;
		
	}
	
	.ul_store li{
	
		display: inline-block;
		color: black;
		
	}
	
	.li_overflow{
	
		overflow: hidden;
	
	}

</style>

</head>
<body>

	<h2 style="text-align:center;">Catalogue</h2>

	<%! 
		BookDAO dao = new BookDAO(); 
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
	%>

	<%
		
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");	
		
		if(session.getAttribute("user") == null){
			response.sendRedirect("login");
		}else{
			session.setAttribute("books", dao.getAll());
			session.setAttribute("decimalFormat", decimalFormat);
		}

	%>
	
	<section style="width: 1000px; margin: auto;">
	
		<ul class="ul_store" style="list-style-type: none;">
	
			<c:forEach items="${books}" var="book">
				<a href="item?product=${book.getId()}">
					<li class="li_overflow">
						<div class="store_div">
							<div><img alt="book" src="${book.getImage()}" width="100%" height="100%" ></div>
							<h5>${book.getTitle()}</h5>
							<p style="font-size: 15px;">R$${decimalFormat.format(book.getPrice())}</p>
						</div>
					</li>
				</a>
			</c:forEach>
		
		</ul>
	
	</section>
	
	<div style="text-align: center;">
		<a href="announce.jsp" style="border: 2px solid black; border-radius: 3px; padding: 5px; text-decoration: none; color: black;">Announce</a>	
	</div>
	
	<%
	
		session.removeAttribute("books");
	
	%>
	
</body>
</html>