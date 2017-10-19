<jsp:include page="WEB-INF/header.jsp">
	<jsp:param name="title" value="Department Entry"/>
</jsp:include>

<%	Cookie[] cookies = request.getCookies(); 
	//String deptName = cookies[0].getValue();
	//String loc = cookies[1].getValue(); %>
	
<%@include file="WEB-INF/menu.jsp" %>

	<h1>DEPARTMENT ENTRY</h1>
	<form method = "post" action = "DepartmentEntry">
		Department Name: <input type = "text" name ="deptName" value = <%cookies[0].getValue();%>/><br>
		<p>${deptNameError}</p>
		Department Location/Floor: <input type = "text" name = "location" value = <%cookies[1].getValue();%>/><br>
		<p>${deptLocError}</p>
		<input type = "submit" value = "Submit" />
		<input type = "reset" value = "Cancel" />
	</form>
<%@include file="WEB-INF/footer.jsp" %>