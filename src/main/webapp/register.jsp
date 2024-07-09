<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body style="font-family: arial; zoom: 125%;">

	<div style="text-align: center;">
		<h1>Register Page</h1>
		<hr>
		<br>
		<form action="register" method="post">
			<input type="text" name="user" placeholder="username...">
			<input type="email" name="email" placeholder="e-mail..."><br>
			<br>
			<input type="text" name="register" placeholder="NIN (CPF)...">
			<input type="text" name="address" placeholder="address...">
			<br>
			<br>
			<input type="password" name ="pass" placeholder="password...">
			<input type="password" name ="cpass" placeholder="confirm password...">
			<br>
			<br>
			<input type="submit" value="Register">
		</form>
		<a style="font-size: 9px;" href="login">I Already Have An Account</a>
	</div>
</body>
</html>