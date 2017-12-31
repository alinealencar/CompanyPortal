<% session.setAttribute("title", "View Attendance"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container">
<div class="row">
	<div class="col col-lg-2">
	</div>
	<div class="col col-lg-7">
		<h1 class="text-center">VIEW ATTENDANCE</h1>
		<br>
		<div class="form-group">
			<form method = "post" action = "ViewAttendance" name = "attendance-dept" onsubmit="return validateDepartment()" novalidate>
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
			<table border=1>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Employee Number</th>
					<% //Get the list of departments from the database
					String[] dateList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectAttendanceByDept((String) session.getAttribute("department")), "attendance_date");
					for (int i = 0; i < dateList.length; i++){
				%>
					<th><%=dateList[i]%></th>
				<%} %>
				</tr>
			</table>
		</div>