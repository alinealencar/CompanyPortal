<% session.setAttribute("title", "Enter Report"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@page import="dataModel.*" %>
<%@page import="dataModel.ReportTemplate" %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container form-group">
	<div class="row align-items-center justify-content-center">
		<span class="${(reportInsertSuccess != null) ? 'alert alert-success':''}" role="alert">${reportInsertSuccess}</span>
		<span class="${(reportInsertFail != null) ? 'alert alert-danger':''}" role="alert">${reportInsertFail}</span>
	</div>
		<br>
	<div class="row align-items-center justify-content-center">
		<h1 class="text-center">Enter Report</h1>
	</div>
	<form action="EnterReport" method="get" name="enter-report" onsubmit="return validateEnterReport()" novalidate>
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
			<select name="reportTemplate" id="reportTemplate" onchange="this.form.submit()" >
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
			
			</select>
		</div>
		<div class="row align-items-center justify-content-center">
			<label>Report Title:&nbsp;</label>
			<input type="text" name="reportTitle" id="reportTitle">			
			&nbsp;&nbsp;&nbsp;
			<label>Date:&nbsp;</label><input type="text" name="date" id="datepicker">
		</div>
		<div class="row align-items-center justify-content-center">
			<div class="error" id="errorReportName">Please enter a report title.</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="error" id="errorDate">Please select a date.</div>
		</div>
<!--  -->
		<div class="row align-items-center justify-content-center">
		<br>
			<label>Report Type:&nbsp;</label>
			<ul class="list-unstyled">
				<li><input type="radio"  class="reportType" name="reportType" value="g" >Group</li>
				<li><input type="radio" class="reportType" name="reportType" value="e">Employee</li>
			</ul>
		</div>

		<div class="row align-items-center justify-content-center">
			<select id = "group" name = "group" disabled="true">
			<option value="" selected = "selected">Group</option>
				<% if(request.getAttribute("groups") != null){
					List<Group> resultGroups = (List<Group>) request.getAttribute("groups");
					for(int i = 0; i < resultGroups.size(); i++){ %>
					<option value="<%=resultGroups.get(i).getGroupId()%>"
					<%if(request.getAttribute("group")!= null 
							&& (String) request.getAttribute("group")== resultGroups.get(i).getGroupName()) {
							out.println("selected");}%>
					><%=resultGroups.get(i).getGroupName() %></option>
			<% }} %>
				
			</select>
			&nbsp;&nbsp;&nbsp;
			<select id = "employee" name = "employee" disabled="true">
				<option value=""  selected = "selected">Employee</option>
				<% if(request.getAttribute("employees") != null){
					List<Employee> resultEmployees = (List<Employee>) request.getAttribute("employees");
						for(int i = 0; i < resultEmployees.size(); i++){ 
						String fullName = resultEmployees.get(i).getFirstName() + " " + resultEmployees.get(i).getLastName();%>
						<option value="<%=resultEmployees.get(i).getEmpId()%>"
						<%if(request.getAttribute("employee")!= null 
								&& (String) request.getAttribute("employee") == fullName) {
								out.println("selected");}%>
						><%=fullName %></option>
				<% }} %>				
			</select>
			</div>
		<div class="row align-items-center justify-content-center">
			<div class="error" id="errorType">Please select a report type. Then choose the option from the dropdown list.</div>
		</div>
		<br>
		<div class="row align-items-center justify-content-center">
			<div class="error" id="evaluationError">Please choose an evaluation value for every criteria.</div>
		</div>
			<br>
<!-- section 1  -->
		<% ReportTemplate rt = (ReportTemplate) request.getAttribute("selectedTemplate"); %>
		<%if(rt != null){ %>
		<hr>
  		<div class="row">
    		<div class="col-2">   		   
     	 		<input id = "section1" type = "text" name="section1" size="12" value='2. <%= rt.getSection1()%>' disabled/>    			
    		</div>
    		
    		<div class="col-4 text-center">
      			<label>Criteria 1:&nbsp;</label><input type = "text" name="s1c1" value='<%= rt.getS1Criteria1()%>' disabled/><br>
      			<%if(!ValidateInput.isMissing(rt.getS1Criteria2())) {%>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s1c2" value='<%= rt.getS1Criteria2()%>' disabled/><br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS1Criteria3())) {%>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s1c3" value='<%= rt.getS1Criteria3()%>' disabled/><br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS1Criteria4())) {%>
      			<label>Criteria 4:&nbsp;</label><input type = "text" name="s1c4" value='<%= rt.getS1Criteria4()%>' disabled/><br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS1Criteria5())) {%>
      			<label>Criteria 5:&nbsp;</label><input type = "text" name="s1c5" value='<%= rt.getS1Criteria5()%>' disabled/><br>
    			<%} %>
    		</div>
    		<div class="col-2">
      			<label for="s1c1e">Evaluation: </label>
      				<select name="s1c1e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS1Crit1Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors1c1e">Please choose a value.</div>
      				<br>
      			<%if(!ValidateInput.isMissing(rt.getS1Criteria2())) {%>
      			<label for="s1c2e">Evaluation: </label>
      				<select name="s1c2e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS1Crit2Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors1c2e">Please choose a value.</div>
      				<br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS1Criteria3())) {%>
      			<label for="s1c3e">Evaluation: </label>
      				<select name="s1c3e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS1Crit3Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors1c3e">Please choose a value.</div>
      				<br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS1Criteria4())) {%>
      			<label for="s1c4e">Evaluation: </label>
      				<select name="s1c4e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS1Crit4Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors1c4e">Please choose a  value..</div>
      				<br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS1Criteria5())) {%>
      			<label for="s1c5e">Evaluation: </label>
      				<select name="s1c5e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS1Crit5Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors1c5e">Please choose a value.</div>
				<%} %>
    		</div>

    		<div class="col-4">
    			<textarea name="comment1" rows="8" cols="30"></textarea>
    			<div class="error" id="errorComm1">Please enter the comment</div>
    		</div>

  		</div>
  		<hr>
