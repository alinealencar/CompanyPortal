<% session.setAttribute("title", "View Report"); %>
<%@page import="java.util.*"%>
<%@page import="helper.*" %>
<%@page import="dataModel.ReportTemplate" %>
<%@page import="dataModel.Report" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@include file="WEB-INF/header.jsp" %>
<%@include file="WEB-INF/menu.jsp" %>
<script>
$(function(){
	$(document).ready(function(){
		//If a report is selected, show the view report area
		var report = document.getElementById("report").value;
		if(report != ""){
			$("#reportView").show();
		}
	})
});
</script>
<div class="container form-group viewReport">
<div class="container">
	<div class="row align-items-center justify-content-center">
		<span class="${(updateReportSuccess != null) ? 'alert alert-success':''}" role="alert">${updateReportSuccess}</span>
		<span class="${(updateReportError != null) ? 'alert alert-danger':''}" role="alert">${updateReportError}</span>
	</div>
	<br>
	<div class="row align-items-center justify-content-center">
		<h1 class="text-center">VIEW REPORT</h1>
	</div>
	<br><br>
	<form action="ViewReport" method="post" id="viewReportForm">
	<div class="row justify-content-md-center">
	<div class="col col-lg-4">
		<select id = "reportTemplate" class="viewReportDropdown" name="reportTemplate" onChange="this.form.submit()">
			<% 
			//Get the list of reportTemplates from the database
			List<ReportTemplate> allTemplates = DatabaseHelper.getReportTemplates(DatabaseManagement.selectFromTable("report_template")); %>
			<option value="" ${(templateId == null) ? 'selected' : ''}>Select a Report Template</option>
			
			<% //Populate drop down list
				for(int i = 0; i < allTemplates.size(); i++){%>
					<option value ="<%=allTemplates.get(i).getTemplateId()%>"
						<%if(request.getAttribute("templateId") != null 
								&& (int) request.getAttribute("templateId") == allTemplates.get(i).getTemplateId()){%>
							selected
						<%}%>
					><%=allTemplates.get(i).getTemplateName()%></option>
				
				<%}%>
		</select>
	</div>
	<div class="col-12 col-lg-4 viewReportDropdown">
		<select id = "department" class="viewReportDropdown" name = "department" onChange="this.form.submit()" disabled>
				<option value="${(deptName == null) ? 'selected': ''}">Select a Department</option>
				<option value = "${deptName} }" ${(deptName != null) ? 'selected': ''}>${deptName}</option>
		</select>
	</div>
	<div class="col col-lg-4 viewReportDropdown">
		<select id = "report" class="viewReportDropdown" name="report">
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
	</div>
	</div>
		</form>
	<div class="row align-items-center justify-content-center">
		<br><br>
		<span id="viewReportError" class="error">Please select a report template and a report.</span>
	</div>
	<div class="row align-items-center justify-content-center">
		<button type="button" class="btn btn-primary" onclick="clickView()">View</button>&nbsp;&nbsp;<input type="button" value = "Cancel" class="btn btn-secondary" onclick="clickCancelView()">
	</div>
