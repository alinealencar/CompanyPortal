package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataModel.ReportTemplate;
import helper.DatabaseHelper;
import helper.DatabaseManagement;
import helper.ValidateInput;

@WebServlet("/CreateReportTemplate")
public class CreateReportTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateReportTemplate() {
    	super();	
    }
    
    /**
     * This method is called whenever this servlet receives a GET request.
     * It automatically calls the doPost method.
     * 
     * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
     * @see #doPost(HttpServletRequest, HttpServletResponse)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		ReportTemplate rt = new ReportTemplate();
		try{
			//Set ReportTemplate attributes with the input from the form
			rt.setTemplateName(request.getParameter("templateName"));
			rt.setTemplateDate(java.sql.Date.valueOf(request.getParameter("templateDate")));
			rt.setSection1(request.getParameter("section1"));
			rt.setSection2(request.getParameter("section2"));
			rt.setSection3(request.getParameter("section3"));
			rt.setS1Criteria1(request.getParameter("s1c1"));
			rt.setS1Criteria2(request.getParameter("s1c2"));
			rt.setS1Criteria3(request.getParameter("s1c3"));
			rt.setS1Criteria4(request.getParameter("s1c4"));
			rt.setS1Criteria5(request.getParameter("s1c5"));
			rt.setS2Criteria1(request.getParameter("s2c1"));
			rt.setS2Criteria2(request.getParameter("s2c2"));
			rt.setS2Criteria3(request.getParameter("s2c3"));
			rt.setS3Criteria1(request.getParameter("s3c1"));
			rt.setS3Criteria2(request.getParameter("s3c2"));
			rt.setS3Criteria3(request.getParameter("s3c3"));
			rt.setS1Crit1Max(Integer.parseInt(request.getParameter("s1c1m")));
			if (!ValidateInput.isMissing(request.getParameter("s1c2m")))
				rt.setS1Crit2Max(Integer.parseInt(request.getParameter("s1c2m")));
			if (!ValidateInput.isMissing(request.getParameter("s1c3m")))
				rt.setS1Crit3Max(Integer.parseInt(request.getParameter("s1c3m")));
			if (!ValidateInput.isMissing(request.getParameter("s1c4m")))
				rt.setS1Crit4Max(Integer.parseInt(request.getParameter("s1c4m")));
			if (!ValidateInput.isMissing(request.getParameter("s1c5m")))
				rt.setS1Crit5Max(Integer.parseInt(request.getParameter("s1c5m")));
			rt.setS2Crit1Max(Integer.parseInt(request.getParameter("s2c1m")));
			if (!ValidateInput.isMissing(request.getParameter("s2c2m")))
				rt.setS2Crit2Max(Integer.parseInt(request.getParameter("s2c2m")));
			if (!ValidateInput.isMissing(request.getParameter("s2c3m")))
				rt.setS2Crit3Max(Integer.parseInt(request.getParameter("s2c3m")));
			rt.setS3Crit1Max(Integer.parseInt(request.getParameter("s3c1m")));
			if (!ValidateInput.isMissing(request.getParameter("s3c2m")))
				rt.setS3Crit2Max(Integer.parseInt(request.getParameter("s3c2m")));
			if (!ValidateInput.isMissing(request.getParameter("s3c3m")))
				rt.setS3Crit3Max(Integer.parseInt(request.getParameter("s3c3m")));
			rt.setDeptId(DatabaseHelper.getDeptId(request.getParameter("department")));
			
			//Insert ReportTemplate into the database
        	if(DatabaseManagement.insertReportTemplate(rt))
        		request.setAttribute("templateInsertSuccess", "Template " + rt.getTemplateName() + " has been successfully added to the system.");
        	else
        		request.setAttribute("templateInsertFail", "Template " + rt.getTemplateName() + " has NOT been added to the system.");
        }
        catch(Exception e){
        	request.setAttribute("templateInsertFail", "Template " + rt.getTemplateName() + " has NOT been added to the system.");
			e.printStackTrace();
		}
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("create-report.jsp");
        dispatcher.forward(request, response);
        
    }

	/**
	 * This method is called whenever this servlet receives a POST request.
	 * It is responsible for creating a report template and inserting it into the database.
	 * 
	 * @param	request	HttpServletRequest object
     * @param	response HttpServletResponse object
     * @return	void
     * @exception	ServletException
     * @exception	 IOException on input error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
