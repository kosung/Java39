package bit.java39.controls.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bit.java39.dao.DepartmentDao;
import bit.java39.servlets.Action;
import bit.java39.vo.Department;

@Controller("/hr/retieveDept.do")
public class DepartmentDetailAction implements Action {
	DepartmentDao departmentDao;
	
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@Override
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		Department dept = departmentDao.getDepartment(deptno);
		if (dept != null) {
			request.setAttribute("department", dept);
			return "/hr/departmentDetail.jsp";
			
		} else {
			throw new Exception("조건에 일치하는 부서를 찾을 수 없습니다.");
		}
	}
	
	
}
