package bit.java39.controls.hr;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;
import bit.java39.servlets.Action;
import bit.java39.vo.Employee;

public class EmployeeListAction implements Action {
	EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int deptno = 0;
		if( request.getParameter("deptno") != null) {
			deptno = Integer.parseInt( request.getParameter("deptno") );
		}
		
		ArrayList<Employee> list = employeeDao.getEmployeeList(deptno);

		request.setAttribute("employeeList", list);
		
		return "/hr/employeeList.jsp";
	}
}









