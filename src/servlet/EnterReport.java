package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String date = request.getParameter("date");
		
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
		

		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("enter-report.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
