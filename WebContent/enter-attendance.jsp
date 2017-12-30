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
						} 
					}%>
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
			String[] employeeFNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "firstname");
 			String[] employeeLNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "lastname");
 			String[] employeeNoList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "emp_no");
 			String[] employeeIdList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "emp_id");
			//create table
		%>
		<div class="row align-items-center justify-content-center">
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