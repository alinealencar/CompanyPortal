<% session.setAttribute("title", "View Report"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@page import="dataModel.ReportTemplate" %>
<%@page import="dataModel.Report" %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>

<div class="container form-group">
	<div class="row align-items-center justify-content-center">
		<h1 class="text-center">VIEW REPORT</h1>
	</div>
	<form action="ViewReport" method="post">
		<select id = "department" name = "department" onChange="this.form.submit()">
				<% 
				//Get the list of departments from the database
				String[] deptList = HelperUtilities.getStringFromResultSet(DatabaseManagement.selectFromTable("department"), "dept_name");
				%>
				
				<option value="${(department == null) ? 'selected': ''}">Select a Department</option>
				
				<%
				//Populate drop down list
				for(int i = 0; i < deptList.length; i++){%>
					<option value ="<%=deptList[i] %>" 
						<%if(request.getAttribute("department")!= null 
								&& request.getAttribute("department").equals(deptList[i])) {
							out.println("selected");}%>
					><%=deptList[i] %></option>
				<% } %>
		</select>
		<br>
		<select id = "reportTemplate" name="reportTemplate" onChange="this.form.submit()">
			<option value="" selected>Select a Report Template</option>
			
			<% if(request.getAttribute("reportTemplates") != null){
					List<ReportTemplate> resultTemplates = (List<ReportTemplate>) request.getAttribute("reportTemplates");
					for(int i = 0; i < resultTemplates.size(); i++){ %>
					<option value="<%=resultTemplates.get(i).getTemplateId() %>"
					<%if(request.getAttribute("templateId")!= null 
							&& Integer.parseInt((String) request.getAttribute("templateId")) == resultTemplates.get(i).getTemplateId()) {
							out.println("selected");}%>
					><%=resultTemplates.get(i).getTemplateName() %></option>
			<% }} %>
		</select>
		<br>
		<select id = "report" name="report" onChange="this.form.submit()">
			<option value="" selected>Select a Report</option>
			
			<% if(request.getAttribute("reports") != null){
					List<Report> resultReports = (List<Report>) request.getAttribute("reports");
					for(int i = 0; i < resultReports.size(); i++){ %>
					<option value="<%=resultReports.get(i).getReportId() %>"><%=resultReports.get(i).getReportTitle() %></option>
			<% }} %>
		</select>
		<br>
		<input type="submit" value="View" class="btn btn-primary"> <input type="reset" value = "Cancel" class="btn btn-secondary">
	</form>
</div>

<div id="viewReport">
		
</div>

<%@include file="WEB-INF/footer.jsp" %>