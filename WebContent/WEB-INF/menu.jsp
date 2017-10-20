<body class="bg-light">
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
		<li class="nav-item">
			<a class="nav-link" href ="report.jsp">Reports</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href ="attendance.jsp">Attendance</a>
		</li>
		<li class="nav-item">
			<span class="nav-link disabled">Welcome, <%=request.getSession().getAttribute("fName") %>!</span>
		</li>
		<li class="nav-item">
			<form action="LogoutServlet">
				<button type="submit" class="btn btn-primary">Logout</button>
			</form>
		</li>
	</ul>
	<hr>