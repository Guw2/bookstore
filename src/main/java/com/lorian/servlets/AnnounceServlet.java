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
import com.lorian.DAOs.UserDAO;
import com.lorian.models.Book;
import com.lorian.models.User;

@WebServlet("/createbook")
public class AnnounceServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		Float price = Float.parseFloat(request.getParameter("price"));
		Long stock = Long.parseLong(request.getParameter("stock"));
		String image = request.getParameter("image");
		String author = (String) session.getAttribute("user");
		
		try {
			Book book = new Book(0L, title, description, price, stock, image, author);
			
			new BookDAO().insertBook(book);
			
			response.sendRedirect("store.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
