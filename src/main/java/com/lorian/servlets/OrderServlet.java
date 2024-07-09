package com.lorian.servlets;

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
		
		try {
			Book book = (Book) session.getAttribute("chosenBook");
			User user = userDao.getByUsername((String) session.getAttribute("user"));
			
			Long book_id = book.getId();
			Long user_id = user.getId();
			
			if(user.getBalance() - book.getPrice() > 0) {
				userDao.debit(user.getName(), book.getPrice());
				userDao.addToBalance(book.getAuthor(), book.getPrice());
				new BookDAO().stockDebit(book.getId());
				
				new OrderDAO().assignOrderToUserById(user_id, book_id);
				
				response.sendRedirect("mybooks.jsp");
			}else {
				throw new Exception("You don't have enough funds.");
			}
			
		} catch (Exception e) {
			request.setAttribute("e", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		
	}


}