</div>
<form action="EditReport" method="post" id="editReportForm">
<div id="reportView"  class="container" style="display:none;">
<hr>
	<div class="row">
		<span>1. Details:</span>
	</div>
	<br>
	<div class="row justify-content-md-center">
		<div class="col col-lg-8">
			<table class="table table-bordered">
				<tr>
					<td><strong>Report</strong></td>
					<td>${selectedTemplate.templateName}</td>
				</tr>
				<tr>
					<td><strong>Report Title</strong></td>
					<td>${selectedReport.reportTitle}</td>
				</tr>
				<tr>
					<td><strong>Date Created</strong></td>
					<td>${selectedReport.reportDate}</td>
				</tr>
				<tr>
					<td><strong>Department</strong></td>
					<td>${deptName}</td>
				</tr>
			</table>
		</div>
	</div>
	<hr>
	<div class="row">
		<span><strong>2. ${selectedTemplate.section1}:</strong></span>
	</div>
	<br>
	<div class="row justify-content-md-center">
		<div class="col col-lg-6">
			<table class="editReport">
				<tr>
					<td>${selectedTemplate.s1Criteria1}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s1c1" class="editable" name = "s1c1" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s1Crit1Max}">
		         				<option value="${i}" ${(i eq selectedReport.s1Crit1) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
				<tr ${(selectedTemplate.s1Criteria2 == null or selectedTemplate.s1Criteria2 eq "") ? 'style="display:none"' : ''}>
					<td>${selectedTemplate.s1Criteria2}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s1c2" class="editable" name = "s1c2" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s1Crit2Max}">
		         				<option value="${i}" ${(i eq selectedReport.s1Crit2) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
				<tr ${(selectedTemplate.s1Criteria3 == null or selectedTemplate.s1Criteria3 eq "") ? 'style="display:none"' : ''}>
					<td>${selectedTemplate.s1Criteria3}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s1c3" class="editable" name = "s1c3" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s1Crit3Max}">
		         				<option value="${i}" ${(i eq selectedReport.s1Crit3) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
				<tr ${(selectedTemplate.s1Criteria4 == null or selectedTemplate.s1Criteria4 eq "") ? 'style="display:none"' : ''}>
					<td>${selectedTemplate.s1Criteria4}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s1c3" class="editable" name = "s1c4" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s1Crit4Max}">
		         				<option value="${i}" ${(i eq selectedReport.s1Crit4) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
				<tr ${(selectedTemplate.s1Criteria5 == null or selectedTemplate.s1Criteria5 eq "") ? 'style="display:none"' : ''}>
					<td>${selectedTemplate.s1Criteria5}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s1c5" class="editable" name = "s1c5" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s1Crit5Max}">
		         				<option value="${i}" ${(i eq selectedReport.s1Crit5) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div class="col col-lg-6">
			<textarea rows="4" cols="50" class="editable" name="comment1" disabled>${selectedReport.comment1}</textarea>
		</div>
	</div>
	<hr>
	<div class="row">
		<span><strong>3. ${selectedTemplate.section2}:</strong></span>
	</div>
	<br>
	<div class="row justify-content-md-center">
		<div class="col col-lg-6">
			<table class="editReport">
				<tr>
					<td>${selectedTemplate.s2Criteria1}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s2c1" class="editable" name = "s2c1" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s2Crit1Max}">
		         				<option value="${i}" ${(i eq selectedReport.s2Crit1) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
				<tr ${(selectedTemplate.s2Criteria2 == null or selectedTemplate.s2Criteria2 eq "") ? 'style="display:none"' : ''}>
					<td>${selectedTemplate.s2Criteria2}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s2c2" class="editable" name = "s2c2" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s2Crit2Max}">
		         				<option value="${i}" ${(i eq selectedReport.s2Crit2) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
				<tr ${(selectedTemplate.s2Criteria3 == null or selectedTemplate.s2Criteria3 eq "") ? 'style="display:none"' : ''}>
					<td>${selectedTemplate.s2Criteria3}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s2c3" class="editable" name = "s2c3" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s2Crit3Max}">
		         				<option value="${i}" ${(i eq selectedReport.s2Crit3) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div class="col col-lg-6">
			<textarea rows="4" cols="50" class="editable" name="comment2" disabled>${selectedReport.comment2}</textarea>
		</div>
	</div>
	<hr>
	<div class="row">
		<span><strong>4. ${selectedTemplate.section3}:</strong></span>
	</div>
	<br>
	<div class="row justify-content-md-center">
		<div class="col col-lg-6">
			<table class="editReport">
				<tr>
					<td>${selectedTemplate.s3Criteria1}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s3c1" class="editable" name = "s3c1" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s3Crit1Max}">
		         				<option value="${i}" ${(i eq selectedReport.s3Crit1) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
				<tr ${(selectedTemplate.s3Criteria2 == null or selectedTemplate.s3Criteria2 eq "") ? 'style="display:none"' : ''}>
					<td>${selectedTemplate.s3Criteria2}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s3c2" class="editable" name = "s3c2" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s3Crit2Max}">
		         				<option value="${i}" ${(i eq selectedReport.s3Crit2) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
				<tr ${(selectedTemplate.s3Criteria3 == null or selectedTemplate.s3Criteria3 eq "") ? 'style="display:none"' : ''}>
					<td>${selectedTemplate.s3Criteria3}</td>
					<td>Evaluation: </td>
					<td>
						<select id="s3c3" class="editable" name = "s3c3" disabled>
							<c:forEach var = "i" begin = "1" end = "${selectedTemplate.s3Crit3Max}">
		         				<option value="${i}" ${(i eq selectedReport.s3Crit3) ? 'selected' : ''}>${i}</option>
		      				</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</div>
		<div class="col col-lg-6">
			<textarea rows="4" cols="50" name="comment3" class="editable" disabled>${selectedReport.comment3}</textarea>
		</div>
	</div>
	<input type="hidden" value="${selectedReport.reportId}" name="reportId">
	<hr>
	<div class="row align-items-center justify-content-center">
		<input type="submit" value="Save" class="btn btn-success" id="updateReport" style="display:none;"/>
		<button type="button" id="editReport" class="btn btn-primary" onclick="enableEdit()">Edit</button>
		&nbsp;<button type="button" class="btn btn-secondary" onclick="disableEdit()">Cancel</button>
	</div>
</div>
</form>
</div>
<%@include file="WEB-INF/footer.jsp" %>