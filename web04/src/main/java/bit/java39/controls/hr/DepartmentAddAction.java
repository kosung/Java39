package bit.java39.controls.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bit.java39.dao.DepartmentDao;
import bit.java39.vo.Department;

@Controller("/hr/addDept.do")
public class DepartmentAddAction  {
	DepartmentDao departmentDao;
	
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			return "/hr/departmentForm.jsp";
			
		} else {
			Department department = new Department();
			department.setNo( Integer.parseInt(request.getParameter("deptno")) );
			department.setName( request.getParameter("dname") );
			department.setLocation( request.getParameter("loc") );
		
			int count = departmentDao.insert(department);
			if (count > 0) {
				return "redirect:searchDept.do";
			} else {
				throw new Exception("등록 실패입니다.");
			}
		}
	}
}

















