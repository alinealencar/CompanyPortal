<% session.setAttribute("title", "Attendance"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<h1 class="text-center">ATTENDANCE</h1>
<br>
<div class="form-group">
	<form method = "post">
		<label for="department">Department: </label>
			<select id = "department" name = "department" onChange="this.form.submit()">
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
							
							session.setAttribute("department", null); //clear cache
						}
					%>
				</select>
		<br>
		<input type = "submit" value = "Submit" class="btn btn-primary"/>
		<input type = "reset" value = "Cancel" class="btn btn-secondary"/><br>
	</form>
</div>
<div class="form-group">
	<form method = "post">
		<label for="department">Date: </label>
		<input type = "date" name="attendanceDate">
		<input type = "submit" value = "Enter" class="btn btn-primary"/>
	</form>
</div>
		

<%@include file="WEB-INF/footer.jsp" %>