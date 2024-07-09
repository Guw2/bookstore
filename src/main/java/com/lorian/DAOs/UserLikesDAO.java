package com.lorian.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLikesDAO {
	
	public void likeAComment(Long comment_id, Long user_id) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		
		PreparedStatement ps = connect.prepareStatement("INSERT INTO user_likes (user_id, comment_id) VALUES (?, ?);");
		
		ps.setLong(1, user_id);
		ps.setLong(2, comment_id);
		
		ps.executeUpdate();
		
		connect.close();
	}
	
	public void dislikeAComment(Long comment_id, Long user_id) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		
		PreparedStatement ps = connect.prepareStatement("DELETE FROM user_likes WHERE user_id=? AND comment_id=?");
		
		ps.setLong(1, user_id);
		ps.setLong(2, comment_id);
		
		ps.executeUpdate();
		
		connect.close();
	}
	
	public boolean alreadyLiked(Long comment_id, Long user_id) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		
		ResultSet rs = connect.createStatement().executeQuery("SELECT user_id FROM user_likes WHERE comment_id = " + comment_id);
		
		while(rs.next()) {
			if(rs.getLong(1) == user_id) {
				connect.close();
				return true;
			}
		}
		connect.close();
		return false;
	}
	
}
