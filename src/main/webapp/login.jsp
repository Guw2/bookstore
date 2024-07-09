<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body style="font-family: arial; zoom: 125%;">
	<div style="text-align: center;">
		<h1>Login Page</h1>
		<hr>
		<br>
		<form action="login" method="post">
			<input type="text" name="user" placeholder="username..."><br>
			<br>
			<input type="password" name="pass" placeholder="password..."><br>
			<br>
			<input type="submit" value="Login">
		</form>
		<a style="font-size: 9px;" href="register">I Don't Have an Account</a>
	</div>
</body>
</html>