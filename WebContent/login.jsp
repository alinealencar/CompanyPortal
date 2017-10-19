<% session.setAttribute("title", "Login"); %>
<%@include file="WEB-INF/header.jsp" %>

<body>
	<span>
	<% if((String) session.getAttribute("error") != null){
			out.println((String) session.getAttribute("error"));
		}
	%>
	</span>
	<form action="Login" method="post">
		User: <input type="text" name="username"/>
		<br>
		Password: <input type="password" name="password"/>
		<br>
		<input type="checkbox" name="rememberMe" value="rememberMe"> Remember Me
		<br>
		<input type="submit" value="submit">
	</form>
	
<%@include file="WEB-INF/footer.jsp" %>