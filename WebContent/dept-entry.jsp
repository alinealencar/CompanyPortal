<<<<<<< HEAD
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Department Entry</title>
</head>
<%	 
	String deptName = CookieUtilities.getCookieValue(request, "deptName", "");
	String location = CookieUtilities.getCookieValue(request, "location", "");
%>
<body>
=======
<% session.setAttribute("title", "Department Entry"); %>
<%@include file="WEB-INF/header.jsp" %>

<%	Cookie[] cookies = request.getCookies(); 
	//String deptName = cookies[0].getValue();
	//String loc = cookies[1].getValue(); %>
	
<%@include file="WEB-INF/menu.jsp" %>

>>>>>>> 9895d8ee79264caf56903a582d3657539eb4f290
	<h1>DEPARTMENT ENTRY</h1>
	<form method = "post" action = "DepartmentEntry">
		Department Name: <input type = "text" name ="deptName" value = "<%=deptName%>" /><br>
		Department Location/Floor: <input type = "text" name = "location" value = "<%=location%>" /><br>
		<input type = "submit" value = "Submit" />
		<input type = "reset" value = "Cancel" />
	</form>
<<<<<<< HEAD
<%
	response.addCookie(CookieUtilities.eraseCookie("deptName"));
	response.addCookie(CookieUtilities.eraseCookie("location"));
%>
</body>
</html>
=======
<%@include file="WEB-INF/footer.jsp" %>
>>>>>>> 9895d8ee79264caf56903a582d3657539eb4f290
