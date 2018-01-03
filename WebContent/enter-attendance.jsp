<% session.setAttribute("title", "Enter Attendance"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container">
<div class="row">
	<div class="col col-lg-2">
		</div>
		<div class="col col-lg-7">
	<div>
		<div>
				<div class="${(attendanceInsertSuccess != null) ? 'alert alert-success':''}" role="alert">${attendanceInsertSuccess}</div>
				<div class="${(attendanceInsertError != null) ? 'alert alert-danger':''}" role="alert">${attendanceInsertError}</div>
			</div>
	</div>
		<h1 class="text-center">ENTER ATTENDANCE</h1>
		<br>
		<div class="form-group">
			<form method = "post" action = "EnterAttendanceHelper" name = "attendance-dept" onsubmit="return validateDepartment()" novalidate>
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
		</form>
	</div><br>
	<div class="form-group">
	<form method = "post" action = "EnterAttendance" name = "attendance-date" onsubmit="return validateDate()" novalidate>
		<div class="row align-items-center justify-content-center">
			<label for="date">Date: &nbsp;</label>
			<input type = "date" name="attendanceDate" id = "attendanceDate">
		</div>
		<div class="row align-items-center justify-content-center">
			<div class="error" id="errorAttendanceDate">Please enter a date.</div>
		</div>
		<br>
		<br>
		<%	
			String[] employeeFNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "firstname");
 			String[] employeeLNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "lastname");
 			String[] employeeNoList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "emp_no");
 			String[] employeeIdList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) request.getAttribute("department")), "emp_id");
			//create table
		%>
		<div class="row align-items-center justify-content-center">
			<table class="table table-striped text-center">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Employee Number</th>
					<th>Present</th>
				</tr>
		<% 	if(request.getAttribute("department") != null ){
			for(int i = 0; i < employeeFNameList.length; i++) { %>
			<tr>
					<td><%=employeeFNameList[i]%></td>
					<td><%=employeeLNameList[i]%></td>
					<td><%=employeeNoList[i]%></td>
					<td><input type="checkbox" name="present" value="<%=employeeIdList[i]%>"></td>
			</tr>
		<%}}%>
		</table>
		</div>
		<br>
		<div class="row align-items-center justify-content-center">
			<input type = "submit" value = "Enter" class="btn btn-primary"/>
		</div>
	</form>
</div>
</div>
	<div class="col col-lg-3">
	</div>
</div>
</div>

<%@include file="WEB-INF/footer.jsp" %>