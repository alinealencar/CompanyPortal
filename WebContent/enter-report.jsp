<% session.setAttribute("title", "Enter Report"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@page import="dataModel.ReportTemplate" %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>
<div class="container form-group">
		<br>
	<div class="row align-items-center justify-content-center">
		<h1 class="text-center">Enter Report</h1>
	</div>
	<form action="EnterReport" method="get" name="enter-report" onsubmit="return validateCreateTemplate()" novalidate>
		<label>1. Details</label>
		<div class="row align-items-center justify-content-center">
			<br>
			<label for="department">Department:&nbsp;</label>
			
			<select id = "department" name = "department" onchange="this.form.submit()">
				<% 
				//Get the list of departments from the database
				String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");%>
				<option value="${(department == null) ? 'selected' : ''}">Select a Department</option>
				<% 
				//Populate drop down list
				for(int i = 0; i < deptList.length; i++){%>
					<option value ="<%=deptList[i]%>" 
						<%if(request.getAttribute("department")!= null &&
							request.getAttribute("department").equals(deptList[i])){
							out.println("selected");}%>
					><%=deptList[i]%></option>
				<%} %>
			</select>
			&nbsp;&nbsp;&nbsp;
			<label>Report Template:&nbsp;</label>
			<select name="reportTemplate" id="reportTemplate" onchange="this.form.submit()">
			<option value="" selected>Select a Template Name</option>
			<% if(request.getAttribute("reportTemplates") != null){
					List<ReportTemplate> resultTemplates = (List<ReportTemplate>) request.getAttribute("reportTemplates");
					for(int i = 0; i < resultTemplates.size(); i++){ %>
					<option value="<%=resultTemplates.get(i).getTemplateId() %>"
					<%if(request.getAttribute("templateId")!= null 
							&& Integer.parseInt((String) request.getAttribute("templateId")) == resultTemplates.get(i).getTemplateId()) {
							out.println("selected");}%>
					><%=resultTemplates.get(i).getTemplateName() %></option>
			<% }} %>
<!--  -->			
			</select>
		</div>
		<div class="row align-items-center justify-content-center">
			<label>Report Title:&nbsp;</label>
			<input type="text" name="reportTitle" id="reportTitle">			
			&nbsp;&nbsp;&nbsp;
			<label>Date:&nbsp;</label><input type="text" id="datepicker">
		</div>
		<div class="row align-items-center justify-content-center">
			<div class="error" id="errorTemplateName">Please enter a report name.</div>
		</div>
		
		<div class="row align-items-center justify-content-center">
			<div class="error" id="errorDepartment">Please enter a department.</div>
		</div>
		<div class="row align-items-center justify-content-center">
		<br>
			<label>Report Type:&nbsp;</label>
			<ul class="list-unstyled">
				<li><input type="radio" name="reportType" value="Group">Group</li>
				<li><input type="radio" name="reportType" value="Employee">Employee</li>
			</ul>
		</div>
		<div class="row align-items-center justify-content-center">
			<select id = "group" name = "group">
				<% 
				//Get the list of departments from the database
				String[] groupList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("groups"), "group_name");%>
				<option value="" selected>Group</option>
				<% 
				//Populate drop down list
				for(int i = 0; i < groupList.length; i++){
					out.print("<option value =\"" + groupList[i] + "\">" + groupList[i] + "</option>");
				}%>
			</select>
			&nbsp;&nbsp;&nbsp;
			<select id = "employee" name = "employee">
				<% 
				//Get the list of departments from the database
				String[] employeeList = HelperUtilities.getFullNameFromResultSet(DatabaseManagement.selectFromTable("employee"), "firstname", "lastname");%>
				<option value="" selected>Employee</option>
				<%
				//Populate drop down list
				for(int i = 0; i < employeeList.length; i++)
					out.print("<option value =\"" + employeeList[i] + "\">" + employeeList[i] + "</option>");
				%>
			</select>
			<br>
		</div>	
