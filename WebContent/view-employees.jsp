<% session.setAttribute("title", "View Employees"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container">
<div class="row">
	<div class="col col-lg-7">
	</div>
	<div class="col col-lg-12">
		<h1 class="text-center">VIEW EMPLOYEES</h1>
		<br>
		<div class="form-group">
			<form method = "post" action = "ViewEmployee" name = "employee-dept" onsubmit="return validateDepartment()" novalidate>
				<div class="row align-items-center justify-content-center">
					<label for="department">Department:&nbsp;</label>
					<select id = "department" name = "department" id = "department">
					<% //Get the list of departments from the database
					  String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");%>
					<option value="" selected>Department</option>
					<% 
					//Populate drop down list
					for(int i = 0; i < deptList.length; i++)
						out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");%>
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
				<%	
			String[] employeeFNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "firstname");
 			String[] employeeLNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "lastname");
 			String[] employeeNoList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "emp_no");
 			String[] employeeHireDate = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "hire_year");
 			String[] employeeEmail = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "email");
 			String[] employeeJobPosition = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "job_position");
			//create table
		%>
		<div class="row align-items-center justify-content-center">
			<table class="table table-striped text-center">
				<tr>
					<th><center>Last Name</center></th>
					<th><center>First Name</center></th>
					<th><center>Number</center></th>
					<th><center>Hire Date</center></th>
					<th><center>Email</center></th>
					<th><center>Job Position</center></th>
				</tr>
		<% 	if(session.getAttribute("department") != null ){
			for(int i = 0; i < employeeFNameList.length; i++) { %>
			<tr>
					<td><center><%=employeeFNameList[i]%></center></td>
					<td><center><%=employeeLNameList[i]%></center></td>
					<td><center><%=employeeNoList[i]%></center></td>
					<td><center><%=employeeHireDate[i]%></center></td>
					<td><center><%=employeeEmail[i]%></center></td>
					<td><center><%=employeeJobPosition[i]%></center></td>
					
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