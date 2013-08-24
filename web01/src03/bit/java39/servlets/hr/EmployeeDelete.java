package bit.java39.servlets.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;

@SuppressWarnings("serial")
public class EmployeeDelete extends HttpServletBase {
	@Override
	protected String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		int count = ((EmployeeDao)this.getServletContext()
				.getAttribute("employeeDao"))
				.delete(empno);
		
		if (count > 0) {
			return "redirect:searchEmp";
		} else {
			throw new Exception("삭제할 직원을 찾을 수 없습니다.");
		}
	}
	
}
