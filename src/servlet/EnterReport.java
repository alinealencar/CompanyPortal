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
		//when template name is selected the selected template stay in combobox
		HttpSession session = request.getSession();
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

		response.sendRedirect("enter-report.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
