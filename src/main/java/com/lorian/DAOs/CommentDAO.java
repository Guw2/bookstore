package com.lorian.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lorian.models.Comment;

public class CommentDAO {

	public List<Comment> getAllCommentsFromABook(Long book_id) throws ClassNotFoundException, SQLException{
		Connection connect = new ConnectToDatabase().connect();
		
		List<Comment> listToReturn = new ArrayList<Comment>();
		
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM comment WHERE book_id = " + book_id);
		
		while(rs.next()) {
			listToReturn.add(new Comment(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getLong(5)));
		}
		
		connect.close();
		return listToReturn;
	}
	
	public void insertComment(Comment comment) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		
		PreparedStatement ps = connect.prepareStatement("INSERT INTO comment (user, likes, content, book_id) VALUES (?, ?, ?, ?);");
		
		ps.setString(1, comment.getUser());
		ps.setLong(2, comment.getLikes());
		ps.setString(3, comment.getContent());
		ps.setLong(4, comment.getBook_id());
		
		ps.executeUpdate();
		
		connect.close();
	}
	
	public void addLike(Long id) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT likes FROM comment WHERE id="+id);
		
		rs.next();
		
		Long likesNow = rs.getLong(1);
		
		connect.createStatement().executeUpdate("UPDATE comment SET likes=" + (likesNow+1) + " WHERE id="+id);
		
		connect.close();
	}
	
	public void removeLike(Long id) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT likes FROM comment WHERE id="+id);
		
		rs.next();
		
		Long likesNow = rs.getLong(1);
		
		connect.createStatement().executeUpdate("UPDATE comment SET likes=" + (likesNow-1) + " WHERE id="+id);
		
		connect.close();
	}
}
