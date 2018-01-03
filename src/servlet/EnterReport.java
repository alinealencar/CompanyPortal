package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataModel.Group;
import dataModel.Report;
import dataModel.ReportTemplate;
import helper.DatabaseHelper;
import helper.DatabaseManagement;
import helper.ValidateInput;

@WebServlet("/EnterReport")
public class EnterReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnterReport() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String department = request.getParameter("department");
		String templateId = request.getParameter("reportTemplate");
		String reportTitle = request.getParameter("reportTitle");
		String dateInput = request.getParameter("date");
		String date = null;
		String group = request.getParameter("group");
		String employee = request.getParameter("employee");

		
		//when department is selected, related template name is generated in combobox		
		try{
			//If department is selected
			if(!ValidateInput.isMissing(department)){
				request.setAttribute("department", department);
				//Get all templates attached to the selected department
				ResultSet templatesResult = DatabaseManagement.selectReportTemplateByDepartment(department);
				
				List<ReportTemplate> templates = DatabaseHelper.getReportTemplates(templatesResult);
				
				//Add all report templates for a certain department to the request scope
				request.setAttribute("reportTemplates", templates);
				
				//Get all groups attached to the selected department
				ResultSet groupeResult = DatabaseManagement.selectGroupByDepartment(department);				
				List<Group> groups = DatabaseHelper.getGroups(groupeResult);
				
				//Add all groups for a certain department to the request scope
				request.setAttribute("groups", groups);
				
				
				//If template is selected
				if(!ValidateInput.isMissing(templateId)){
					//Add the selected templateId to the request scope
					request.setAttribute("templateId", templateId);
					
					//get selected template
					ReportTemplate selectedTemplate = DatabaseManagement.selectReportTemplateById(templateId);
					
					//calculate Total max value
					ReportTemplate st = selectedTemplate;
					int totalMax = st.getS1Crit1Max() + st.getS1Crit2Max() + st.getS1Crit3Max() + st.getS1Crit4Max() + st.getS1Crit5Max() +
									st.getS2Crit1Max() + st.getS2Crit2Max() + st.getS2Crit3Max() +
									st.getS3Crit1Max() + st.getS3Crit2Max() + st.getS3Crit3Max();

					//Send report and template objects to the request scope
					request.setAttribute("selectedTemplate", selectedTemplate);
					
					//Send totalMax value to the request scope
					request.setAttribute("totalMax", totalMax);
	
					//Get all report names for the selected template
//					ResultSet reportsResult = DatabaseManagement.selectReportByTemplate(templateId);
//					
//					List<Report> reports = DatabaseHelper.getReports(reportsResult);
//					
//					request.setAttribute("reports", reports);
//					
//					String reportId = request.getParameter("report");
//					//If report is selected
//					if(!ValidateInput.isMissing(reportId)){
//						Report selectedReport = DatabaseManagement.selectReportById(reportId);
//						ReportTemplate selectedTemplate = DatabaseManagement.selectReportTemplateById(templateId);
//						
//						//Send report and template objects to the request scope
//						request.setAttribute("selectedReport", selectedReport);
//						request.setAttribute("selectedTemplate", selectedTemplate);
//					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//Check if a date was submitted
		if(!ValidateInput.isMissing(dateInput)){
			//change ReportDate format
			SimpleDateFormat formatterIn = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat formatterOut = new SimpleDateFormat("yyyy-MM-dd");
			try{
				//change string(user input) to Date
				Date strToDate = new Date((formatterIn.parse(dateInput)).getTime());
	
				//change date format with slash and change order
				date = formatterOut.format(strToDate);
			}catch (ParseException e) {
	            e.printStackTrace();
	        }
			
			
			Report report = new Report();
			try{
				System.out.println("title 1 " + reportTitle);
				System.out.println("Date " + request.getParameter("date"));
				System.out.println("Parse Date " + date);
				System.out.println("title request " + request.getParameter("reportTitle"));
				System.out.println("Start");
				//Set ReportTemplate attributes with the input from the form
				report.setReportTitle(request.getParameter("reportTitle"));
				report.setReportDate(java.sql.Date.valueOf(date));
				report.setReportType(request.getParameter("reportType"));
				report.setS1Crit1(Integer.parseInt(request.getParameter("s1c1e")));
				System.out.println("title 2 " + reportTitle);
				if (!ValidateInput.isMissing(request.getParameter("s1c2e")))
					report.setS1Crit2(Integer.parseInt(request.getParameter("s1c2e")));
				if (!ValidateInput.isMissing(request.getParameter("s1c3e")))
					report.setS1Crit3(Integer.parseInt(request.getParameter("s1c3e")));
				if (!ValidateInput.isMissing(request.getParameter("s1c4e")))
					report.setS1Crit4(Integer.parseInt(request.getParameter("s1c4e")));
				if (!ValidateInput.isMissing(request.getParameter("s1c5e")))
					report.setS1Crit5(Integer.parseInt(request.getParameter("s1c5e")));
				report.setComment1(request.getParameter("comment1"));
				report.setS2Crit1(Integer.parseInt(request.getParameter("s2c1e")));
				if (!ValidateInput.isMissing(request.getParameter("s2c2e")))
					report.setS2Crit2(Integer.parseInt(request.getParameter("s2c2e")));
				if (!ValidateInput.isMissing(request.getParameter("s2c3e")))
					report.setS2Crit3(Integer.parseInt(request.getParameter("s2c3e")));
				report.setComment2(request.getParameter("comment2"));
				report.setS3Crit1(Integer.parseInt(request.getParameter("s3c1e")));
				if (!ValidateInput.isMissing(request.getParameter("s3c2e")))
					report.setS3Crit2(Integer.parseInt(request.getParameter("s3c2e")));
				if (!ValidateInput.isMissing(request.getParameter("s3c3e")))
					report.setS3Crit3(Integer.parseInt(request.getParameter("s3c3e")));
				report.setComment3(request.getParameter("comment3"));
				report.setTemplateId(Integer.parseInt(request.getParameter("reportTemplate")));
				System.out.println("title 2 " + reportTitle);
				System.out.println("Title 3 object " + report.toString());
	
				
				//Insert ReportTemplate into the database
	        	if(DatabaseManagement.insertReport(report))
	        		request.setAttribute("reportInsertSuccess", "Report " + report.getReportTitle() + " has been successfully added to the system.");
	        	else
	        		request.setAttribute("reportInsertFail", "Report " + report.getReportTitle() + " has NOT been added to the system. DB");
	        }
	        catch(Exception e){
	        	request.setAttribute("reportInsertFail", "Report " + report.getReportTitle() + " has NOT been added to the system. EX");
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("enter-report.jsp");
		dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
