package com.lorian.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.lorian.DAOs.CommentDAO;
import com.lorian.DAOs.UserLikesDAO;

@WebServlet("/likeit")
public class LikeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long comment_id = Long.parseLong(request.getParameter("comment_id"));
		Long user_id = Long.parseLong(request.getParameter("user_id"));
		Integer likeOrDislike = Integer.parseInt(request.getParameter("like"));
		
		try {
			if(likeOrDislike == 1) {
				new CommentDAO().addLike(comment_id);
				new UserLikesDAO().likeAComment(comment_id, user_id);
				response.sendRedirect("item.jsp");
			}else {
				new CommentDAO().removeLike(comment_id);
				new UserLikesDAO().dislikeAComment(comment_id, user_id);
				response.sendRedirect("item.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
