package bit.java39.servlets.hr;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

public class EmployeeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 직원정보 변경폼 출력
	@Override
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		try {
			Employee emp = ((EmployeeDao)this.getServletContext()
					.getAttribute("employeeDao"))
					.getEmployee(empno);
			
			if (emp != null) {
				request.setAttribute("employee", emp);
				RequestDispatcher rd = 
						request.getRequestDispatcher("/hr/employeeUpdateForm.jsp");
				rd.forward(request, response);
			} else {
				throw new Exception("<p>조건에 일치하는 직원을 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			
		} 
	}
	
	// 직원정보 DB에 입력하고 결과 출력
	@Override
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = new Employee();
		employee.setNo( Integer.parseInt(request.getParameter("empno")) );
		employee.setName( request.getParameter("ename") );
		employee.setJob( request.getParameter("job") );
		employee.setManagerNo( Integer.parseInt(request.getParameter("mgr")) );
		String hiredate = request.getParameter("hiredate");
		employee.setHireDate( Date.valueOf(hiredate) );
		employee.setSalary( Integer.parseInt(request.getParameter("sal")) );
		employee.setCommission( Integer.parseInt(request.getParameter("comm")) );
		employee.setDepartmentNo( Integer.parseInt(request.getParameter("deptno")));
		
		try {
			int count = ((EmployeeDao)this.getServletContext()
					.getAttribute("employeeDao"))
					.update(employee);
			
			if (count > 0) {
				response.sendRedirect("retrieveEmp?empno=" + employee.getNo());
				return;
			} else {
				throw new Exception("변경할 직원을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			
		}
		
	}
}

















