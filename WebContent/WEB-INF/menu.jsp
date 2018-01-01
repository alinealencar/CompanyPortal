<body class="bg-light">
<div style="margin-top: 1%;" class="container-fluid">
<div class="row">
	<ul class="nav nav-pills">
		<li class="nav-item">
			<a class="nav-link" href ="home.jsp">Home</a>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Departments</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href ="dept-entry.jsp">Enter Department</a>
				<a class="dropdown-item" href ="view-department.jsp">View Department</a>
			</div>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Employees</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href ="employee-entry.jsp">Enter Employees</a>
				<a class="dropdown-item" href ="view-employees.jsp">View Employees</a>
			</div>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Group</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href ="group-entry.jsp">Enter Group</a>
				<a class="dropdown-item" href ="view-group.jsp">View Group</a>
			</div>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Reports</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href ="enter-report.jsp">Enter Report</a>
				<a class="dropdown-item" href ="create-report.jsp">Create Report Template</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href ="view-report.jsp">View Report</a>
			</div>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Attendance</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href ="enter-attendance.jsp">Enter Attendance</a>
				<a class="dropdown-item" href ="view-attendance.jsp">View Attendance</a>
			</div>
		</li>
		<li class="nav-item">
			<span class="nav-link disabled">Signed in as <%=request.getSession().getAttribute("fName") %>.</span>
		</li>
		<li class="nav-item">
			<form action="LogoutServlet">
				<button type="submit" class="btn btn-outline-primary">Logout</button>
			</form>
		</li>
	</ul>
	</div>
</div>
	<hr>