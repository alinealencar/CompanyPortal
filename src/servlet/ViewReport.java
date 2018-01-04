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

@WebServlet("/ViewReport")
public class ViewReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewReport() {
        super();
    }

    /**
	 * This method is called whenever this servlet receives a GET request.
	 * It is responsible for filtering the reportTemplate and report drop downs and showing a
	 * selected report.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{
			//If template is selected
			if(!ValidateInput.isMissing(request.getParameter("reportTemplate"))){
				String templateId = request.getParameter("reportTemplate");

				//Add selected template to the request scope
				request.setAttribute("templateId", Integer.parseInt(templateId));
				
				//Get department associated with the template
				ReportTemplate selectedTemplate = DatabaseManagement.selectReportTemplateById(templateId);

				//Add department associated to the selected template to the request scope
				request.setAttribute("deptName", DatabaseHelper.getDeptNameById(selectedTemplate.getDeptId()));
				
				//Get all report names for the selected template
				ResultSet reportsResult = DatabaseManagement.selectReportByTemplate(templateId);
				List<Report> reports = DatabaseHelper.getReports(reportsResult);
				request.setAttribute("reports", reports);
				
				String reportFor = null;
				Report selectedReport = null;
				if(!ValidateInput.isMissing(request.getParameter("report"))){
					String reportId = request.getParameter("report");
				
					selectedReport = DatabaseManagement.selectReportById(reportId);

					reportFor = DatabaseHelper.getReportFor(selectedReport);
				}		
				//Send report and template objects to the request scope
				request.setAttribute("selectedReport", selectedReport);
				request.setAttribute("selectedTemplate", selectedTemplate);
				request.setAttribute("reportFor", reportFor);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view-report.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method is called whenever this servlet receives a POST request.
	 * It calls the doGet method.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
