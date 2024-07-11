<%@page import="java.text.DecimalFormat"%>
<%@page import="com.lorian.DAOs.UserLikesDAO"%>
<%@page import="com.lorian.models.User"%>
<%@page import="com.lorian.DAOs.UserDAO"%>
<%@page import="com.lorian.models.Comment"%>
<%@page import="com.lorian.DAOs.CommentDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.lorian.models.Book"%>
<%@page import="com.lorian.DAOs.OrderDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${chosenBook.getTitle()} | Book Store</title>
<style type="text/css">

	.main_section{
	
		border: 3px solid black;
		border-radius: 8px;
		padding: 15px;
		width: 700px;
		margin: auto;
	}
	
	.main_section .buy{
	
		width: 80px;
		height: 18px;
		border: 3px solid black;
		border-radius: 8px;
		padding: 10px;
		text-align: center;
		color: black;
	}
	
	.comment_section{
	
		border: 3px solid black;
		border-radius: 8px;
		padding: 15px;
		width: 700px;
		margin: auto;
	}
	
	.comment_section div{
	
		border: 2px solid black;
		border-radius: 8px;
		padding: 15px;
	}

</style>
</head>
<body>
	<section class="main_section">
		<%! DecimalFormat decimalFormat = new DecimalFormat("#.00");  %>
		<%
		
			if(session.getAttribute("user") == null){
				response.sendRedirect("login");
			}else{
				Book book = (Book) session.getAttribute("chosenBook");
				
				User user = new UserDAO().getByUsername((String) session.getAttribute("user"));
				
				List<Comment> comments = new CommentDAO().getAllCommentsFromABook(book.getId());
				
				List<Book> userBooks = orderDao.findSoldBooksByUsername((String) session.getAttribute("user"));
				
				request.setAttribute("book", book);
				request.setAttribute("alreadyLiked", new UserLikesDAO());
				request.setAttribute("userModel", user);
				request.setAttribute("comments", comments);
				request.setAttribute("contains", userBooks.contains(book));
				request.setAttribute("decimalFormat", decimalFormat);
			}
		
		%>
	
		<img alt="book" src="${chosenBook.getImage()}" style="border: 2px solid black;
		width: 315px; height: 315px;">
		<h2>${chosenBook.getTitle()}</h2>
		
		<h4 style="margin-left: 5px;">R$${decimalFormat.format(chosenBook.getPrice())} | ${chosenBook.getStock()} in Stock</h4>
		
		<h5>by ${chosenBook.getAuthor()} | ${orderDao.findSoldBooksByBookId(chosenBook.getId()).size()} sold</h5>
		
		<c:if test="${!chosenBook.getAuthor().equals(user) && !contains}">
			<a style="text-decoration: none;" href="order?book=${chosenBook.getId()}" class="buy">Order Now</a>
			
		</c:if>
		
		
		<p style="margin-top: 35px;">${chosenBook.getDescription()}</p>
		
	</section>
	<br>
	<section class="comment_section">
	
		<form action="commentit" method="post">
		
			<textarea name="comment" placeholder="Your comment..." rows="2" cols="83"></textarea>
			<input type="submit" value="send" style="position: absolute; margin-top: 10px; margin-left: 10px;">
		</form>
		
		<c:forEach items="${comments}" var="comment">
			
			<c:choose>
			
				<c:when test="${chosenBook.getAuthor().equals(comment.getUser())}">
				
				<p style="color: green; font-style: oblique; margin-bottom: 3px; margin-left: 3px;">@${comment.getUser()}</p>
				
				</c:when>
				<c:otherwise>
					<p style="font-style: oblique; margin-bottom: 3px; margin-left: 3px;">@${comment.getUser()}</p>
				</c:otherwise>
			
			</c:choose>
			
			<div>
			
				${comment.getContent()}
				<br>
				<br>
				<c:choose>
				
					<c:when test="${!alreadyLiked.alreadyLiked(comment.getId(), userModel.getId())}">
					
						<a style="border: 1px solid black; border-radius: 3px; padding: 3px; margin-right: 5px; padding: 3px 0px 3px 3px; text-align: center; text-decoration: none;" href="likeit?comment_id=${comment.getId()}&user_id=${userModel.getId()}&like=1">
							Like
						</a>
					
					</c:when>
					<c:otherwise>
						<a style="border: 1px solid black; text-decoration: none; border-radius: 3px; padding: 3px 0px 3px 3px; margin-right: 5px; text-align: center; background-color: red; color: white;" href="likeit?comment_id=${comment.getId()}&user_id=${userModel.getId()}&like=0">
							Like
						</a>
					</c:otherwise>
				
				</c:choose>
				${comment.getLikes()}
				
			</div>
			<br>
		</c:forEach>
	</section>
	
</body>
</html>