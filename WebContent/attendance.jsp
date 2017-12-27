<% session.setAttribute("title", "Attendance"); %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center">ATTENDANCE</h1>
<br>
<div class="form-group">
	<form method = "post">
		<label for="department">Department: </label>
		<select id = "department" name = "department">
			<option value = "">Department</option>
			<c:forEach item="attend" value="${attendance.deptNames}">
			<option value = "${attend.deptName}">Department</option>
			</c:forEach>
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