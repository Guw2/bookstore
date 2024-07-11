<%@page import="java.text.DecimalFormat"%>
<%@page import="com.lorian.DAOs.UserDAO"%>
<%@page import="com.lorian.DAOs.OrderDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart | Bookstore</title>

<style type="text/css">

	.main_section{
	
		border: 3px solid black;
		border-radius: 4px;
		width: 480px;
		margin: auto;
		padding: 0px 20px 20px 20px;
	
	}
	
	.order_div{
	
		border: 3px solid black;
		border-radius: 4px;
		width: 445px;
		padding: 15px;
	
	}
	
	.order_div ul {
	
		list-style: none;
		padding: 0;
		margin: 0;
		display: flex;
		align-items: center;
	
	}
	
	.x_button{
	
		background-color: red;
		color: white;
		text-align: center;
		border: 2px solid black;
		padding: 3px;
		width: 16px;
		height: 16px;
	
	}

</style>

</head>
<body>
	<%! 
	UserDAO userDao = new UserDAO();
	DecimalFormat decimalFormat = new DecimalFormat("#.00");
	%>
	<br>
	<section class="main_section">
		
		<% 
		
		if(session.getAttribute("user") == null){
			response.sendRedirect("login");
		}else{
			request.setAttribute("orders", orderDao.findOrderedBooksByUsername((String) session.getAttribute("user")));
			
			request.setAttribute("userModel", userDao.getByUsername((String) session.getAttribute("user")));
			
			request.setAttribute("decimalFormat", decimalFormat);
		}
			
			
		
		%>
		
		<h2 style="text-align: center;">Cart | Total :
		 
		<c:choose>
		
			<c:when test="${orderDao.cartTotalPrice(userModel.getId()) > 0}">
			
				R$${decimalFormat.format(orderDao.cartTotalPrice(userModel.getId()))}
			
			</c:when>
			<c:otherwise>
				
				R$0.00
				
			</c:otherwise>
		
		</c:choose>
		
		</h2>
		
		<c:forEach items="${orders}" var="order">
			<div class="order_div">
				<ul>
			        <li style="margin-right: 15px;">
			            <div style="border: 3px solid black; border-radius: 4px; width: 40px; height: 40px;">
			            	<img alt="book" style="width: 40px; height: 40px;" src="${order.getImage()}">
			            </div>
			        </li>
			        <li>
			            <div>
			                <span>${order.getTitle()} - R$${decimalFormat.format(order.getPrice())}</span>
			            </div>
			        </li>
			        <li style="margin-left: auto;">
			           <a href="order?book=${order.getId()}&delete=1" style="text-decoration: none;">
			               <div class="x_button">X</div>
			           </a>
			        </li>
			    </ul>
			</div>
			<br>
		
		</c:forEach>
		
		<c:if test="${orderDao.findOrderedBooksByUsername(user).size() > 0}">
			<div style="text-align: center;">
			  <a href="sell?user_id=${userModel.getId()}" style="
			    display: inline-block;
			    text-align: center;
			    border: 2px solid black;
			    border-radius: 3px;
			    width: 150px;
			    padding: 10px;
			    margin: auto;
			    text-decoration: none;
			    color: black;
			  ">
			    Purchase
			  </a>
			</div>
		</c:if>
	</section>
</body>
</html>
