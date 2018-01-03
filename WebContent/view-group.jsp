<% session.setAttribute("title", "View Group"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container">
<div class="row">
	<div class="col col-lg-2">
	</div>
	<div class="col col-lg-7">
		<h1 class="text-center">VIEW GROUP</h1>
		<br>
		<div class="form-group">
			<form method = "post" action = "ViewGroup" name = "viewGroup" onsubmit="return validateDepartment()" novalidate>
				<div class="row align-items-center justify-content-center">
				
					<div>

					<select id = "department" name = "department" id = "department" onChange="this.form.submit()">
					<% 
					//Get the list of departments from the database
					String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
					//check if a department is selected
					%>	
					<option value="" ${(deptViewAttendance == null) ? 'selected' : ''}>Department</option>
			
					<% //Populate drop down list
					for(int i = 0; i < deptList.length; i++){%>
						<option value ="<%=deptList[i]%>"
							<%if(request.getAttribute("dept") != null 
								&& request.getAttribute("dept").equals(deptList[i])){%>
							selected
						<%}%>
						><%=deptList[i]%></option>
				
					<%}%>
					</select>
					</div>
					&nbsp;
					<div>
					<select id = "group" name = "group" id = "group">
					<option value="" ${(dept == null) ? 'selected' : ''}>Group</option>
					<% 
					//Get the list of groups from the database
					String[] groupList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectGroupByDept((String) request.getAttribute("dept")), "group_name");
					for(int i = 0; i < groupList.length; i++){%>
					<option value ="<%=groupList[i]%>"
						<%if(request.getAttribute("group") != null 
							&& request.getAttribute("group").equals(groupList[i])){%>
						selected
					<%}%>
					><%=groupList[i]%></option>
			
				<%}%>

					<!-- <option value="" ${(group == null) ? 'selected' : ''}>Group</option>
					<option value = "${group}" ${(groups != null) ? 'selected': ''}>${deptName}</option> -->
			
			
					

					</select>
					</div>
					<br>
					</div>
					<div class="row align-items-center justify-content-center">
						<div class="error" id="errorDepartment">Please select a group.</div>
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
					<th>Department</th>
					<th>Group Name</th>
					<th>Employee Last Name</th>
					<th>Employee First Name</th>
					<th>Employee No</th>
					<%
					//get employee information
					String[] gGroupId = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectGroupEmployees((String) request.getAttribute("group")), "groups_id");		
					String[] gDepartment = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectGroupEmployees((String) request.getAttribute("group")), "dept_name");
					String[] gGroupName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectGroupEmployees((String) request.getAttribute("group")), "group_name");	
					String[] gLastName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectGroupEmployees((String) request.getAttribute("group")), "lastname");
					String[] gFirstName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectGroupEmployees((String) request.getAttribute("group")), "firstname");		
					String[] gEmployeeNo = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectGroupEmployees((String) request.getAttribute("group")), "emp_no");	
					
					for (int i = 0; i < gGroupId.length; i++){%>
					</tr>
					<tr>
						<td><%=gDepartment[i]%></td>
						<td><%=gGroupName[i]%></td>
						<td><%=gLastName[i]%></td>
						<td><%=gFirstName[i]%></td>
						<td><%=gEmployeeNo[i]%></td>
						
					<%}%>
			</table>
		</div>
		<br>
		<div class="row align-items-center justify-content-center">
			<input type = "submit" value = "Home" class="btn btn-primary" onClick="window.location='home.jsp';"/>
		</div>
		