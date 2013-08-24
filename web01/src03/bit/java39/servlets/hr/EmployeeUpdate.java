package bit.java39.servlets.hr;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

@SuppressWarnings("serial")
public class EmployeeUpdate extends HttpServletBase {

	@Override
	protected String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			int empno = Integer.parseInt(request.getParameter("empno"));
			Employee emp = ((EmployeeDao)this.getServletContext()
					.getAttribute("employeeDao"))
					.getEmployee(empno);
			if (emp != null) {
				request.setAttribute("employee", emp);
				return "/hr/employeeUpdateForm.jsp";
			} else {
				throw new Exception("조건에 일치하는 직원을 찾을 수 없습니다.");
			}
			
		} else {
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
			
			int count = ((EmployeeDao)this.getServletContext()
					.getAttribute("employeeDao"))
					.update(employee);
			
			if (count > 0) {
				return "redirect:retrieveEmp?empno=" + employee.getNo();
			} else {
				throw new Exception("변경할 직원을 찾을 수 없습니다.");
			}
		}
	}
}

















