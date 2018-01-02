<% session.setAttribute("title", "View Department"); %>
<%@include file="WEB-INF/header.jsp" %>

<%@include file="WEB-INF/menu.jsp" %>


<div class="container">
<div class="row">
	<div class="col col-lg-7">
	</div>
	<div class="col col-lg-12">
		<h1 class="text-center">VIEW DEPARTMENTS</h1>
		<br>
		<div class="form-group">
			<form method = "post" action = "ViewDepartments" name = "department-dept">
				
		<br>
		</form>
		<div class="row align-items-center justify-content-center">
			<table class="table table-striped text-center">
				<%	
			String[] departmentName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
			String[] departmentLoc = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "location");
				
			//create table
		%>
		<div class="row align-items-center justify-content-center">
			<table class="table table-striped text-center">
				<tr>
					<th><center>Department Name</center></th>
					<th><center>Department Location</center></th>
				</tr>
		<% 	if(session.getAttribute("department") != null ){
			for(int i = 0; i < departmentName.length; i++) { %>
			<tr>
					<td><center><%=departmentName[i]%></center></td>
					<td><center><%=departmentLoc[i]%></center></td>
					
			</tr>
		<%}}%>
		</table>
		</div>
		<br>
			</table>
		</div>
	</div>
</div>
</div>
</div>
<%@include file="WEB-INF/footer.jsp" %>