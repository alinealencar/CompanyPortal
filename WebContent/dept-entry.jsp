<% session.setAttribute("title", "Department Entry"); %>
<%@include file="WEB-INF/header.jsp" %>

<%--	//Cookie[] cookies = request.getCookies(); 
	//String deptName = cookies[0].getValue();
	//String loc = cookies[1].getValue(); 
	
	
--%>
	
<%@include file="WEB-INF/menu.jsp" %>
<span>
	<%	//show success message if database insertion is successful
		if((String) session.getAttribute("deptInsertSuccess") != null)
		out.println((String) session.getAttribute("deptInsertSuccess"));
	
		////show error message if database insertion failed
		if((String) session.getAttribute("deptInsertError") != null)
		out.println((String) session.getAttribute("deptInsertError"));
	%>
</span>
<br><br>

	<h1>DEPARTMENT ENTRY</h1>
	<form method = "post" action = "DepartmentEntry">
		Department Name: <input type = "text" name ="deptName" value = "<%if((String) session.getAttribute("deptName") != null) out.println((String) session.getAttribute("deptName"));%>"/>
		<% if((String) session.getAttribute("errorDeptName") != null){
			out.println((String) session.getAttribute("errorDeptName"));
		   }
		%>
		<br>
		Department Location/Floor: <input type = "text" name = "location" value = "<%if((String) session.getAttribute("location") != null) out.println((String) session.getAttribute("location"));%>"/>
		<% if((String) session.getAttribute("errorLoc") != null){
			out.println((String) session.getAttribute("errorLoc"));
		   }
		%>
		<br>
		<input type = "submit" value = "Submit" />
		<input type = "reset" value = "Cancel" />
	</form>
	
	
	
   	


<%@include file="WEB-INF/footer.jsp" %>
