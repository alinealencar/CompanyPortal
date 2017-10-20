<% session.setAttribute("title", "Employee Entry"); %>
<%@include file="WEB-INF/header.jsp" %>

<%@include file="WEB-INF/menu.jsp" %>
<%-- 	<span>
	<% if((String) session.getAttribute("error") != null){
			out.println((String) session.getAttribute("error"));
		}
	%>
	</span> --%>
	<h1>EMPLOYEE ENTRY</h1>
	<form method = "post" action = "EmployeeEntry">
		First Name: <input type = "text" name ="firstName" /><br>
		Last Name: <input type = "text" name ="lastName" /><br>
		Employee #: <input type = "text" name ="employeeNum" /><br>
		Email: <input type = "text" name ="email" /><br>
		<select id="hireYear" name = "hireYear">
			<option value="">Hire Year</option>
  			<option value="2000">2000</option>
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
  			<option value="2017">2017</option>
		</select><br>
		<select id="jobPosition" name="jobPosition">
			<option value="">Job Position</option>
  			<option value="Community Manager">Community Manager</option>
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
  			<option value="Administrator">Administrator</option>
		</select><br>
		
		<input type = "submit" value = "Submit" />
		<input type = "reset" value = "Cancel" />
	</form>

<%@include file ="WEB-INF/footer.jsp"%>