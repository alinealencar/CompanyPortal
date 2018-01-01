<% session.setAttribute("title", "View Report"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@page import="dataModel.ReportTemplate" %>
<%@page import="dataModel.Report" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
		<select id = "report" name="report">
			<option value="" selected>Select a Report</option>
			
			<% if(request.getAttribute("reports") != null){
					List<Report> resultReports = (List<Report>) request.getAttribute("reports");
					for(int i = 0; i < resultReports.size(); i++){ %>
					<option value="<%=resultReports.get(i).getReportId() %>"
					<%if(request.getAttribute("selectedReport")!= null 
							&& (((Report) request.getAttribute("selectedReport")).getReportId()) == resultReports.get(i).getReportId()) {
							out.println("selected");}%>
					><%=resultReports.get(i).getReportTitle() %></option>
			<% }} %>
		</select>
		<br>
		<input type="submit" value="View" class="btn btn-primary"> <input type="reset" value = "Cancel" class="btn btn-secondary">
	</form>
</div>
<hr>
<div id="reportView">
	<span>1. Details:</span>
	<br>
	<table>
		<tr>
			<td>Report</td>
			<td>${selectedTemplate.templateName}</td>
		</tr>
		<tr>
			<td>Report Title</td>
			<td>${selectedReport.reportTitle}</td>
		</tr>
		<tr>
			<td>Date Created</td>
			<td>${selectedReport.reportDate}</td>
		</tr>
		<tr>
			<td>Department</td>
			<td>${department}</td>
		</tr>
	</table>
	<hr>
	<span>2. ${selectedTemplate.section1}:</span>
	<br>
	<table>
		<tr>
			<td>${selectedTemplate.s1Criteria1}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s1c1" name = "s1c1">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s1Crit1Max}">
         				<option value="${i}" ${(i eq selectedReport.s1Crit1) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
		<tr ${(selectedTemplate.s1Criteria2 == null or selectedTemplate.s1Criteria2 eq "") ? 'style="display:none"' : ''}>
			<td>${selectedTemplate.s1Criteria2}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s1c2" name = "s1c2">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s1Crit2Max}">
         				<option value="${i}" ${(i eq selectedReport.s1Crit2) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
		<tr ${(selectedTemplate.s1Criteria3 == null or selectedTemplate.s1Criteria3 eq "") ? 'style="display:none"' : ''}>
			<td>${selectedTemplate.s1Criteria3}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s1c3" name = "s1c3">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s1Crit3Max}">
         				<option value="${i}" ${(i eq selectedReport.s1Crit3) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
		<tr ${(selectedTemplate.s1Criteria4 == null or selectedTemplate.s1Criteria4 eq "") ? 'style="display:none"' : ''}>
			<td>${selectedTemplate.s1Criteria4}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s1c3" name = "s1c4">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s1Crit4Max}">
         				<option value="${i}" ${(i eq selectedReport.s1Crit4) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
		<tr ${(selectedTemplate.s1Criteria5 == null or selectedTemplate.s1Criteria5 eq "") ? 'style="display:none"' : ''}>
			<td>${selectedTemplate.s1Criteria5}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s1c3" name = "s1c3">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s1Crit5Max}">
         				<option value="${i}" ${(i eq selectedReport.s1Crit5) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<hr>
	<span>3. ${selectedTemplate.section2}:</span>
	<br>
	<table>
		<tr>
			<td>${selectedTemplate.s2Criteria1}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s2c1" name = "s2c1">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s2Crit1Max}">
         				<option value="${i}" ${(i eq selectedReport.s2Crit1) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
		<tr ${(selectedTemplate.s2Criteria2 == null or selectedTemplate.s2Criteria2 eq "") ? 'style="display:none"' : ''}>
			<td>${selectedTemplate.s2Criteria2}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s2c2" name = "s2c2">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s2Crit2Max}">
         				<option value="${i}" ${(i eq selectedReport.s2Crit2) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
		<tr ${(selectedTemplate.s2Criteria3 == null or selectedTemplate.s2Criteria3 eq "") ? 'style="display:none"' : ''}>
			<td>${selectedTemplate.s2Criteria3}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s2c3" name = "s2c3">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s2Crit3Max}">
         				<option value="${i}" ${(i eq selectedReport.s2Crit3) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<hr>
	<span>4. ${selectedTemplate.section3}:</span>
	<br>
	<table>
		<tr>
			<td>${selectedTemplate.s3Criteria1}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s3c1" name = "s3c1">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s3Crit1Max}">
         				<option value="${i}" ${(i eq selectedReport.s3Crit1) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
		<tr ${(selectedTemplate.s3Criteria2 == null or selectedTemplate.s3Criteria2 eq "") ? 'style="display:none"' : ''}>
			<td>${selectedTemplate.s3Criteria2}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s3c2" name = "s3c2">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s3Crit2Max}">
         				<option value="${i}" ${(i eq selectedReport.s3Crit2) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
		<tr ${(selectedTemplate.s3Criteria3 == null or selectedTemplate.s3Criteria3 eq "") ? 'style="display:none"' : ''}>
			<td>${selectedTemplate.s3Criteria3}</td>
			<td>Evaluation: </td>
			<td>
				<select id="s3c3" name = "s3c3">
					<c:forEach var = "i" begin = "0" end = "${selectedTemplate.s3Crit3Max}">
         				<option value="${i}" ${(i eq selectedReport.s3Crit3) ? 'selected' : ''}>${i}</option>
      				</c:forEach>
				</select>
			</td>
		</tr>
	</table>
</div>

<%@include file="WEB-INF/footer.jsp" %>