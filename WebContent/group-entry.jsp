<% session.setAttribute("title", "Group Entry"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container">
	<div class="row">
		<div class="col col-lg-2">
		</div>
		<div class="col col-lg-7">
			<div>
				<span class="${(groupInsertSuccess != null) ? 'alert alert-success':''}" role="alert">${groupInsertSuccess}</span>
				<span class="${(groupInsertError != null) ? 'alert alert-danger':''}" role="alert">${groupInsertError}</span>
			</div>
			<br>
			<br>
			<h1 class="text-center">GROUP ENTRY</h1>
			<br>
			<form method = "post" action = "GroupEntryHelper">
			<div class="form-group">
				<label for="department">Department: </label>
				<select id = "department" name = "department" onChange="this.form.submit()">
					<% 
						//Get the list of departments from the database
						String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
						
						//check if a department is selected
						if(session.getAttribute("department") == null || session.getAttribute("department").equals("")){ %>
							<option value="" selected>Department</option>
							
						<% //Populate drop down list
							for(int i = 0; i < deptList.length; i++) {
								out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");
							}
						}
						else{
							%>
							<option value="">Department</option>
							<%							//Populate drop down list
							for(int i = 0; i < deptList.length; i++) {
								if(session.getAttribute("department").equals(deptList[i]))
									out.print("<option value =\"" + deptList[i] + "\"selected>" + deptList[i] + "</option>");
								else
									out.print("<option value =\"" + deptList[i] + "\">" + deptList[i] + "</option>");
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
				<span class="${(errorGroupName != null) ? 'text-danger':''}">${errorGroupName}</span>
				<br>
			</div>
			
			<span class="${(errorEmp != null) ? 'text-danger':''}">${errorEmp}</span>
			<div class="form-group">
				<label>Employee 1:</label>
				<select style="margin-right:10%;" id="emp1" name = "emp1">
						<%
						String[] empFirstName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "firstname");
				 		String[] empLastName = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectEmployees((String) session.getAttribute("department")), "lastname");
				 	
				 		
						//check if an employee selected,
					 	if(session.getAttribute("emp1") != null){%>
					 		<option value="">Employee</option>
					 	
					 		<%//populate dropdown list
					 		for(int i = 0; i < empFirstName.length; i++){
					 			if(((String) session.getAttribute("emp1")).contains(empFirstName[i]) && ((String) session.getAttribute("emp1")).contains(empLastName[i])){
    								out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" SELECTED >"+ empFirstName[i] + " " + empLastName[i] +"</option>");
								}
								else{
    								out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
					 			}
					 		}
					  	}
						//if no employee is selected, populate dropdown list
					  	else { %>
							<option value = "" selected>Employee</option>
							<% for(int i = 0; i < empFirstName.length; i++){
								out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
								}
						}
						%>
				</select>
	
			<label>Employee 2:</label>
				<select id="emp2" name = "emp2">
					<% //check if a second employee is selected
					if(session.getAttribute("emp2") != null){%>
			 		<option value="">Employee</option>
			 	
			 		<%//populate dropdown list
			 		for(int i = 0; i < empFirstName.length; i++){
			 			if(((String) session.getAttribute("emp2")).contains(empFirstName[i]) && ((String) session.getAttribute("emp2")).contains(empLastName[i])){
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" SELECTED >"+ empFirstName[i] + " " + empLastName[i] +"</option>");
					}
						else{
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
			 			}
			 		}
			  	}
				//if no employee is selected, populate dropdown list
			  	else { %>
					<option value = "" selected>Employee</option>
					<% for(int i = 0; i < empFirstName.length; i++){
						out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
						}
				}
				%>
				</select>
			</div>
			<div class="form-group">
				<label>Employee 3:</label>
				<select style="margin-right:10%;" id="emp3" name = "emp3">
					<% //check if a second employee is selected
					if(session.getAttribute("emp3") != null){%>
			 		<option value="">Employee</option>
			 	
			 		<%//populate dropdown list
			 		for(int i = 0; i < empFirstName.length; i++){
			 			if(((String) session.getAttribute("emp3")).contains(empFirstName[i]) && ((String) session.getAttribute("emp3")).contains(empLastName[i])){
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" SELECTED >"+ empFirstName[i] + " " + empLastName[i] +"</option>");
					}
						else{
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
			 			}
			 		}
			  	}
				//if no employee is selected, populate dropdown list
			  	else { %>
					<option value = "" selected>Employee</option>
					<% for(int i = 0; i < empFirstName.length; i++){
						out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
						}
				}
				%>
				</select>

				<label>Employee 4:</label>
				<select id="emp4" name = "emp4">
					<% //check if a second employee is selected
					if(session.getAttribute("emp4") != null){%>
			 		<option value="">Employee</option>
			 	
			 		<%//populate dropdown list
			 		for(int i = 0; i < empFirstName.length; i++){
			 			if(((String) session.getAttribute("emp4")).contains(empFirstName[i]) && ((String) session.getAttribute("emp4")).contains(empLastName[i])){
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" SELECTED >"+ empFirstName[i] + " " + empLastName[i] +"</option>");
					}
						else{
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
			 			}
			 		}
			  	}
				//if no employee is selected, populate dropdown list
			  	else { %>
					<option value = "" selected>Employee</option>
					<% for(int i = 0; i < empFirstName.length; i++){
						out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
						}
				}
				%>
				</select>
			</div>
			
			<div class="form-group">
				<label>Employee 5:</label>
				<select style="margin-right:10%;" id="emp5" name = "emp5">
					<% //check if a second employee is selected
					if(session.getAttribute("emp5") != null){%>
			 		<option value="">Employee</option>
			 	
			 		<%//populate dropdown list
			 		for(int i = 0; i < empFirstName.length; i++){
			 			if(((String) session.getAttribute("emp5")).contains(empFirstName[i]) && ((String) session.getAttribute("emp5")).contains(empLastName[i])){
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" SELECTED >"+ empFirstName[i] + " " + empLastName[i] +"</option>");
					}
						else{
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
			 			}
			 		}
			  	}
				//if no employee is selected, populate dropdown list
			  	else { %>
					<option value = "" selected>Employee</option>
					<% for(int i = 0; i < empFirstName.length; i++){
						out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
						}
				}
				%>
				</select>
				
				<label>Employee 6:</label>
				<select id="emp6" name = "emp6">
					<% //check if a second employee is selected
					if(session.getAttribute("emp6") != null){%>
			 		<option value="">Employee</option>
			 	
			 		<%//populate dropdown list
			 		for(int i = 0; i < empFirstName.length; i++){
			 			if(((String) session.getAttribute("emp6")).contains(empFirstName[i]) && ((String) session.getAttribute("emp6")).contains(empLastName[i])){
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" SELECTED >"+ empFirstName[i] + " " + empLastName[i] +"</option>");
					}
						else{
							out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
			 			}
			 		}
			  	}
				//if no employee is selected, populate dropdown list
			  	else { %>
					<option value = "" selected>Employee</option>
					<% for(int i = 0; i < empFirstName.length; i++){
						out.print("<option value=\""+ empFirstName[i] + " " + empLastName[i] +"\" >"+ empFirstName[i] + " " + empLastName[i] + "</option>");
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
