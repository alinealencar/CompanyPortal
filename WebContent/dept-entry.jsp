<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department Entry</title>
</head>
<%	Cookie[] cookies = request.getCookies(); 
	//String deptName = cookies[0].getValue();
	//String loc = cookies[1].getValue(); %>
<body>
	<h1>DEPARTMENT ENTRY</h1>
	<form method = "post" action = "DepartmentEntry">
		Department Name: <input type = "text" name ="deptName" value = <%cookies[0].getValue();%>/><br>
		<p>${deptNameError}</p>
		Department Location/Floor: <input type = "text" name = "location" value = <%cookies[1].getValue();%>/><br>
		<p>${deptLocError}</p>
		<input type = "submit" value = "Submit" />
		<input type = "reset" value = "Cancel" />
	</form>
</body>
</html>