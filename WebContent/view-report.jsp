<% session.setAttribute("title", "View Report"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container form-group">
	<div class="row align-items-center justify-content-center">
		<h1 class="text-center">VIEW REPORT</h1>
	</div>
	<form action="ViewReport" method="post">
		<select id = "department" name = "department" onChange="this.form.submit()">
				<% 
				//Get the list of departments from the database
				String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");%>
				<option value="" selected>Department</option>
				<% 
				//Populate drop down list
				for(int i = 0; i < deptList.length; i++)
					out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");%>
		</select>
	</form>
</div>

<%@include file="WEB-INF/footer.jsp" %>