<% session.setAttribute("title", "Department Entry"); %>
<%@include file="WEB-INF/header.jsp" %>

	
<%@include file="WEB-INF/menu.jsp" %>
	<div class="container">
		<div class="row">
			<div class="col col-lg-3">
			</div>
			<div class="col col-lg-6">
				<div>
					<div class="${(deptInsertSuccess != null) ? 'alert alert-success':''}" role="alert">${deptInsertSuccess}</div>
					<div class="${(deptInsertError != null) ? 'alert alert-danger':''}" role="alert">${deptInsertError}</div>
				</div>
				<br>
				<h1 class="text-center">DEPARTMENT ENTRY</h1>
				<form method = "post" action = "DepartmentEntry">
					<div class="form-group">
						<label for="deptName">Department Name:</label> 
						<input class="form-control" placeholder="Department Name" type = "text" id = "deptName" name ="deptName" value = "<%if((String) session.getAttribute("deptName") != null) out.println((String) session.getAttribute("deptName"));%>"/>
						<% if((String) session.getAttribute("errorDeptName") != null){
							out.println("<div class=\"text-danger\" id =\"errorDeptName\">" + (String) session.getAttribute("errorDeptName") + "</div>");
						   }
						%> 
					</div>
					<div class="form-group">
						<label for="location">Department Location/Floor:</label>
						<input class="form-control" placeholder="Department Location" type = "text" id = "location" name = "location" value = "<%if((String) session.getAttribute("location") != null) out.println((String) session.getAttribute("location"));%>"/>
						<% if((String) session.getAttribute("errorLoc") != null){
							out.println("<div class=\"text-danger\" id = \"errorLoc\">" + (String) session.getAttribute("errorLoc") + "</div>");
						   }
						%> 
					</div>
					<input type = "submit" value = "Submit" class="btn btn-primary"/>
					<input type = "reset" value = "Clear" class="btn btn-secondary"/>
				</form>
			</div>
			<div class="col col-lg-3">
			</div>
		</div>
	</div>

<%@include file="WEB-INF/footer.jsp" %>
