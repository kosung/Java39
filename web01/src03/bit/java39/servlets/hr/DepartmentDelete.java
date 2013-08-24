package bit.java39.servlets.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.DepartmentDao;

@SuppressWarnings("serial")
public class DepartmentDelete extends HttpServletBase {
	@Override
	protected String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		int count = ((DepartmentDao)this.getServletContext()
				.getAttribute("departmentDao"))
				.delete(deptno);
		
		if (count > 0) {
			return "redirect:searchDept";
		} else {
			throw new Exception("삭제할 부서를 찾을 수 없습니다.");
		}
	}
	
	
}
