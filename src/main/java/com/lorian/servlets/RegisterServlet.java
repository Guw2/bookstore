package com.lorian.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.lorian.DAOs.UserDAO;
import com.lorian.models.User;
import com.lorian.models.enums.Role;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	RequestDispatcher rd;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("user");
		String email = request.getParameter("email");
		String nin = request.getParameter("register");
		String address = request.getParameter("address");
		String password = "";
		
		UserDAO dao = new UserDAO();
		
		if(username == "" || email == "" || nin == "" || address == "") {
			rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			throw new NullPointerException();
		}else {
			if(request.getParameter("pass").equals(request.getParameter("cpass"))) {
				password = BCrypt.hashpw(request.getParameter("pass"), BCrypt.gensalt());
				User user = new User(0L, username, email, nin, address, 0f, password, Role.USER);
				try {
					dao.insertUser(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect("login");
				
			}else {
				rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
				throw new IllegalArgumentException();
			}
		}
		
	}
	
}
