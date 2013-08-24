package bit.java39.controls.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.annotations.Component;
import bit.java39.dao.DepartmentDao;
import bit.java39.servlets.Action;
import bit.java39.vo.Department;

@Component("/hr/addDept.do")
public class DepartmentAddAction implements Action {
	DepartmentDao departmentDao;
	
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@Override
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

















