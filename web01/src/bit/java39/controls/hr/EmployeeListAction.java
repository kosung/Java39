package bit.java39.controls.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.annotations.Component;
import bit.java39.dao.EmployeeDao;
import bit.java39.servlets.Action;

@Component("/hr/searchEmp.do")
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
		
		request.setAttribute("employeeList", employeeDao.getEmployeeList(deptno));
		
		return "/hr/employeeList.jsp";
	}
}









