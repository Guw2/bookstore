package com.lorian.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.lorian.DAOs.CommentDAO;
import com.lorian.models.Book;
import com.lorian.models.Comment;

@WebServlet("/commentit")
public class CommentServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("user");
		String content = request.getParameter("comment");
		Book book = (Book) session.getAttribute("chosenBook");
		Long book_id = book.getId();
		
		try {
			new CommentDAO().insertComment(new Comment(0L, user, 0L, content, book_id));
			response.sendRedirect("item.jsp");
		} catch (Exception e) {
			request.setAttribute("e", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} 
		
	}

}
