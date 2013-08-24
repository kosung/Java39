package bit.java39.controls.hr;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.annotations.Component;
import bit.java39.dao.DepartmentDao;
import bit.java39.servlets.Action;
import bit.java39.vo.Department;

@Component("/hr/searchDept.do")
public class DepartmentListAction implements Action {
	DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList<Department> list = departmentDao.getDepartmentList();
		
		request.setAttribute("departmentList", list);
		return "/hr/departmentList.jsp";
	}
	
	
}
