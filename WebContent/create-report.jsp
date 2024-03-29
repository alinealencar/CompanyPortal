<% session.setAttribute("title", "Create Report Template"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>
<div class="container form-group">
	<div class="row align-items-center justify-content-center">
		<span class="${(templateInsertSuccess != null) ? 'alert alert-success':''}" role="alert">${templateInsertSuccess}</span>
		<span class="${(templateInsertFail != null) ? 'alert alert-danger':''}" role="alert">${templateInsertFail}</span>
	</div>
	<br>
	<div class="row align-items-center justify-content-center">
		<h1 class="text-center">CREATE REPORT TEMPLATE</h1>
	</div>
	<form action="CreateReportTemplate" method="get" name="create-template" onsubmit="return validateCreateTemplate()" novalidate>
		<label>1. Details</label>
		<div class="row align-items-center justify-content-center">
			<br>
			<label>Report Template:&nbsp;</label><input type="text" name="templateName" id="templateName">
			&nbsp;&nbsp;&nbsp;
			<label>Date:&nbsp;</label><input class="templateDate" name = "templateDate" disabled>
			<input class="templateDate" name = "templateDate" type="hidden">
		</div>
		<div class="row align-items-center justify-content-center">
			<div class="error" id="errorTemplateName">Please enter a report name.</div>
		</div>
		<div class="row align-items-center justify-content-center">
			<label for="department">Department:&nbsp;</label>
			<select id = "department" name = "department">
				<% 
				//Get the list of departments from the database
				String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");%>
				<option value="" selected>Department</option>
				<% 
				//Populate drop down list
				for(int i = 0; i < deptList.length; i++)
					out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");%>
			</select>
			<br>
		</div>
		<div class="row align-items-center justify-content-center">
			<div class="error" id="errorDepartment">Please enter a department.</div>
		</div>	
		<hr>
  		<div class="row">
    		<div class="col">
     	 		<label for="section1">2. Section 1 </label><input id = "section1" type = "text" name="section1">
    			<div class="error" id="errorSection1">Please enter a title for Section 1.</div>
    		</div>
    		<div class="col-6 text-center">
      			<label>Criteria 1:&nbsp;</label><input type = "text" name="s1c1">
      			<div class="error" id="errorS1c1">Please enter a criteria.</div><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s1c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s1c3"><br>
      			<label>Criteria 4:&nbsp;</label><input type = "text" name="s1c4"><br>
      			<label>Criteria 5:&nbsp;</label><input type = "text" name="s1c5"><br>
    		</div>
    		<div class="col">
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
  		</div>
  		<hr>
  		<div class="row">
    		<div class="col">
				<label for="section2">3. Section 2 </label><input id = "section2" type = "text" name="section2">
    			<div class="error" id="errorSection2">Please enter a title for Section 2.</div>
    		</div>
    		<div class="col-6 text-center">
    			<label>Criteria 1:&nbsp;</label><input type = "text" name="s2c1">
    			<div class="error" id="errorS2c1">Please enter a criteria.</div><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s2c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s2c3"><br>
    		</div>
    		<div class="col">
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
  		</div>
  		<hr>
  		<div class="row">
    		<div class="col">
				<label for="section3">4. Section 3 </label><input id = "section3" type = "text" name="section3">
    			<div class="error" id="errorSection3">Please enter a title for Section 3.</div>
    		</div>
    		<div class="col-6 text-center">
    			<label>Criteria 1:&nbsp;</label><input type = "text" name="s3c1">
    			<div class="error" id="errorS3c1">Please enter a criteria.</div><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s3c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s3c3"><br>
    		</div>
    		<div class="col">
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
  		</div>
  		<hr>
		<div class="row align-items-center justify-content-center">
			<input type="submit" value="Create" class="btn btn-primary">
			&nbsp;
			<input type="reset" value="Clear" class="btn btn-secondary">
		</div>
	</form>
</div>
<script type="text/javascript">
	//Automatically fill out the date field with the current date
	var date = new Date();

  	var month = (1 + date.getMonth()).toString();
  	month = month.length > 1 ? month : '0' + month;

  	var day = date.getDate().toString();
  	day = day.length > 1 ? day : '0' + day;
  
  	var formattedDate = date.getFullYear() + '-' + month + '-' + day;
  	
  	//Add the formatted date to both the disabled text field and the hidden one (value of disabled text field is not submitted)
  	document.getElementsByClassName('templateDate')[0].value = formattedDate;
  	document.getElementsByClassName('templateDate')[1].value = formattedDate;
  	
  	//Populate the dropdowns for maximum with numbers from 1 to 5
  	var maxDropdown = document.getElementsByClassName("maximum"); 

  	for(var j = 0; j < maxDropdown.length; j++){
	  	for(var i = 1; i <= 5; i++) {
	  	    var el = document.createElement("option");
	  	    el.textContent = i;
	  	    el.value = i;
	  	  	maxDropdown[j].appendChild(el);
	  	}
  	}
</script>
<%@include file="WEB-INF/footer.jsp" %>