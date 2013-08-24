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

@SuppressWarnings("serial")
public class EmployeeAdd extends HttpServlet {
	// 직원정보 입력폼 출력
	@Override
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/hr/employeeForm.jsp");
		rd.forward(request, response);
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
									.insert(employee);
			if (count > 0) {
				RequestDispatcher rd = 
						request.getRequestDispatcher("/hr/employeeAdd.jsp");
				rd.forward(request, response);
			} else {
				throw new Exception("등록 실패입니다.");
			}
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		} 
	}
}

















