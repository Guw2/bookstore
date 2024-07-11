package com.lorian.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.lorian.models.User;
import com.lorian.models.enums.Role;

public class UserDAO {
	
	public void insertUser(User user) throws SQLException, ClassNotFoundException {
		Connection connect = new ConnectToDatabase().connect();
		PreparedStatement ps = connect.prepareStatement("INSERT INTO user (name, email, register, address, balance, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getRegister());
		ps.setString(4, user.getAddress());
		ps.setFloat(5, user.getBalance());
		ps.setString(6, user.getPassword());
		ps.setString(7, user.getRole().name());
		
		ps.executeUpdate();
		
		connect.close();
	}
	
	public boolean validateUser(String username, String password) throws ClassNotFoundException, SQLException {
		
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT password FROM user WHERE name = '" + username + "';");
		
		if(rs.next()) {
			if(BCrypt.checkpw(password, rs.getString(1))) {
				connect.close();
				return true;
			}else {
				connect.close();
				return false;
			}
		}else {
			connect.close();
			return false;
		}
	}
	
	public User getByUsername(String user) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM user WHERE name = '" + user + "';");
		rs.next();
		
		Long id = rs.getLong(1);
		String name = rs.getString(2);
		String email = rs.getString(3);
		String register = rs.getString(4);
		String address = rs.getString(5);
		Float balance = rs.getFloat(6);
		String password = rs.getString(7);
		String role = rs.getString(8);
		
		User userToReturn = new User(id, name, email, register, address, balance, password, Role.valueOf(role));
		
		connect.close();
		return userToReturn;
	}
	
	public User findById(Long user_id) throws SQLException, ClassNotFoundException {
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM user WHERE id = " + user_id + ";");
		rs.next();
		
		Long id = rs.getLong(1);
		String name = rs.getString(2);
		String email = rs.getString(3);
		String register = rs.getString(4);
		String address = rs.getString(5);
		Float balance = rs.getFloat(6);
		String password = rs.getString(7);
		String role = rs.getString(8);
		
		User userToReturn = new User(id, name, email, register, address, balance, password, Role.valueOf(role));
		
		connect.close();
		return userToReturn;
	}
	
	public void debit(String username, Float value) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		
		Float balanceNow = getByUsername(username).getBalance();
		
		connect.createStatement().executeUpdate("UPDATE user SET balance = " + (balanceNow - value) + " WHERE name = '" + username + "';");
		
		connect.close();
	}
	
	public void addToBalance(String username, Float value) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		
		Float balanceNow = getByUsername(username).getBalance();
		
		connect.createStatement().executeUpdate("UPDATE user SET balance = " + (balanceNow + value) + " WHERE name = '" + username + "';");
		
		connect.close();
	}
}




