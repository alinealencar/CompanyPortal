<body class="bg-light">
<div style="margin-top: 1%;" class="container-fluid">
<div class="row">
	<ul class="nav nav-pills">
		<li class="nav-item">
			<a class="nav-link" href ="home.jsp">Home</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href ="dept-entry.jsp">Departments</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href ="employee-entry.jsp">Employees</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href ="group-entry.jsp">Group</a>
		</li>
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Reports</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href ="employee-report.jsp">Enter Employee Report</a>
				<a class="dropdown-item" href ="group-report.jsp">Enter Group Report</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href ="create-report.jsp">Create Report Template</a>
			</div>
		</li>
		<li class="nav-item">
			<a class="nav-link" href ="attendance.jsp">Attendance</a>
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