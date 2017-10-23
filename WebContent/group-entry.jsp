<% session.setAttribute("title", "Group Entry"); %>
<%@include file="WEB-INF/header.jsp" %>

<% Connection conn = DatabaseAccess.connectDataBase(); 
	
	ResultSet rsDept = null; 
	ResultSet rsEmp1 = null; 
	ResultSet rsEmp2 = null;
	ResultSet rsEmp3 = null;
	ResultSet rsEmp4 = null;
	ResultSet rsEmp5 = null;
	ResultSet rsEmp6 = null;
	
	//result set to select departments
	rsDept = DatabaseManagement.selectFromTable("department", conn);
	
	String selectedEmp1 = null;
	String selectedEmp2 = null;
	String selectedEmp3 = null;
	String selectedEmp4 = null;
	String selectedEmp5 = null;
	String selectedEmp6 = null;
	
	String selectedDept = (String) session.getAttribute("department");	
	
	//result set to retrieve employees based on selected department
	rsEmp1 = DatabaseManagement.selectEmployees(selectedDept, conn);
	rsEmp2 = DatabaseManagement.selectEmployees(selectedDept, conn);
	rsEmp3 = DatabaseManagement.selectEmployees(selectedDept, conn);
	rsEmp4 = DatabaseManagement.selectEmployees(selectedDept, conn);
	rsEmp5 = DatabaseManagement.selectEmployees(selectedDept, conn);
	rsEmp6 = DatabaseManagement.selectEmployees(selectedDept, conn);

	//store session attributes for employees to variables if they are not null
	if((String) session.getAttribute("emp1") != null) 
		selectedEmp1 = (String) session.getAttribute("emp1");
	if((String) session.getAttribute("emp2") != null) 
		selectedEmp2 = (String) session.getAttribute("emp2");
	if((String) session.getAttribute("emp3") != null) 
		selectedEmp3 = (String) session.getAttribute("emp3");
	if((String) session.getAttribute("emp4") != null) 
		selectedEmp4 = (String) session.getAttribute("emp4");
	if((String) session.getAttribute("emp5") != null) 
		selectedEmp5 = (String) session.getAttribute("emp5");
	if((String) session.getAttribute("emp6") != null) 
		selectedEmp6 = (String) session.getAttribute("emp6");
%>

<%@include file="WEB-INF/menu.jsp" %>

