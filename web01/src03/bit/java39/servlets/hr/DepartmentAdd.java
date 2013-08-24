package bit.java39.servlets.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.DepartmentDao;
import bit.java39.vo.Department;

@SuppressWarnings("serial")
public class DepartmentAdd extends HttpServletBase {
	@Override
	protected String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			return "/hr/departmentForm.jsp";
			
		} else {
			Department department = new Department();
			department.setNo( Integer.parseInt(request.getParameter("deptno")) );
			department.setName( request.getParameter("dname") );
			department.setLocation( request.getParameter("loc") );
		
			int count = ((DepartmentDao)this.getServletContext()
									.getAttribute("departmentDao"))
									.insert(department);
			if (count > 0) {
				return "redirect:searchDept";
			} else {
				throw new Exception("등록 실패입니다.");
			}
		}
	}
}

















