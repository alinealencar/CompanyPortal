<% session.setAttribute("title", "View Attendance"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
					<% 
					//Get the list of departments from the database
					String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
					//check if a department is selected
					%>	
					<option value="" ${(deptViewAttendance == null) ? 'selected' : ''}>Department</option>
			
					<% //Populate drop down list
					for(int i = 0; i < deptList.length; i++){%>
						<option value ="<%=deptList[i]%>"
							<%if(request.getAttribute("deptViewAttendance") != null 
								&& request.getAttribute("deptViewAttendance").equals(deptList[i])){%>
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
			<table class="table table-striped text-center"">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Employee Number</th>
				<c:forEach items = "${dateList}" var="date">
					<th>${date}</th>
				</c:forEach>
				</tr>
				<c:forEach items = "${employeesByDept}" var="e">
				<tr>
					<td>${e.firstName}</td>
					<td>${e.lastName}</td>
					<td>${e.empNo}</td>
					<!-- <c:forEach items = "${attendanceList}" var = "a">
						<c:forEach items = "${attendance}" var = "a"> 
							<c:if test = "${a.present == null}">
								<td><input type="checkbox" name="present" disabled></td>
							</c:if>	
							<c:if test = "${a.present != null}">
								<td><input type="checkbox" name="present" checked disabled></td>
							</c:if>
						</c:forEach> 
					</c:forEach>  -->
				</tr>
				</c:forEach>
			</table>
		</div>
		<br>
		<div class="row align-items-center justify-content-center">
			<input type = "submit" value = "Enter" class="btn btn-primary" onClick="window.location='enter-attendance.jsp';"/>
		</div>
		