<%@page import="dataModel.User"%>
<body class="bg-light">
	<ul class="nav nav-tabs">
		<li class="nav-item">
			<a class="nav-link active" href ="home.jsp">Home</a>
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
			<span class="nav-link disabled">Welcome, <%String loggedInUser = (String) session.getAttribute("fName"); %>!</span>
		</li>
		<li class="nav-item">
			<form action="LogoutServlet">
				<input type="submit" value="logout" name="Logout">
			</form>
		</li>
	</ul>