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

@WebServlet("/item")
public class ItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookDAO dao = new BookDAO();
		
		Long id = Long.parseLong(request.getParameter("product"));
		
		HttpSession session = request.getSession();
		
		try {
			session.setAttribute("chosenBook", dao.getById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("item.jsp");
	}

}
