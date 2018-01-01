package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EnterReport")
public class EnterReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnterReport() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//when template name is selected the selected template stay in combobox
		
		String template = request.getParameter("templateName");
		if(template != null){
			session.removeAttribute("errorTemplate");
			session.setAttribute("template", template);
		}else{
			if(template == null){
				session.setAttribute("errorTempalte", "You must select a template");
			}
		}
		System.out.println(template);
		
		//when department name is selected the selected template stay in combobox
		String department = request.getParameter("department");
		if(department != null){
			session.removeAttribute("errorDepartment");
			session.setAttribute("department", department);
		}else{
			if(department == null){
				session.setAttribute("errorDepartment", "You must select a department");
			}
		}
		System.out.println(department);

		response.sendRedirect("enter-report.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
