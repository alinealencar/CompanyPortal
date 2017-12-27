<%@page import="database.DatabaseAccess"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.sql.ResultSet" import = "java.sql.Connection" import = "helper.DatabaseManagement"
    import = "helper.CookieUtilities" import = "helper.HelperUtilities"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<title>
	<% String pageTitle = (String)session.getAttribute("title");%>
	<% out.print(pageTitle); %>

 </title>
</head>
