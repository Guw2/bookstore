package com.lorian.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.lorian.DAOs.BookDAO;
import com.lorian.DAOs.OrderDAO;
import com.lorian.DAOs.UserDAO;
import com.lorian.models.Book;
import com.lorian.models.User;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserDAO userDao = new UserDAO();
		
		String delete = request.getParameter("delete");
		
		try {
			Book book = new BookDAO().getById(Long.parseLong(request.getParameter("book")));
			User user = userDao.getByUsername((String) session.getAttribute("user"));
			
			Long book_id = book.getId();
			Long user_id = user.getId();
			RequestDispatcher rd;
			
			if(delete == null) {
				new OrderDAO().assignOrderToUserById(user_id, book_id);
				rd = request.getRequestDispatcher("cart.jsp");
			}else {
				new OrderDAO().deleteOrder(user_id, book_id);
				rd = request.getRequestDispatcher("cart.jsp");
			}
			
			rd.forward(request, response);
		}catch(Exception e) {
			request.setAttribute("e", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}


}
