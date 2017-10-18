<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department Entry</title>
</head>
<body>
<span>
	<% if((String) session.getAttribute("error") != null){
			out.println((String) session.getAttribute("error"));
		}
	%>
	</span>
	<h1>DEPARTMENT ENTRY</h1>
	<form method = "post" action = "DepartmentEntry">
		Department Name: <input type = "text" name ="deptName" /><br>
		Department Location/Floor: <input type = "text" name = "location" /><br>
		<input type = "submit" value = "Submit" />
		<input type = "reset" value = "Cancel" />
	</form>
</body>
</html>