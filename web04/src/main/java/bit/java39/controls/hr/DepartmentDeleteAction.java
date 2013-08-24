package bit.java39.controls.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bit.java39.dao.DepartmentDao;

@Controller("/hr/deleteDept.do")
public class DepartmentDeleteAction  {
	DepartmentDao departmentDao;
	
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
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
