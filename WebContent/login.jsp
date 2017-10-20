<% session.setAttribute("title", "Login"); %>
<%@include file="WEB-INF/header.jsp" %>

<body class="bg-light">
<br>
<br>
	<div class="container">
		<div class="row">
			<div class="col col-lg-4">
			</div>
			<div class="col col-lg-4">
				<h1 class="text-center">Log In</h1>
				<br>
				
				<% if((String) session.getAttribute("error") != null){
						out.println("<span  class = \"alert alert-danger\" role = \"alert\">" 
							+ (String) session.getAttribute("error") + "</span>");
					}
				%>
				
				<br>
				<br>
				<form action="Login" method="post">
					<div class="form-group">
						<label for="usernameField">User</label>
						<input type="text" name="username" class="form-control" id="usernameField" placeholder="Username"/>
					</div>
					<div class="form-group">
						<label for="passwordField">Password</label>
						<input type="password" name="password" class="form-control" id="passwordField" placeholder="Password"/>
					</div>
					<div class="form-check">
						<label class="form-check-label">
							<input type="checkbox" name="rememberMe" value="rememberMe" class="form-check-input">
							Remember Me
						</label>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
			<div class="col col-lg-4">
			</div>
		</div>
	</div>
	
<%@include file="WEB-INF/footer.jsp" %>