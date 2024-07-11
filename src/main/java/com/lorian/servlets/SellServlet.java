package com.lorian.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.lorian.DAOs.OrderDAO;
import com.lorian.DAOs.UserDAO;


@WebServlet("/sell")
public class SellServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Long user_id = Long.parseLong(request.getParameter("user_id"));
		
		
		try {
			Float balance = new UserDAO().getByUsername((String) session.getAttribute("user")).getBalance();
			
			if(balance > new OrderDAO().cartTotalPrice(user_id)) {
				new OrderDAO().confirmOrdersAndSell(user_id);
			}else {
				throw new Exception("You Don't Have Enough Funds.");
			}
		} catch (Exception e) {
			request.setAttribute("e", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		response.sendRedirect("mybooks.jsp");
		
	}
	
}
