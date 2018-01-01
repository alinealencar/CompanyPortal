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
import javax.servlet.http.HttpSession;

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
				
				String templateId = request.getParameter("reportTemplate");
				
				//If template is selected
				if(!ValidateInput.isMissing(templateId)){
					//Add the selected templateId to the request scope
					request.setAttribute("templateId", templateId);
					//Get all report names for the selected template
					ResultSet reportsResult = DatabaseManagement.selectReportByTemplate(templateId);
					
					List<Report> reports = DatabaseHelper.getReports(reportsResult);
					
					request.setAttribute("reports", reports);
					
					String reportId = request.getParameter("report");
					//If report is selected
					if(!ValidateInput.isMissing(reportId)){
						Report selectedReport = DatabaseManagement.selectReportById(reportId);
						ReportTemplate selectedTemplate = DatabaseManagement.selectReportTemplateById(templateId);
						
						//Send report and template objects to the request scope
						request.setAttribute("selectedReport", selectedReport);
						request.setAttribute("selectedTemplate", selectedTemplate);
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("enter-report.jsp");
		dispatcher.forward(request, response);
	}

//		response.sendRedirect("enter-report.jsp");
//}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
