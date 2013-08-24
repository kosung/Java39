package bit.java39.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.controls.hr.EmployeeAddAction;
import bit.java39.controls.hr.EmployeeDeleteAction;
import bit.java39.controls.hr.EmployeeDetailAction;
import bit.java39.controls.hr.EmployeeListAction;
import bit.java39.controls.hr.EmployeeUpdateAction;
import bit.java39.dao.EmployeeDao;

@SuppressWarnings("serial")
public class DispatcherServlet01 extends HttpServlet {
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/*
			System.out.println(request.getRequestURI());
			System.out.println(request.getContextPath());
			System.out.println(request.getServletPath());
			*/
			
			String servletPath = request.getServletPath();
			
			String viewUrl = null;
			
			EmployeeDao employeeDao = 
				(EmployeeDao)this.getServletContext().getAttribute("employeeDao");
			
			if("/hr/searchEmp.do".equals(servletPath)) {
				EmployeeListAction action = new EmployeeListAction();
				action.setEmployeeDao(employeeDao);
				viewUrl = action.execute(request, response);
			} else if("/hr/retrieveEmp.do".equals(servletPath)){
				EmployeeDetailAction action = new EmployeeDetailAction();
				action.setEmployeeDao(employeeDao);
				viewUrl = action.execute(request, response);
			} else if("/hr/addEmp.do".equals(servletPath)){
				EmployeeAddAction action = new EmployeeAddAction();
				action.setEmployeeDao(employeeDao);
				viewUrl = action.execute(request, response);
			} else if("/hr/deleteEmp.do".equals(servletPath)){
				EmployeeDeleteAction action = new EmployeeDeleteAction();
				action.setEmployeeDao(employeeDao);
				viewUrl = action.execute(request, response);
			} else if("/hr/updateEmp.do".equals(servletPath)){
				EmployeeUpdateAction action = new EmployeeUpdateAction();
				action.setEmployeeDao(employeeDao);
				viewUrl = action.execute(request, response);
			} else {
				throw new Exception("해당 URL을 위한 서비스를 찾을 수 없습니다.");
			}
			
			if (viewUrl.indexOf("redirect:") == -1) {
				RequestDispatcher rd = 
						request.getRequestDispatcher(viewUrl);
				rd.forward(request, response);
			} else {
				response.sendRedirect(viewUrl.substring(9));
			}
			
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}

}
