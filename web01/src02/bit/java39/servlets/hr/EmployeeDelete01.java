package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;

@SuppressWarnings("serial")
public class EmployeeDelete01 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>직원 삭제</h1>");
		
		try {
			int count = ((EmployeeDao)this.getServletContext()
					.getAttribute("employeeDao"))
					.delete(empno);
			
			if (count > 0) {
				response.sendRedirect("searchEmp");
				return;
			} else {
				out.println("<p>삭제할 직원을 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		} 
		
		out.println("</body></html>");
	}
	
	
}
