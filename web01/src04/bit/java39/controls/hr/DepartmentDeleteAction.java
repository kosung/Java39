package bit.java39.controls.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.DepartmentDao;
import bit.java39.servlets.Action;

public class DepartmentDeleteAction implements Action {
	DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		int count = departmentDao.delete(deptno);
		
		if (count > 0) {
			return "redirect:searchDept.do";
		} else {
			throw new Exception("삭제할 부서를 찾을 수 없습니다.");
		}
	}
	
	
}
