<<<<<<< HEAD
<<<<<<< HEAD

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Entry</title>
</head>
 <% Connection conn = DatabaseAccess.connectDataBase(); %>

<% session.setAttribute("title", "Group Entry"); %>
<%@include file="WEB-INF/header.jsp" %>



=======
<%session.setAttribute("title", "Group Entry"); %>
>>>>>>> 995a804f883ce2f2bca40a468a0dec775d6f926b
<% 	
	//String selectedDept = "";
	ResultSet rsDept = null; 
	ResultSet rsEmp1 = null; 
	ResultSet rsEmp2 = null;
	ResultSet rsEmp3 = null;
	ResultSet rsEmp4 = null;
	ResultSet rsEmp5 = null;
	ResultSet rsEmp6 = null;
	
	//result set to select departments
	rsDept = DatabaseManagement.selectFromTable("department", conn);
	
	/*rsEmp1 = DatabaseManagement.selectFromTable("employee", conn);
	rsEmp2 = DatabaseManagement.selectFromTable("employee", conn);
	rsEmp3 = DatabaseManagement.selectFromTable("employee", conn);
	rsEmp4 = DatabaseManagement.selectFromTable("employee", conn);
	rsEmp5 = DatabaseManagement.selectFromTable("employee", conn);
	rsEmp6 = DatabaseManagement.selectFromTable("employee", conn);*/
	
	String selectedDept = CookieUtilities.getCookieValue(request, "deptName", "");
%>

<%@include file="WEB-INF/menu.jsp" %>
<span>
	<%	//show success message if database insertion is successful
		if((String) session.getAttribute("groupInsertSuccess") != null)
		out.println((String) session.getAttribute("groupInsertSuccess"));
	
		////show error message if database insertion failed
		if((String) session.getAttribute("groupInsertError") != null)
		out.println((String) session.getAttribute("groupInsertError"));
	%>
	
	<script>
		function checkEmployeeDropDown(){
		  if(document.getElementById("emp1").value!="")
		  	document.getElementById("emp2").disabled=false;
		  if(document.getElementById("emp2").value!="")
			document.getElementById("emp3").disabled=false;
		  if(document.getElementById("emp3").value!="")
			document.getElementById("emp4").disabled=false;
		  if(document.getElementById("emp4").value!="")
		   	document.getElementById("emp5").disabled=false;
		  if(document.getElementById("emp5").value!="")
			document.getElementById("emp6").disabled=false;
		  //else
		    //document.getElementById('servername').disabled=true;
		}
	</script>
</span>
	<h1>GROUP ENTRY</h1>
	
		<form method = "post" action = "GroupEntryHelper">
			<label>Department: </label>
			<select id = "deptName" name = "deptName" onchange = "this.form.submit()">
				<option value="">Department</option>
				<% //populate department drop down list 
					while(rsDept.next()){ %>
        		<option value ="<%=rsDept.getString("dept_name")%>" selected = "<%=selectedDept%>"><%=rsDept.getString("dept_name")%></option><%}%> 
			</select>
		
			<% //show error message if no department is selected
				if((String) session.getAttribute("errorDepartment") != null){
					out.println((String) session.getAttribute("errorDepartment"));
		   		}
			%>
			<br>
	
			<label>Group Name: </label><input type = "text" name = "groupName" value = "<%if((String) session.getAttribute("groupName") != null) out.println((String) session.getAttribute("groupName"));%>"/>
			
			<% 
				//show error message if no group name is entered
				if((String) session.getAttribute("errorGroupName") != null){
					out.println((String) session.getAttribute("errorGroupName"));
		   		}
			%>
			<br>
		
			<%
				//selectedDept = request.getParameter("option");
				//result set to retrieve employees based on selected department 
				rsEmp1 = DatabaseManagement.selectEmployees(selectedDept, conn);
				rsEmp2 = DatabaseManagement.selectEmployees(selectedDept, conn);
				rsEmp3 = DatabaseManagement.selectEmployees(selectedDept, conn);
				rsEmp4 = DatabaseManagement.selectEmployees(selectedDept, conn);
				rsEmp5 = DatabaseManagement.selectEmployees(selectedDept, conn);
				rsEmp6 = DatabaseManagement.selectEmployees(selectedDept, conn);
		
				 //show error message if no employee is selected
				 if((String) session.getAttribute("errorEmp1") != null){
					 out.println((String) session.getAttribute("errorEmp1"));
		   		}
			%>
			<br>
		
			<label>Employee 1:</label>
			<select id="emp1" name = "emp1" onchange = "checkEmployeeDropDown()">
				<option value="">Employee</option>
				<%	//populate employee 1 drop down list
					while(rsEmp1.next()){ %>
        		<option value="<%=rsEmp1.getString("firstname") + " " + 
					rsEmp1.getString("lastname")%>"><%=rsEmp1.getString("firstname") + " " 
        			+ rsEmp1.getString("lastname")%></option><%}%> 
			</select>
	
			<label>Employee 2:</label>
			<select id="emp2" name = "emp2" onchange = "checkEmployeeDropDown()" disabled>
				<option value="">Employee</option>
				<%	//populate employee 2 drop down list
					while(rsEmp2.next()){ %>
        		<option value="<%=rsEmp2.getString("firstname") + " " + 
					rsEmp2.getString("lastname")%>"><%=rsEmp2.getString("firstname") + " " + 
        			rsEmp2.getString("lastname")%></option><%}%> 
			</select>
		
			<label>Employee 3:</label>
			<select id="emp3" name = "emp3" onchange = "checkEmployeeDropDown()" disabled>
				<option value="">Employee</option>
				<%	//populate employee 3 drop down list
					while(rsEmp3.next()){ %>
        		<option value="<%=rsEmp3.getString("firstname") + " " + 
					rsEmp3.getString("lastname")%>"><%=rsEmp3.getString("firstname") + " " + 
        			rsEmp3.getString("lastname")%></option><%}%> 
			</select>
			<br>
	
			<label>Employee 4:</label>
			<select id="emp4" name = "emp4" onchange = "checkEmployeeDropDown()" disabled>
				<option value="">Employee</option>
				<%	//populate employee 4 drop down list
					while(rsEmp4.next()){ %>
        		<option value="<%=rsEmp4.getString("firstname") + " " + 
					rsEmp4.getString("lastname")%>"><%=rsEmp4.getString("firstname") + " " + 
        			rsEmp4.getString("lastname")%></option><%}%> 
			</select>
	
			<label>Employee 5:</label>
			<select id="emp5" name = "emp5" onchange = "checkEmployeeDropDown()" disabled>
				<option value="">Employee</option>
				<%	//populate employee 5 drop down list
					while(rsEmp5.next()){ %>
        		<option value="<%=rsEmp5.getString("firstname") + " " + 
					rsEmp5.getString("lastname")%>"><%=rsEmp5.getString("firstname") + " " + 
        			rsEmp5.getString("lastname")%></option><%}%> 
			</select>
	
			<label>Employee 6:</label>
			<select id="emp6" name = "emp6" disabled>
				<option value="">Employee</option>
				<%	//populate employee 6 drop down list
					while(rsEmp6.next()){ %>
        	<option value="<%=rsEmp6.getString("firstname") + " " + 
					rsEmp6.getString("lastname")%>"><%=rsEmp6.getString("firstname") + " " + 
        			rsEmp6.getString("lastname")%></option><%}%> 
			</select>
			<br>
	
			<input type = "submit" value = "Submit" onclick = "form.action = 'GroupEntry'" />
			<input type = "reset" value = "Cancel" />
		</form>
	
<%@include file="WEB-INF/footer.jsp" %>