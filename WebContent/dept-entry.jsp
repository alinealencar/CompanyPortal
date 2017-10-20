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
