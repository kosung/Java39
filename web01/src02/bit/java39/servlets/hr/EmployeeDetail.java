package bit.java39.servlets.hr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

@SuppressWarnings("serial")
public class EmployeeDetail extends HttpServlet {
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
			Employee emp = ((EmployeeDao)this.getServletContext()
									.getAttribute("employeeDao"))
									.getEmployee(empno);
			if (emp != null) {
				//HttpSession session = request.getSession();
				//session.setAttribute("employee", emp);
				
				request.setAttribute("employee", emp);
				RequestDispatcher rd = 
						request.getRequestDispatcher("/hr/employeeDetail.jsp");
				rd.forward(request, response);
			} else {
				throw new Exception ("해당 직원을 찾을 수 없습니다.");
			}

		}  catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			
		}
		
	}
	
	
}
