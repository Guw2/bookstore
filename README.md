Connection Name : bookstore


Tables : 

		 user (id INT, name STRING, email STRING, register STRING, address STRING, balance FLOAT, password STRING, role STRING)
   
		 book (id INT, title STRING, description STRING, price FLOAT, stock INT, product_image STRING, author STRING)
		 
		 orders (id INT, user_id INT, book_id INT)
		 
		 comment (id INT, user STRING, likes INT, content STRING, book_id INT)
		 
		 user_likes (id INT, user_id INT, comment_id INT)

