package bit.java39.controls.hr;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.annotations.Component;
import bit.java39.dao.EmployeeDao;
import bit.java39.servlets.Action;
import bit.java39.vo.Employee;

@Component("/hr/retrieveEmp.do")
public class EmployeeDetailAction implements Action {
	EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		Employee emp = employeeDao.getEmployee(empno);
		
		request.setAttribute("employee", emp);
		
		if (emp != null) {
			return "/hr/employeeDetail.jsp";
			
		} else {
			throw new Exception ("해당 직원을 찾을 수 없습니다.");
		}
	}
}
