package com.lorian.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lorian.models.Book;
import com.lorian.models.Order;
import com.lorian.models.User;

public class OrderDAO {
	
	public Order findById(Long id) throws SQLException, ClassNotFoundException {
		Connection connect = new ConnectToDatabase().connect();
		
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM orders WHERE id="+id);
		
		rs.next();
		
		Order order = new Order(rs.getLong(1), rs.getLong(2), rs.getLong(3));
		
		connect.close();
		return order;
	}
	
	public void assignOrderToUserById(Long user_id, Long book_id) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		
		connect.createStatement().executeUpdate("INSERT INTO orders (user_id, book_id) VALUES (" + user_id + ", " + book_id + ");");
		
		connect.close();
	}
	
	public List<Book> findOrderedBooksByUserId(Long user_id) throws ClassNotFoundException, SQLException {
		
		List<Book> listToReturn = new ArrayList<Book>();
		
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT book_id FROM orders WHERE user_id=" + user_id + ";");
		
		while(rs.next()) {
			listToReturn.add(new BookDAO().getById(rs.getLong(1)));
		}
		
		connect.close();
		return listToReturn;
	}
	
	public List<Book> findOrderedBooksByUsername(String username) throws ClassNotFoundException, SQLException {
		
		User userModel = new UserDAO().getByUsername(username);
		List<Book> listToReturn = new ArrayList<Book>();
		
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT book_id FROM orders WHERE user_id=" + userModel.getId() + ";");
		
		while(rs.next()) {
			listToReturn.add(new BookDAO().getById(rs.getLong(1)));
		}
		
		connect.close();
		return listToReturn;
	}
	
	public List<Order> findOrdersByBookId(Long id) throws ClassNotFoundException, SQLException {
		
		Book bookModel = new BookDAO().getById(id);
		List<Order> listToReturn = new ArrayList<Order>();
		
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT id FROM orders WHERE book_id=" + bookModel.getId() + ";");
		
		while(rs.next()) {
			listToReturn.add(findById(rs.getLong(1)));
		}
		
		connect.close();
		return listToReturn;
	}
}
