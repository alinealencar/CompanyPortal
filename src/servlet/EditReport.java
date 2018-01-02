package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataModel.Report;
import helper.DatabaseManagement;

@WebServlet("/EditReport")
public class EditReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditReport() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.out.println("submeteu pra editar");
			Report updatedReport = new Report();
			updatedReport.setReportId(Integer.parseInt(request.getParameter("reportId")));
			updatedReport.setS1Crit1(Integer.parseInt(request.getParameter("s1c1")));
			if(request.getParameter("s1c2") != null)
				updatedReport.setS1Crit2(Integer.parseInt(request.getParameter("s1c2")));
			if(request.getParameter("s1c3") != null)
				updatedReport.setS1Crit3(Integer.parseInt(request.getParameter("s1c3")));
			if(request.getParameter("s1c4") != null)
				updatedReport.setS1Crit4(Integer.parseInt(request.getParameter("s1c4")));
			if(request.getParameter("s1c5") != null)
				updatedReport.setS1Crit5(Integer.parseInt(request.getParameter("s1c5")));
			updatedReport.setComment1(request.getParameter("comment1"));
			updatedReport.setS2Crit1(Integer.parseInt(request.getParameter("s2c1")));
			if(request.getParameter("s2c2") != null)
				updatedReport.setS2Crit2(Integer.parseInt(request.getParameter("s2c2")));
			if(request.getParameter("s2c3") != null)
				updatedReport.setS2Crit3(Integer.parseInt(request.getParameter("s2c3")));
			updatedReport.setComment2(request.getParameter("comment2"));
			updatedReport.setS3Crit1(Integer.parseInt(request.getParameter("s3c1")));
			if(request.getParameter("s3c2") != null)
				updatedReport.setS3Crit2(Integer.parseInt(request.getParameter("s3c2")));
			if(request.getParameter("s3c3") != null)
				updatedReport.setS3Crit3(Integer.parseInt(request.getParameter("s3c3")));
			updatedReport.setComment3(request.getParameter("comment3"));
			
			//Update report in the database
			boolean update = DatabaseManagement.updateReport(updatedReport);
			
			//Set alert message
			if(update)
				request.setAttribute("updateReportSuccess", "The report was successfully updated.");
			else
				request.setAttribute("updateReportError", "The report was NOT updated.");
			
		} catch (Exception ex){
			ex.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view-report.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
