package com.lorian.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lorian.models.Book;
import com.lorian.models.Order;
import com.lorian.models.User;

import jakarta.servlet.http.HttpSession;

public class OrderDAO {
	
	public Order findById(Long id) throws SQLException, ClassNotFoundException {
		Connection connect = new ConnectToDatabase().connect();
		
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM orders WHERE id="+id);
		
		rs.next();
		
		Order order = new Order(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getInt(4));
		
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
		ResultSet rs = connect.createStatement().executeQuery("SELECT book_id,sold FROM orders WHERE user_id=" + user_id + ";");
		
		while(rs.next()) {
			if(rs.getInt(2) == 0) {
				listToReturn.add(new BookDAO().getById(rs.getLong(1)));
			}else {
				listToReturn.clear();
			}
		}
		
		connect.close();
		return listToReturn;
	}
	
	public Float cartTotalPrice(Long user_id) throws ClassNotFoundException, SQLException {
		List<Book> listOfOrderedBooks = findOrderedBooksByUserId(user_id);
		
		Float[] total = {0f};
		
		listOfOrderedBooks.forEach(x -> total[0] += x.getPrice());
		
		return total[0];
	}
	
	public void confirmOrdersAndSell(Long user_id) throws ClassNotFoundException, SQLException {
		Connection connect = new ConnectToDatabase().connect();
		
		new UserDAO().debit(new UserDAO().findById(user_id).getName(), new OrderDAO().cartTotalPrice(user_id));
		
		int i = 0;
		for(Book b : new OrderDAO().findOrderedBooksByUserId(user_id)) {
			new UserDAO().addToBalance(b.getAuthor(), b.getPrice());
			new BookDAO().stockDebit(b.getId());
			i++;
		}
		
		connect.createStatement().executeUpdate("UPDATE orders SET sold=1 WHERE user_id="+user_id+";");
		
		connect.close();
	}
	
	
	public List<Book> findSoldBooksByBookId(Long book_id) throws ClassNotFoundException, SQLException {
		
		List<Book> listToReturn = new ArrayList<Book>();
		
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM orders WHERE book_id=" + book_id + ";");
		
		while(rs.next()) {
			if(rs.getInt(4) == 1) {
				listToReturn.add(new BookDAO().getById(rs.getLong(3)));
			}
		}
		
		connect.close();
		return listToReturn;
	}
	
public List<Book> findSoldBooksByUsername(String username) throws ClassNotFoundException, SQLException {
		
		List<Book> listToReturn = new ArrayList<Book>();
		
		User user = new UserDAO().getByUsername(username);
		
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM orders WHERE user_id=" + user.getId() + ";");
		
		while(rs.next()) {
			if(rs.getInt(4) == 1) {
				listToReturn.add(new BookDAO().getById(rs.getLong(3)));
			}
		}
		
		connect.close();
		return listToReturn;
	}
	
	public List<Book> findOrderedBooksByUsername(String username) throws ClassNotFoundException, SQLException {
		
		User userModel = new UserDAO().getByUsername(username);
		List<Book> listToReturn = new ArrayList<Book>();
		
		Connection connect = new ConnectToDatabase().connect();
		ResultSet rs = connect.createStatement().executeQuery("SELECT book_id, sold FROM orders WHERE user_id=" + userModel.getId() + ";");
		
		while(rs.next()) {
			if(rs.getInt(2) == 0) {
				listToReturn.add(new BookDAO().getById(rs.getLong(1)));
			}
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
	
	public void deleteOrder(Long user_id, Long book_id) throws ClassNotFoundException, SQLException {
		
		Connection connect = new ConnectToDatabase().connect();
				
		connect.createStatement().executeUpdate("DELETE FROM orders WHERE user_id="+user_id+" AND book_id="+book_id+";");
		
		connect.close();
		
	}
}