<!-- section 2  -->
  		<div class="row">
    		<div class="col-2">
				<input id = "section2" type = "text" name="section2" size="12" value='3. <%= rt.getSection2()%>' disabled/>
    		</div>
    		<div class="col-4 text-center">
    			<label>Criteria 1:&nbsp;</label><input type = "text" name="s2c1" value='<%= rt.getS2Criteria1()%>' disabled/><br>
    			<%if(!ValidateInput.isMissing(rt.getS2Criteria2())) {%>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s2c2" value='<%= rt.getS2Criteria2()%>' disabled/><br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS2Criteria3())) {%>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s2c3" value='<%= rt.getS2Criteria3()%>' disabled/><br>
      			<%} %>
    		</div>
    		<div class="col-2">
				<label for="s2c1e">Evaluation: </label>
      				<select name="s2c1e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS2Crit1Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors2c1e">Please choose a maximum value.</div>
      				<br>
      			<%if(!ValidateInput.isMissing(rt.getS2Criteria2())) {%>
      			<label for="s2c2e">Evaluation: </label>
      				<select name="s2c2e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS2Crit2Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors2c2e">Please choose a maximum value.</div>
      				<br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS2Criteria3())) {%>
      			<label for="s2c3e">Evaluation: </label>
      				<select name="s2c3e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS2Crit3Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors2c3e">Please choose a maximum value.</div>
      			<%} %>
    		</div>
    		<div class="col-4">
    			<textarea name="comment2" rows="8" cols="30"></textarea>
    			<div class="error" id="errorComm2">Please enter the comment</div>
    		</div>
  		</div>
  		<hr>
<!-- section 3  -->
  		<div class="row">
    		<div class="col-2">
				<input id = "section3" type = "text" name="section3" size="12" value='4. <%= rt.getSection3()%>' disabled/>
    		</div>
    		<div class="col-4 text-center">
    			<label>Criteria 1:&nbsp;</label><input type = "text" name="s3c1" value='<%= rt.getS3Criteria1()%>' disabled/><br>
    			<%if(!ValidateInput.isMissing(rt.getS3Criteria2())) {%>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s3c2" value='<%= rt.getS3Criteria2()%>' disabled/><br>
				<%} %>
				<%if(!ValidateInput.isMissing(rt.getS3Criteria3())) {%>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s3c3" value='<%= rt.getS3Criteria3()%>' disabled/><br>
				<%} %>
    		</div>
    		<div class="col-2">
    			<label for="s3c1e">Evaluation: </label>
      				<select name="s3c1e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS3Crit1Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors3c1e">Please choose a value.</div>
      				<br>
      			<%if(!ValidateInput.isMissing(rt.getS3Criteria2())) {%>
      			<label for="s3c2e">Evaluation: </label>
      				<select name="s3c2e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS3Crit2Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors3c2e">Please choose a value.</div>
      				<br>
      			<%} %>
      			<%if(!ValidateInput.isMissing(rt.getS3Criteria3())) {%>
      			<label for="s3c3e">Evaluation: </label>
      				<select name="s3c3e" class="evaluation" onchange="evaluationTotal()">
      					<option value = "" selected>-</option>
      					<%for(int i = 1; i <= rt.getS3Crit3Max(); i++)
      						out.print("<option value =\"" + i + "\">" + i + "</option>"); %>
      				</select>
      				<div class="error" id="errors3c3e">Please choose a value.</div>
				<%} %>
    		</div>
    		<div class="col-4">
    			<textarea name="comment3" rows="8" cols="30"></textarea>
    			<div class="error" id="errorComm3">Please enter the comment</div>
    		</div>
  		</div>
  		<hr>
