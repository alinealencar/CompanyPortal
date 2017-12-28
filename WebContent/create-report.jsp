<% session.setAttribute("title", "Create Report Template"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>
<div class="container form-group">
	<h1 class="text-center">CREATE REPORT TEMPLATE</h1>
	<form action="CreateReportTemplate" method="get">
		<label>1. Details</label>
		<div class="row align-items-center justify-content-center">
			<br>
			<label>Report Template:&nbsp;</label><input type="text" name="templateName">
			&nbsp;&nbsp;&nbsp;
			<label>Date:&nbsp;</label><input id="templateDate" name = "templateDate" disabled>
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
		</div>	
		<hr>
  		<div class="row">
    		<div class="col">
     	 		<label for="section1">2. Section 1 </label><input id = "section1" type = "text" name="section1">
    		</div>
    		<div class="col-6 text-center">
      			<label>Criteria 1:&nbsp;</label><input type = "text" name="s1c1"><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s1c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s1c3"><br>
      			<label>Criteria 4:&nbsp;</label><input type = "text" name="s1c4"><br>
      			<label>Criteria 5:&nbsp;</label><input type = "text" name="s1c5">
    		</div>
    		<div class="col">
      			<label for="s1c1m">Maximum: </label>
      				<select name="s1c1m" class="maximum">
      					<option selected>-</option>
      				</select>
      				<br>
      			<label for="s1c2m">Maximum: </label>
      				<select name="s1c2m" class="maximum">
      					<option selected>-</option>
      				</select>
      				<br>
      			<label for="s1c3m">Maximum: </label>
      				<select name="s1c3m" class="maximum">
      					<option selected>-</option>
      				</select>
      				<br>
      			<label for="s1c4m">Maximum: </label>
      				<select name="s1c4m" class="maximum">
      					<option selected>-</option>
      				</select>
      				<br>
      			<label for="s1c5m">Maximum: </label>
      				<select name="s1c5m" class="maximum">
      					<option selected>-</option>
      				</select>
    		</div>
  		</div>
  		<hr>
  		<div class="row">
    		<div class="col">
				<label for="section2">3. Section 2 </label><input id = "section2" type = "text" name="section2">
    		</div>
    		<div class="col-6 text-center">
    			<label>Criteria 1:&nbsp;</label><input type = "text" name="s2c1"><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s2c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s2c3"><br>
    		</div>
    		<div class="col">
				<label for="s2c1m">Maximum: </label>
      				<select name="s2c1m" class="maximum">
      					<option selected>-</option>
      				</select>
      				<br>
      			<label for="s2c2m">Maximum: </label>
      				<select name="s2c2m" class="maximum">
      					<option selected>-</option>
      				</select>
      				<br>
      			<label for="s2c3m">Maximum: </label>
      				<select name="s2c3m" class="maximum">
      					<option selected>-</option>
      				</select>
    		</div>
  		</div>
  		<hr>
  		<div class="row">
    		<div class="col">
				<label for="section3">4. Section 3 </label><input id = "section3" type = "text" name="section3">
    		</div>
    		<div class="col-6 text-center">
    			<label>Criteria 1:&nbsp;</label><input type = "text" name="s3c1"><br>
      			<label>Criteria 2:&nbsp;</label><input type = "text" name="s3c2"><br>
      			<label>Criteria 3:&nbsp;</label><input type = "text" name="s3c3"><br>
    		</div>
    		<div class="col">
    			<label for="s3c1m">Maximum: </label>
      				<select name="s3c1m" class="maximum">
      					<option selected>-</option>
      				</select>
      				<br>
      			<label for="s3c2m">Maximum: </label>
      				<select name="s3c2m" class="maximum">
      					<option selected>-</option>
      				</select>
      				<br>
      			<label for="s3c3m">Maximum: </label>
      				<select name="s3c3m" class="maximum">
      					<option selected>-</option>
      				</select>
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
  
  	var formattedDate = month + '/' + day + '/' + date.getFullYear();
  	
  	document.getElementById('templateDate').value = formattedDate;
  	
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