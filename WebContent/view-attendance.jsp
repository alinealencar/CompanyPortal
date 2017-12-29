<% session.setAttribute("title", "View Attendance"); %>
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
		<h1 class="text-center">ATTENDANCE</h1>
		<br>
		<div class="form-group">
			<form method = "post" action = "EnterAttendanceHelper" name = "attendance-dept" onsubmit="return validateDepartment()" novalidate>
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
					<span class="error" id="errorDepartment">Please select a department.</span>
					<br>
			<div class="row align-items-center justify-content-center">
				<input type = "submit" value = "Submit" class="btn btn-primary"/>
				&nbsp;
				<input type = "reset" value = "Cancel" class="btn btn-secondary"/><br>
			</div>
		</form>