<!-- total / buttons -->
		<div class="row">
			<div class="col-9"></div>
			<div class="col-3">TOTAL&nbsp;&nbsp;<input id="total" type="text" name="total" size="2" disabled> / <%= request.getAttribute("totalMax") %></div>
		</div>
		<div class="row align-items-center justify-content-center">
			<input type="submit" value="Enter" class="btn btn-primary">
			&nbsp;
			<input type="reset" value="Cancel" class="btn btn-secondary">
		</div>
		
	<%} %><!-- end of if(rt != null) -->
		
	</form>
</div>

<!-- scripts -->

<!-- for datepicker -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
  
<!-- for disable combo box depend on radio button -->
<script>
$(document).ready(function(){
    $('input[name="reportType"]').click(function() {
       if($('input[name="reportType"]').is(':checked')) { 
    	   $(".reportType").on("click", function () {
               $('#employee option').prop('selected', function() {
       	        return this.defaultSelected;
       	   		});
           });
    	   $(".reportType").on("click", function () {
           	$('#group option').prop('selected', function() {
          	        return this.defaultSelected;
          	   	}); 
       	});
           var radioValue = $("input[name='reportType']:checked").val();
            if(radioValue == "g"){
               $( "#group" ).prop( "disabled", false );
               $(".reportType").on("click", function () {
	               $('#employee option').prop('selected', function() {
	       	        return this.defaultSelected;
	       	   		});
               });
               $( "#employee" ).prop( "disabled", true );
            } else {
            	$(".reportType").on("click", function () {
	            	$('#group option').prop('selected', function() {
	           	        return this.defaultSelected;
	           	   	}); 
            	});
               $( "#group" ).prop( "disabled", true );
               $( "#employee" ).prop( "disabled", false );
            }
       }
    });
});
</script>

<!-- to calculate current evaluate sum -->
<script>
function evaluationTotal()
{
    var s1c1 = document.querySelector('select[name="s1c1e"]');
    var s1c2 = document.querySelector('select[name="s1c2e"]');
    var s1c3 = document.querySelector('select[name="s1c3e"]');
    var s1c4 = document.querySelector('select[name="s1c4e"]');
    var s1c5 = document.querySelector('select[name="s1c5e"]');
    
    var s2c1 = document.querySelector('select[name="s2c1e"]');
    var s2c2 = document.querySelector('select[name="s2c2e"]');
    var s2c3 = document.querySelector('select[name="s2c3e"]');
    
    var s3c1 = document.querySelector('select[name="s3c1e"]');
    var s3c2 = document.querySelector('select[name="s3c2e"]');
    var s3c3 = document.querySelector('select[name="s3c3e"]');

    if (s1c1 && s1c1.value && s1c1.value != "")
        s1c1 = parseInt(s1c1.value);
    else
        s1c1 = 0;
    if (s1c2 && s1c2.value && s1c2.value != "")
        s1c2 = parseInt(s1c2.value);
    else
        s1c2 = 0;
    if (s1c3 && s1c3.value && s1c3.value != "")
        s1c3 = parseInt(s1c3.value);
    else
        s1c3 = 0;
    if (s1c4 && s1c4.value && s1c4.value != "")
        s1c4 = parseInt(s1c4.value);
    else
        s1c4 = 0;
    if (s1c5 && s1c5.value && s1c5.value != "")
        s1c5 = parseInt(s1c5.value);
    else
        s1c5 = 0;
    if (s2c1 && s2c1.value && s2c1.value != "")
        s2c1 = parseInt(s2c1.value);
    else
        s2c1 = 0;
    if (s2c2 && s2c2.value && s2c2.value != "")
        s2c2 = parseInt(s2c2.value);
    else
        s2c2 = 0;
    if (s2c3 && s2c3.value && s2c3.value != "")
        s2c3 = parseInt(s2c3.value);
    else
        s2c3 = 0;
    if (s3c1 && s3c1.value && s3c1.value != "")
        s3c1 = parseInt(s3c1.value);
    else
        s3c1 = 0;
    if (s3c2 && s3c2.value && s3c2.value != "")
        s3c2 = parseInt(s3c2.value);
    else
        s3c2 = 0;
    if (s3c3 && s3c3.value && s3c3.value != "")
        s3c3 = parseInt(s3c3.value);
    else
        s3c3 = 0;

      document.getElementById("total").value = parseInt(s1c1) + parseInt(s1c2) + parseInt(s1c3) + parseInt(s1c4) + parseInt(s1c5) +
      											parseInt(s2c1) + parseInt(s2c2) + parseInt(s2c3) +
      											parseInt(s3c1) + parseInt(s3c2) + parseInt(s3c3);

}
</script>
  
<%@include file="WEB-INF/footer.jsp" %>