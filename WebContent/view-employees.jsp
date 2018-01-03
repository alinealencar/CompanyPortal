<% session.setAttribute("title", "View Employees"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container">
<div class="row">
	<div class="col col-lg-2">
	</div>
	<div class="col col-lg-7">
		<h1 class="text-center">EMPLOYEE LISTING</h1>
		<br>
		<div class="form-group">
			<form method = "post" action = "ViewEmployee" name = "attendance-dept" onsubmit="return validateDepartment()" novalidate>
				<div class="row align-items-center justify-content-center">
					<label for="department">Department:&nbsp;</label>
					<select id = "department" name = "department" id = "department">
					<% 
					//Get the list of departments from the database
					String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
					//check if a department is selected
					%>	
					<option value="" ${(department == null) ? 'selected' : ''}>Department</option>
			
					<% //Populate drop down list
					for(int i = 0; i < deptList.length; i++){%>
						<option value ="<%=deptList[i]%>"
							<%if(request.getAttribute("department") != null 
								&& request.getAttribute("department").equals(deptList[i])){%>
							selected
						<%}%>
						><%=deptList[i]%></option>
				
					<%}%>
					</select>
					</div>
					<div class="row align-items-center justify-content-center">
						<div class="error" id="errorDepartment">Please select a department.</div>
					</div>
					<br>
			<div class="row align-items-center justify-content-center">
				<input type = "submit" value = "Submit" class="btn btn-primary"/>
				&nbsp;
				<input type = "reset" value = "Cancel" class="btn btn-secondary"/><br>
			</div>
		<br>
		</form>
		<div class="row align-items-center justify-content-center">
			<table class="table table-striped text-center">
				<tr>
					<th>Last Name</th>
					<th>First Name</th>
					<th>Employee Number</th>
					<th>Hire Year</th>
					<th>Email</th>
					<th>Job Position</th>
					<%
					//get employee information
					String[] employeeId = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "emp_id");
					String[] empLName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "lastname");	
					String[] empFName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "firstname");
					String[] empNo = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "emp_no");		
					String[] empHireYear = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "hire_year");	
					String[] empEmail = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "email");
					String[] empJobPosition = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "job_position");
					
					for (int i = 0; i < employeeId.length; i++){%>
					</tr>
					<tr>
						<td><%=empLName[i]%></td>
						<td><%=empFName[i]%></td>
						<td><%=empNo[i]%></td>
						<td><%=empHireYear[i]%></td>
						<td><%=empEmail[i]%></td>
						<td><%=empJobPosition[i]%></td>
						
					<%}%>
			</table>
		</div>
		<br>
		<div class="row align-items-center justify-content-center">
			<input type = "submit" value = "Home" class="btn btn-primary" onClick="window.location='home.jsp';"/>
		</div>
	</div>
</div>
</div>
</div>

		