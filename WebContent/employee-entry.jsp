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
						
			  			<%-- <option value="2000">2000</option>
			  			<option value="2001">2001</option>
			 			<option value="2002">2002</option>
			  			<option value="2003">2003</option>
			  			<option value="2004">2004</option>
			  			<option value="2005">2005</option>
			  			<option value="2006">2006</option>
			  			<option value="2007">2007</option>
			  			<option value="2008">2008</option>
			  			<option value="2009">2009</option>
			  			<option value="2010">2010</option>
			  			<option value="2011">2011</option>
			  			<option value="2012">2012</option>
			  			<option value="2013">2013</option>
			  			<option value="2014">2014</option>
			  			<option value="2015">2015</option>
			  			<option value="2016">2016</option>
			  			<option value="2017">2017</option> --%>
					</select><br>
					<p class="text-danger">${errorYear}</p>
					<select id="jobPosition" name="jobPosition">
						<%String selectedPosition = (String) session.getAttribute("jobPosition"); %>
						<option selected disabled>Job Position</option>
						<%
							String[] jobPositions = new String[]{"Community Manager", "Analytics Manager", "CTO",
									"Front End", "Back End", "Database",
									"Testing", "API", "System Operator", "Designer",
									"Feature Management", "Introductory Agreements", "Pre-Sales",
									"Account Management", "CFO", "Administrator"};
						
							for(int i = 0; i <jobPositions.length; i++){
								out.print("<option value=\"" + jobPositions[i] + "\" ");
				  				if(selectedPosition != null && selectedPosition.equals(jobPositions[i]))
				  					out.print("selected=\"selected\"");
				  				out.print(">" + jobPositions[i] + "</option>");
							}
						
						
						%>
<!-- 			  			<option value="Community Manager">Community Manager</option>
			  			<option value="Analytics Manager">Analytics Manager</option>
			  			<option value="CTO">CTO</option>
			  			<option value="Front End">Front End</option>
			  			<option value="Back End">Back End</option>
			  			<option value="Database">Database</option>
			  			<option value="Testing">Testing</option>
			  			<option value="API">API</option>
			  			<option value="System Operator">System Operator</option>
			  			<option value="Designer">Designer</option>
			  			<option value="Feature Management">Feature Management</option>
			  			<option value="Introductory Agreements">Introductory Agreements</option>
			  			<option value="Pre-Sales">Pre-Sales</option>
			  			<option value="Account Management">Account Management</option>
			  			<option value="CFO">CFO</option>
			  			<option value="Administrator">Administrator</option> -->
					</select><br>
					<p class="text-danger">${errorPosition}</p>
					
<%-- 					<p>Session Values: ${firstName }, ${lastName}, ${employeeNum}, ${email}, ${hireYear}, ${jobPosition}</p> --%>
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