<div class="container">
	<div class="row">
		<div class="col col-lg-2">
		</div>
		<div class="col col-lg-7">
			<div>
				<span>
					<%	//show success message if database insertion is successful
						if((String) session.getAttribute("groupInsertSuccess") != null) {
							out.println("<span  class = \"alert alert-success\" role = \"alert\">" +
								(String) session.getAttribute("groupInsertSuccess") +
								"</span>");
						}
					
						//show error message if database insertion failed
						if((String) session.getAttribute("groupInsertError") != null) {
							out.println("<span  class = \"alert alert-danger\" role = \"alert\">" +
								(String) session.getAttribute("groupInsertError") +
									"</span>");
						}
					%>
				</span>
			</div>
			<br>
			<br>
			<h1 class="text-center">GROUP ENTRY</h1>
			<br>
			<form method = "post" action = "GroupEntryHelper">
			<div class="form-group">
				<label for="department">Department: </label>
				<select id = "department" name = "department" onChange="this.form.submit()">
					<% //check if a department is selcted
						if(selectedDept == null){
						out.print("<option value=\"\" selected>Department</option> ");
							//populate drop down list 
							while(rsDept.next()){
								out.print("<option value =\"" + rsDept.getString("dept_name") + "\">" + rsDept.getString("dept_name") + "</option>");
							}
						}
						else{
							//populate dropdown and assign selected value to show as a default in the dropdown list
		 					while(rsDept.next()){
		    					if(rsDept.getString("dept_name").equals(selectedDept)){
		        					out.print("<option value=\""+rsDept.getString("dept_name")+"\" SELECTED >"+rsDept.getString("dept_name")+"</option>");
		    					}
		    					else{
		        					out.print("<option value=\""+rsDept.getString("dept_name")+"\" >"+rsDept.getString("dept_name")+"</option>");
		   					 	}
		  					}
						}
					%>
				</select>
				<% //show error message if no department is selected
					if((String) session.getAttribute("errorDepartment") != null){
						out.println("<span class=\"text-danger\">" +
								(String) session.getAttribute("errorDepartment") +
								"</span>");
			   		}
				%>
			</div>
			<div class="form-group">
				<label for="groupName">Group Name: </label>
				<input class="form-control" placeholder="Group Name" type = "text" id = "groupName" name = "groupName" value = "<%if((String) session.getAttribute("groupName") != null) out.println((String) session.getAttribute("groupName"));%>"/>
				<% 
					//show error message if no group name is entered
					if((String) session.getAttribute("errorGroupName") != null){
						out.println("<span class=\"text-danger\">" +
						(String) session.getAttribute("errorGroupName") +
						"</span>");
			   		}
				%>
				<br>
			</div>
			
			<%
				 //show error message if no employee is selected or a duplicate employee is selected
				 if((String) session.getAttribute("errorEmp") != null){
					 out.println("<span class=\"text-danger\">" +
					 (String) session.getAttribute("errorEmp") +
					"</span>");
		   		 }
			%>
			<div class="form-group">
				<label>Employee 1:</label>
				<select style="margin-right:10%;" id="emp1" name = "emp1">
					<% //check if a department is selected
						if(selectedDept == null){
						out.print("<option value = \"\" selected>Employee</option> ");
					  }
						//check if an employee selected,
					 	if(selectedEmp1 != null){
					 		out.print("<option value=\"\">Employee</option> ");
					 		//populate dropdown list
							while(rsEmp1.next()){
								//populate dropdown and assign selected value to show as a default in the dropdown list
	    						if(selectedEmp1.contains(rsEmp1.getString("firstname")) && selectedEmp1.contains(rsEmp1.getString("lastname"))){
	        							out.print("<option value=\""+ rsEmp1.getString("firstname") + " " + rsEmp1.getString("lastname") +"\" SELECTED >"+rsEmp1.getString("firstname") + " " + rsEmp1.getString("lastname")+"</option>");
	    						}
	    						else{
	        						out.print("<option value=\""+rsEmp1.getString("firstname") + " " + rsEmp1.getString("lastname")+"\" >"+rsEmp1.getString("firstname") + " " + rsEmp1.getString("lastname")+ "</option>");
	   					 		}
	  						}
					  	}
						//if no employee is selected, populate dropdown list
					  	else {
							out.print("<option value = \"\" selected>Employee</option>");
								while(rsEmp1.next()){
									out.print("<option value =\"" + rsEmp1.getString("firstname") + " " + rsEmp1.getString("lastname") + "\">" + rsEmp1.getString("firstname") + " " + rsEmp1.getString("lastname")+ "</option>");
							}
						}
				%>
				</select>
	
				<label>Employee 2:</label>
				<select id="emp2" name = "emp2">
					<% //check if a second employee is selected
						if(selectedEmp2 != null){
							out.print("<option value = \"\" selected>Employee</option> ");
							while(rsEmp2.next()){
								//populate dropdown and assign selected value to show as a default in the dropdown list
								if(selectedEmp2.contains(rsEmp2.getString("firstname")) && selectedEmp2.contains(rsEmp2.getString("lastname"))){
	 				   				out.print("<option value=\""+ rsEmp2.getString("firstname") + " " + rsEmp2.getString("lastname") +"\" SELECTED >"+rsEmp2.getString("firstname") + " " + rsEmp2.getString("lastname")+"</option>");
	    						}
	    						else{
	        							out.print("<option value=\""+rsEmp2.getString("firstname") + " " + rsEmp2.getString("lastname")+"\" >"+rsEmp2.getString("firstname") + " " + rsEmp2.getString("lastname")+ "</option>");
	   					 			}
	  							}
							}
					  else {
							out.print("<option value = \"\" selected>Employee</option> ");
								while(rsEmp2.next()){
									out.print("<option value =\"" + rsEmp2.getString("firstname") + " " + rsEmp2.getString("lastname") + "\">" + rsEmp2.getString("firstname") + " " + rsEmp2.getString("lastname")+ "</option>");
							}
						
						}
				%>
				</select>
			</div>
			<div class="form-group">
				<label>Employee 3:</label>
				<select style="margin-right:10%;" id="emp3" name = "emp3">
					<% //check if a third employee is selected
						if(selectedEmp3 != null){
							out.print("<option value = \"\" selected>Employee</option> ");
							while(rsEmp3.next()){
								//populate dropdown and assign selected value to show as a default in the dropdown list
								if(selectedEmp3.contains(rsEmp3.getString("firstname")) && selectedEmp3.contains(rsEmp3.getString("lastname"))){
	 				   				out.print("<option value=\""+ rsEmp3.getString("firstname") + " " + rsEmp3.getString("lastname") +"\" SELECTED >"+rsEmp3.getString("firstname") + " " + rsEmp3.getString("lastname")+"</option>");
	    						}
	    						else{
	        							out.print("<option value=\""+rsEmp3.getString("firstname") + " " + rsEmp3.getString("lastname")+"\" >"+rsEmp3.getString("firstname") + " " + rsEmp3.getString("lastname")+ "</option>");
	   					 			}
	  							}
							}
						
					  else {
							out.print("<option value = \"\" selected>Employee</option> ");
								while(rsEmp3.next()){
									out.print("<option value =\"" + rsEmp3.getString("firstname") + " " + rsEmp3.getString("lastname") + "\">" + rsEmp3.getString("firstname") + " " + rsEmp3.getString("lastname")+ "</option>");
							}
						
						}
	  				
				%>
				</select>

				<label>Employee 4:</label>
				<select id="emp4" name = "emp4">
					<% //check if a fourth employee is selected
						if(selectedEmp4 != null){
							out.print("<option value = \"\" selected>Employee</option> ");
							while(rsEmp4.next()){
								//populate dropdown and assign selected value to show as a default in the dropdown list
								if(selectedEmp4.contains(rsEmp4.getString("firstname")) && selectedEmp4.contains(rsEmp4.getString("lastname"))){
	 				   				out.print("<option value=\""+ rsEmp4.getString("firstname") + " " + rsEmp4.getString("lastname") +"\" SELECTED >"+rsEmp4.getString("firstname") + " " + rsEmp4.getString("lastname")+"</option>");
	    						}
	    						else{
	        							out.print("<option value=\""+rsEmp4.getString("firstname") + " " + rsEmp4.getString("lastname")+"\" >"+rsEmp4.getString("firstname") + " " + rsEmp4.getString("lastname")+ "</option>");
	   					 			}
	  							}
							}
						
					  else {
							out.print("<option value = \"\" selected>Employee</option> ");
								while(rsEmp4.next()){
									out.print("<option value =\"" + rsEmp4.getString("firstname") + " " + rsEmp4.getString("lastname") + "\">" + rsEmp4.getString("firstname") + " " + rsEmp4.getString("lastname")+ "</option>");
							}
						
						}
				%>
				</select>
			</div>
			
			<div class="form-group">
				<label>Employee 5:</label>
				<select style="margin-right:10%;" id="emp5" name = "emp5">
					<% //check if a fifth employee is selected
						if(selectedEmp5 != null){
							out.print("<option value = \"\" selected>Employee</option> ");
							while(rsEmp5.next()){
								//populate dropdown and assign selected value to show as a default in the dropdown list
								if(selectedEmp5.contains(rsEmp5.getString("firstname")) && selectedEmp5.contains(rsEmp5.getString("lastname"))){
	 				   				out.print("<option value=\""+ rsEmp5.getString("firstname") + " " + rsEmp5.getString("lastname") +"\" SELECTED >"+rsEmp5.getString("firstname") + " " + rsEmp5.getString("lastname")+"</option>");
	    						}
	    						else{
	        							out.print("<option value=\""+rsEmp5.getString("firstname") + " " + rsEmp5.getString("lastname")+"\" >"+rsEmp5.getString("firstname") + " " + rsEmp5.getString("lastname")+ "</option>");
	   					 			}
	  							}
							}
						
					  else {
							out.print("<option value = \"\" selected>Employee</option> ");
								while(rsEmp5.next()){
									out.print("<option value =\"" + rsEmp5.getString("firstname") + " " + rsEmp5.getString("lastname") + "\">" + rsEmp5.getString("firstname") + " " + rsEmp5.getString("lastname")+ "</option>");
							}
						
						}
				%>
				</select>
				
				<label>Employee 6:</label>
				<select id="emp6" name = "emp6">
					<% //check if a sixth employee is selected
						if(selectedEmp6 != null){
							out.print("<option value = \"\" selected>Employee</option> ");
							while(rsEmp6.next()){
								//populate dropdown and assign selected value to show as a default in the dropdown list
								if(selectedEmp6.contains(rsEmp6.getString("firstname")) && selectedEmp6.contains(rsEmp6.getString("lastname"))){
	 				   				out.print("<option value=\""+ rsEmp6.getString("firstname") + " " + rsEmp6.getString("lastname") +"\" SELECTED >"+rsEmp6.getString("firstname") + " " + rsEmp6.getString("lastname")+"</option>");
	    						}
	    						else{
	        							out.print("<option value=\""+rsEmp6.getString("firstname") + " " + rsEmp6.getString("lastname")+"\" >"+rsEmp6.getString("firstname") + " " + rsEmp6.getString("lastname")+ "</option>");
	   					 			}
	  							}
							}
						
					  else {
							out.print("<option value = \"\" selected>Employee</option> ");
								while(rsEmp6.next()){
									out.print("<option value =\"" + rsEmp6.getString("firstname") + " " + rsEmp6.getString("lastname") + "\">" + rsEmp6.getString("firstname") + " " + rsEmp6.getString("lastname")+ "</option>");
							}
						
						}
				%>
				</select>
			</div>
			<br>
			<input type = "submit" value = "Submit" onclick = "form.action = 'GroupEntry'" class="btn btn-primary" />
			<input type = "reset" value = "Clear" class="btn btn-secondary"/>
		</form>
	</div>
	<div class="col col-lg-3">
	</div>
</div>
</div>
<%@include file="WEB-INF/footer.jsp" %>