<!-- section 1  -->
		<hr>
  		<div class="row">
    		<div class="col-2">
     	 		<input id = "section1" type = "text" name="section1" size="12">
    			<div class="error" id="errorSection1">Please enter a title for Section 1.</div>
    		</div>
    		<div class="col-4 text-center">
      			<label>Criteria 1:&nbsp;</label><input type = "text" name="s1c1">
      			<div class="error" id="errorS1c1">Please enter a criteria.</div><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s1c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s1c3"><br>
      			<label>Criteria 4:&nbsp;</label><input type = "text" name="s1c4"><br>
      			<label>Criteria 5:&nbsp;</label><input type = "text" name="s1c5"><br>
    		</div>
    		<div class="col-2">
      			<label for="s1c1m">Maximum: </label>
      				<select name="s1c1m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors1c1m">Please choose a maximum value.</div>
      				<br>
      			<label for="s1c2m">Maximum: </label>
      				<select name="s1c2m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors1c2m">Please choose a maximum value.</div>
      				<br>
      			<label for="s1c3m">Maximum: </label>
      				<select name="s1c3m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors1c3m">Please choose a maximum value.</div>
      				<br>
      			<label for="s1c4m">Maximum: </label>
      				<select name="s1c4m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors1c4m">Please choose a maximum value..</div>
      				<br>
      			<label for="s1c5m">Maximum: </label>
      				<select name="s1c5m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors1c5m">Please choose a maximum value.</div>
    		</div>
    		<div class="col-4">
    			<textarea rows="8" cols="30"></textarea>
    		</div>
  		</div>
  		<hr>
<!-- section 2  -->
  		<div class="row">
    		<div class="col-2">
				<input id = "section2" type = "text" name="section2" size="12">
    			<div class="error" id="errorSection2">Please enter a title for Section 2.</div>
    		</div>
    		<div class="col-4 text-center">
    			<label>Criteria 1:&nbsp;</label><input type = "text" name="s2c1">
    			<div class="error" id="errorS2c1">Please enter a criteria.</div><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s2c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s2c3"><br>
    		</div>
    		<div class="col-2">
				<label for="s2c1m">Maximum: </label>
      				<select name="s2c1m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors2c1m">Please choose a maximum value.</div>
      				<br>
      			<label for="s2c2m">Maximum: </label>
      				<select name="s2c2m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors2c2m">Please choose a maximum value.</div>
      				<br>
      			<label for="s2c3m">Maximum: </label>
      				<select name="s2c3m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors2c3m">Please choose a maximum value.</div>
    		</div>
    		<div class="col-4">
    			<textarea rows="8" cols="30"></textarea>
    		</div>
  		</div>
  		<hr>
<!-- section 3  -->
  		<div class="row">
    		<div class="col-2">
				<input id = "section3" type = "text" name="section3" size="12">
    			<div class="error" id="errorSection3">Please enter a title for Section 3.</div>
    		</div>
    		<div class="col-4 text-center">
    			<label>Criteria 1:&nbsp;</label><input type = "text" name="s3c1">
    			<div class="error" id="errorS3c1">Please enter a criteria.</div><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s3c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s3c3"><br>
    		</div>
    		<div class="col-2">
    			<label for="s3c1m">Maximum: </label>
      				<select name="s3c1m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors3c1m">Please choose a maximum value.</div>
      				<br>
      			<label for="s3c2m">Maximum: </label>
      				<select name="s3c2m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors3c2m">Please choose a maximum value.</div>
      				<br>
      			<label for="s3c3m">Maximum: </label>
      				<select name="s3c3m" class="maximum">
      					<option value = "" selected>-</option>
      				</select>
      				<div class="error" id="errors3c3m">Please choose a maximum value.</div>
    		</div>
    		<div class="col-4">
    			<textarea rows="8" cols="30"></textarea>
    		</div>
  		</div>
  		<hr>
<!-- total / buttons -->
		<div class="row">
			<div class="col-9"></div>
			<div class="col-3">TOTAL&nbsp;&nbsp;<input id="total" type="text" name="total" size="2" disabled> / 50</div>
		</div>
		<div class="row align-items-center justify-content-center">
			<input type="submit" value="Create" class="btn btn-primary">
			&nbsp;
			<input type="reset" value="Clear" class="btn btn-secondary">
		</div>
	</form>
</div>

<!-- for datepicker -->

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
  
<%@include file="WEB-INF/footer.jsp" %>