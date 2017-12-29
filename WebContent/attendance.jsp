<% session.setAttribute("title", "Attendance"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<div class="container">
<div class="row">
	<div class="col col-lg-2">
		</div>
		<div class="col col-lg-7">
	<div>
		<div>
				<span class="${(attendanceInsertSuccess != null) ? 'alert alert-success':''}" role="alert">${attendanceInsertSuccess}</span>
				<span class="${(attendanceInsertError != null) ? 'alert alert-danger':''}" role="alert">${attendanceInsertError}</span>
			</div>
	</div>
		<h1 class="text-center">ATTENDANCE</h1>
		<br>
		<div class="form-group">
			<form method = "post" action = "AttendanceHelperServlet" name = "attendance-dept" onsubmit="return validateDepartment()" novalidate>
				<label for="department">Department: </label>
				<select id = "department" name = "department" id = "department">
					<% 
						//Get the list of departments from the database
						String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
						
						//check if a department is selected
						if(session.getAttribute("department") == null || session.getAttribute("department").equals("")){ %>
							<option value="" selected>Department</option>
							<% 
						//Populate drop down list
						for(int i = 0; i < deptList.length; i++)
							out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");
						}
						else{
						
						for(int i = 0; i < deptList.length; i++) {
							if(session.getAttribute("department").equals(deptList[i]))
								out.print("<option value =\"" + deptList[i] + "\"selected>" + deptList[i] + "</option>");
							else
								out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");
						} }%>
					</select>
					<span class="error" id="errorDepartment">Please select a department.</span>
					<br><br>
					<input type = "submit" value = "Submit" class="btn btn-primary"/>
			<input type = "reset" value = "Cancel" class="btn btn-secondary"/><br>
		</form>
	</div><br>
	<div class="form-group">
	<form method = "post" action = "AttendanceServlet" name = "attendance-date" onsubmit="return validateDate()" novalidate>
		<label for="date">Date: </label>
		<input type = "date" name="attendanceDate" id = "attendanceDate">
		<span class="error" id="errorAttendanceDate">Please enter a date.</span>
		<br>
		<br>
		<%	
			String[] employeeFNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "firstname");
 			String[] employeeLNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "lastname");
 			String[] employeeNoList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "emp_no");
 			String[] employeeIdList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "emp_id");
			//create table
		%>
			<table border="1">
				<tr>
					<th><center>Employee Last Name</center></th>
					<th><center>Employee First Name</center></th>
					<th><center>Employee Number</center></th>
					<th><center>Present</center></th>
				</tr>
		<% 	if(session.getAttribute("department") != null ){
			for(int i = 0; i < employeeFNameList.length; i++) { %>
			<tr>
					<td><center><%=employeeFNameList[i]%></center></td>
					<td><center><%=employeeLNameList[i]%></center></td>
					<td><center><%=employeeNoList[i]%></center></td>
					<td><center><input type="checkbox" name="present" value="<%=employeeIdList[i]%>"></center></td>
			</tr>
		<%}}%>
		</table><br>
		<input type = "submit" value = "Enter" class="btn btn-primary"/>
	</form>
</div>
</div>
	<div class="col col-lg-3">
	</div>
</div>
</div>

<%@include file="WEB-INF/footer.jsp" %>