<%@page import="database.DatabaseAccess"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.sql.ResultSet" import = "java.sql.Connection" import = "helper.DatabaseManagement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Entry</title>
</head>
<% 	Connection conn = DatabaseAccess.connectDataBase();
	ResultSet rs = null; 
	rs = DatabaseManagement.selectFromTable("department", conn);
	%>
<body>
	<h1>GROUP ENTRY</h1>
	<form method = "post" action = "GroupEntry">
	<label>Department: </label>
	<select>
		<%while(rs.next()){ %>
        <option value="<%=rs.getString("dept_name")%>"><%=rs.getString("dept_name")%></option><%}%> 
	</select><br>
	<label>Group Name: </label><input type = "text" name = "groupName" /><br>
	<input type = "submit" value = "Submit" />
	<input type = "reset" value = "Cancel" />
	</form>
</body>
</html>