package com.lorian.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lorian.models.Book;

public class BookDAO {
	
	public List<Book> getAll() throws ClassNotFoundException, SQLException{
		Connection connect = new ConnectToDatabase().connect();
		List<Book> listToReturn = new ArrayList<Book>();
		
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM book;");
		
		while(rs.next()) {
			
			listToReturn.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(3),
					rs.getFloat(4), rs.getLong(5), rs.getString(6), rs.getString(7)));
			
		}
		
		connect.close();
		return listToReturn;
		
	}
	
	public Book getById(Long id) throws SQLException, ClassNotFoundException {
		
		Connection connect = new ConnectToDatabase().connect();
		
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM book WHERE id='" + id + "';");
		
		rs.next();
		
		Book book = new Book(rs.getLong(1), rs.getString(2), rs.getString(3), 
				rs.getFloat(4), rs.getLong(5), rs.getString(6), rs.getString(7));
		
		connect.close();
		return book;
	}
	
public List<Book> getByAuthor(String author) throws SQLException, ClassNotFoundException {
		
		Connection connect = new ConnectToDatabase().connect();
		
		List<Book> listToReturn = new ArrayList<Book>();
		
		ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM book WHERE author ='" + author + "';");
		
		while(rs.next()) {
			listToReturn.add(new Book(rs.getLong(1), rs.getString(2), rs.getString(3), 
					rs.getFloat(4), rs.getLong(5), rs.getString(6), rs.getString(7)));
		}
		
		connect.close();
		return listToReturn;
	}
	
	public void insertBook(Book book) throws ClassNotFoundException, SQLException {
		
		Connection connect = new ConnectToDatabase().connect();
		
		PreparedStatement ps = connect.prepareStatement("INSERT INTO book (title, description, price, stock, product_image, author) VALUES (?, ?, ?, ?, ?, ?);");
		
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getDescription());
		ps.setFloat(3, book.getPrice());
		ps.setLong(4, book.getStock());
		ps.setString(5, book.getImage());
		ps.setString(6, book.getAuthor());
		
		ps.executeUpdate();
		
		connect.close();
	}
	
	public void stockDebit(Long id) throws ClassNotFoundException, SQLException {
		
		Connection connect = new ConnectToDatabase().connect();
		Book book = getById(id);
		Long stockNow = book.getStock();
		
		connect.createStatement().executeUpdate("UPDATE book SET stock = " + (stockNow-1) + " WHERE id = " + id);
		
		connect.close();
	}
}
