package bit.java39.controls.hr;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bit.java39.dao.DepartmentDao;
import bit.java39.vo.Department;

@Controller("/hr/searchDept.do")
public class DepartmentListAction {
	DepartmentDao departmentDao;
	
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList<Department> list = departmentDao.getDepartmentList();
		
		request.setAttribute("departmentList", list);
		return "/hr/departmentList.jsp";
	}
	
	
}
