<% session.setAttribute("title", "Attendance"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<h1 class="text-center">ATTENDANCE</h1>
<br>
<div>
	<span class="${(groupInsertSuccess != null) ? 'alert alert-success':''}" role="alert">${groupInsertSuccess}</span>
	<span class="${(groupInsertError != null) ? 'alert alert-danger':''}" role="alert">${groupInsertError}</span>
</div>
<div class="form-group">
	<form method = "post" action = "AttendanceHelperServlet">
		<label for="department">Department: </label>
			<select id = "department" name = "department">
					<% 
						//Get the list of departments from the database
						String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
						
						//check if a department is selected
						if(session.getAttribute("department") == null || session.getAttribute("department").equals("")){ %>
							<option value="" selected>Department</option>
							
						<% //Populate drop down list
							for(int i = 0; i < deptList.length; i++) {
								out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");
							}
						}
						else{
							%>
							<option value="">Department</option>
							<%							//Populate drop down list
							for(int i = 0; i < deptList.length; i++) {
								if(session.getAttribute("department").equals(deptList[i]))
									out.print("<option value =\"" + deptList[i] + "\"selected>" + deptList[i] + "</option>");
								else
									out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");
							}
							
							//session.setAttribute("department", null); //clear cache
						}
					%>
				</select>
				<% //show error message if no department is selected
					if((String) session.getAttribute("errorDepartment") != null){
						out.println("<span class=\"text-danger\">" +
								(String) session.getAttribute("errorDepartment") +
								"</span>");
			   		}
				%>
		<br>
		<input type = "submit" value = "Submit" class="btn btn-primary"/>
		<input type = "reset" value = "Cancel" class="btn btn-secondary"/><br>
	</form>
</div>
<div class="form-group">
	<form method = "post" action = "AttendanceServlet">
		<label for="date">Date: </label>
		<input type = "date" name="attendanceDate">
		<% //show error message if no department is selected
					if((String) session.getAttribute("errorAttendanceDate") != null){
						out.println("<span class=\"text-danger\">" +
								(String) session.getAttribute("errorAttendanceDate") +
								"</span>");
			   		}
				%>
		<br>
		<%	
			String[] employeeFNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "firstname");
 			String[] employeeLNameList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "lastname");
 			String[] employeeNoList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "emp_no");
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
					<td><center><input type="checkbox" name="present" value="true"></center></td>
			</tr>
		<%}}%>
		</table><br>
		<input type = "submit" value = "Enter" class="btn btn-primary"/>
	</form>
</div>
		

<%@include file="WEB-INF/footer.jsp" %>