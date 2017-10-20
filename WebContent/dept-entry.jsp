<<<<<<< HEAD
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
=======
>>>>>>> 995a804f883ce2f2bca40a468a0dec775d6f926b
<% session.setAttribute("title", "Department Entry"); %>
<%@include file="WEB-INF/header.jsp" %>

<%	Cookie[] cookies = request.getCookies(); 
	//String deptName = cookies[0].getValue();
	//String loc = cookies[1].getValue(); %>
	
<%@include file="WEB-INF/menu.jsp" %>

	<h1>DEPARTMENT ENTRY</h1>
	<form method = "post" action = "DepartmentEntry">
		Department Name: <input type = "text" name ="deptName" value = "<%=deptName%>" /><br>
		Department Location/Floor: <input type = "text" name = "location" value = "<%=location%>" /><br>
		<input type = "submit" value = "Submit" />
		<input type = "reset" value = "Cancel" />
	</form>
<%
	response.addCookie(CookieUtilities.eraseCookie("deptName"));
	response.addCookie(CookieUtilities.eraseCookie("location"));
%>

<%@include file="WEB-INF/footer.jsp" %>
