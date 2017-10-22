<% session.setAttribute("title", "Employee Entry"); %>
<%@page import="java.util.Calendar"%>
<%@include file="WEB-INF/header.jsp" %>

<%@include file="WEB-INF/menu.jsp" %>

<div>
	<div class="text-center">
		<% if((String) session.getAttribute("employeeInsertSuccess") != null){
				out.println("<span class = \"alert alert-success\" role = \"alert\">" +
				(String) session.getAttribute("employeeInsertSuccess") +
				"</span>");
			}
			else if((String) session.getAttribute("employeeInsertFail") != null){
				out.println("<span class = \"alert alert-danger\" role = \"alert\">" +
				(String) session.getAttribute("employeeInsertFail") +
				"</span>");
			}
		%>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col col-lg-4">
			</div>
			<div class="col col-lg-4">
				<h1>EMPLOYEE ENTRY</h1>
				<br>
				<form method = "post" action = "EmployeeEntry" id="employeeEntry">
					<div class="form-group">
						<label for="firstName">First Name:</label> 
						<input id="firstName" class="form-control" placeholder="First Name" type ="text" name ="firstName" value="${firstName }"/>
						<p class="text-danger">${errorFName}</p>
					</div>
					<div class="form-group">
						<label for="lastName">Last Name:</label>
						<input id="lastName" class="form-control" placeholder="Last Name" type="text" name ="lastName" value="${lastName}"/>
						<p class="text-danger">${errorLName}</p>
					</div>
					<div class="form-group">
						<label for="employeeNo">Employee #: </label>
						<input id="employeeNo" class="form-control" placeholder="Employee #" type = "text" name ="employeeNum" value="${employeeNum}"/>
					</div>
					<div class="form-group">
						<label for="email">Email:</label>
						<input id="email" class="form-control" placeholder="example@domain.com" type = "text" name ="email" value="${email}"/>
						<p class="text-danger">${errorEmail}</p>
					</div>
					<select id="hireYear" name = "hireYear">
					<%String selectedYear = (String) session.getAttribute("hireYear"); %>
						<option selected disabled>Hire Year</option>
						
						<%for(int i = 2000; i <= Calendar.getInstance().get(Calendar.YEAR); i++){
							out.print("<option value=\"" + i + "\" ");
			  				if(selectedYear != null && selectedYear.equals((Integer.toString(i))))
			  					out.print("selected=\"selected\"");
			  				out.print(">" + i + "</option>");
						} %>
					</select><br>
					<p class="text-danger">${errorYear}</p>
					<select id="jobPosition" name="jobPosition">
						<%String selectedPosition = (String) session.getAttribute("jobPosition"); %>
						<option selected disabled>Job Position</option>
						<%
							String[] jobPositions = new String[]{"Community Manager", "Analytics Manager", "CTO",
									"Front End", "Back End", "Database",
									"System Operator", "Designer",
									"Feature Management", "Introductory Agreements", "Pre-Sales",
									"Account Management", "CFO", "Administrator"};
						
							for(int i = 0; i <jobPositions.length; i++){
								out.print("<option value=\"" + jobPositions[i] + "\" ");
				  				if(selectedPosition != null && selectedPosition.equals(jobPositions[i]))
				  					out.print("selected=\"selected\"");
				  				out.print(">" + jobPositions[i] + "</option>");
							}
						
						
						%>
					</select><br>
					<p class="text-danger">${errorPosition}</p>
					
					<input type = "submit" value = "Submit" class="btn btn-primary" />
					<input type = "reset" value="Clear" onclick="ClearAll()" class="btn btn-secondary"/>
				</form>
			</div>
			<div class="col col-lg-4">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function ClearAll(){
<%request.getSession().setAttribute("firstName", "");%>;
}
</script>


<%@include file ="WEB-INF/footer.jsp"%>