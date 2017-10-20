<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.sql.ResultSet" import = "java.sql.Connection" import = "helper.DatabaseManagement"
    import = "helper.CookieUtilities" import="database.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Entry</title>
</head>
 <% Connection conn = DatabaseAccess.connectDataBase();
	ResultSet rsDept = null; 
	ResultSet rsEmp1 = null; 
	ResultSet rsEmp2 = null;
	ResultSet rsEmp3 = null;
	ResultSet rsEmp4 = null;
	ResultSet rsEmp5 = null;
	ResultSet rsEmp6 = null;
	rsDept = DatabaseManagement.selectFromTable("department", conn);	
	
	String selectedDept = CookieUtilities.getCookieValue(request, "deptName", "");
%>
<body>
	<h1>GROUP ENTRY</h1>
	
	<form method = "post" action = "GroupEntry">
		<label>Department: </label>
		<select id = "deptName" name = "deptName" onchange = this.form.submit()>
			<option value="">Department</option>
			<%while(rsDept.next()){ %>
        	<option value ="<%=rsDept.getString("dept_name")%>" selected = "<%=selectedDept%>"><%=rsDept.getString("dept_name")%></option><%}%> 
		</select><br>
	
		<label>Group Name: </label><input type = "text" name = "groupName" /><br>
	
		
		<%
			//result set to retrieve employees based on selected department 
			rsEmp1 = DatabaseManagement.selectEmployees(selectedDept, conn);
			rsEmp2 = DatabaseManagement.selectEmployees(selectedDept, conn);
			rsEmp3 = DatabaseManagement.selectEmployees(selectedDept, conn);
			rsEmp4 = DatabaseManagement.selectEmployees(selectedDept, conn);
			rsEmp5 = DatabaseManagement.selectEmployees(selectedDept, conn);
			rsEmp6 = DatabaseManagement.selectEmployees(selectedDept, conn);
		%>
		
	
		<label>Employee 1:</label>
		<select id="emp1" name = "emp1">
			<option value="">Employee</option>
			<%while(rsEmp1.next()){ %>
        	<option value="<%=rsEmp1.getString("firstname") + " " + rsEmp1.getString("lastname")%>"><%=rsEmp1.getString("firstname") + " " + rsEmp1.getString("lastname")%></option><%}%> 
		</select>
	
		<label>Employee 2:</label>
		<select id="emp2" name = "emp2">
			<option value="">Employee</option>
			<%while(rsEmp2.next()){ %>
        	<option value="<%=rsEmp2.getString("firstname") + " " + rsEmp2.getString("lastname")%>"><%=rsEmp2.getString("firstname") + " " + rsEmp2.getString("lastname")%></option><%}%> 
		</select>
		
		<label>Employee 3:</label>
		<select id="emp3" name = "emp3">
			<option value="">Employee</option>
			<%while(rsEmp3.next()){ %>
        	<option value="<%=rsEmp3.getString("firstname") + " " + rsEmp3.getString("lastname")%>"><%=rsEmp3.getString("firstname") + " " + rsEmp3.getString("lastname")%></option><%}%> 
		</select><br>
	
		<label>Employee 4:</label>
		<select id="emp4" name = "emp4">
			<option value="">Employee</option>
			<%while(rsEmp4.next()){ %>
        	<option value="<%=rsEmp4.getString("firstname") + " " + rsEmp4.getString("lastname")%>"><%=rsEmp4.getString("firstname") + " " + rsEmp4.getString("lastname")%></option><%}%> 
		</select>
	
		<label>Employee 5:</label>
		<select id="emp5" name = "emp5">
			<option value="">Employee</option>
			<%while(rsEmp5.next()){ %>
        	<option value="<%=rsEmp5.getString("firstname") + " " + rsEmp5.getString("lastname")%>"><%=rsEmp5.getString("firstname") + " " + rsEmp5.getString("lastname")%></option><%}%> 
		</select>
	
		<label>Employee 6:</label>
		<select id="emp6" name = "emp6">
			<option value="">Employee</option>
			<%while(rsEmp6.next()){ %>
        	<option value="<%=rsEmp6.getString("firstname") + " " + rsEmp6.getString("lastname")%>"><%=rsEmp6.getString("firstname") + " " + rsEmp6.getString("lastname")%></option><%}%> 
		</select><br>
	
		<input type = "submit" value = "Submit" />
		<input type = "reset" value = "Cancel" />
	</form>
</body>
</html>