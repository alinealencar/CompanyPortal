<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log In</title>
</head>
<body>
	<span>
	<% if((String) session.getAttribute("error") != null){
			out.println((String) session.getAttribute("error"));
		}
	%>
	</span>
	<form action="Login" method="post">
		User: <input type="text" name="username"/><br>
		Password: <input type="password" name="password"/><br>
		<input type="submit" value="submit">
	</form>
</body>
</html>