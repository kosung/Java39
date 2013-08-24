package bit.java39.servlets.hr;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

@SuppressWarnings("serial")
public class EmployeeDetail extends HttpServletBase {
	@Override
	protected String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		Employee emp = ((EmployeeDao)this.getServletContext()
				.getAttribute("employeeDao"))
				.getEmployee(empno);
		
		request.setAttribute("employee", emp);
		
		if (emp != null) {
			return "/hr/employeeDetail.jsp";
			
		} else {
			throw new Exception ("해당 직원을 찾을 수 없습니다.");
		}
	}
}
