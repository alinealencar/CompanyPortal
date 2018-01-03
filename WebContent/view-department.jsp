<% session.setAttribute("title", "View Departments"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container">
<div class="row">
	<div class="col col-lg-2">
	</div>
	<div class="col col-lg-7">
		<h1 class="text-center">VIEW DEPARTMENT</h1>
		<br>
		<div class="row align-items-center justify-content-center">
			<table class="table table-striped text-center">
				<tr>
					<th>Department Name</th>
					<th>Department Location</th>
					<%{%>
					<%}
					//get employee information 
					String[] deptId = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "id");
					String[] deptName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
					String[] deptLoc = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "location");
					for (int i = 0; i < deptId.length; i++){%>
					</tr>
					<tr>
						<td><%=deptName[i]%></td>
						<td><%=deptLoc[i]%></td>
					<%}%>		
			</table>
		</div>
		<br>
	</div>
</div>
</div>
<%@include file="WEB-INF/footer.jsp" %>
		