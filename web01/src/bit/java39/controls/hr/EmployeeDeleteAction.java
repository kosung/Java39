package bit.java39.controls.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.annotations.Component;
import bit.java39.dao.EmployeeDao;
import bit.java39.servlets.Action;

@Component("/hr/deleteEmp.do")
public class EmployeeDeleteAction implements Action {
	EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		int count = employeeDao.delete(empno);
		
		if (count > 0) {
			return "redirect:searchEmp.do";
		} else {
			throw new Exception("삭제할 직원을 찾을 수 없습니다.");
		}
	}
	
}
