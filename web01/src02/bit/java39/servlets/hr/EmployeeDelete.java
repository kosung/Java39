package bit.java39.servlets.hr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;

@SuppressWarnings("serial")
public class EmployeeDelete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		try {
			int count = ((EmployeeDao)this.getServletContext()
					.getAttribute("employeeDao"))
					.delete(empno);
			
			if (count > 0) {
				response.sendRedirect("searchEmp");
				return;
			} else {
				throw new Exception("삭제할 직원을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		} 
		
	}
	
	
}
