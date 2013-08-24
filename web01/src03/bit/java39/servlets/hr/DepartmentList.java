package bit.java39.servlets.hr;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.DepartmentDao;
import bit.java39.vo.Department;

@SuppressWarnings("serial")
public class DepartmentList extends HttpServletBase {

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList<Department> list = 
				((DepartmentDao) this.getServletContext()
								.getAttribute("departmentDao"))
								.getDepartmentList();
		
		request.setAttribute("departmentList", list);
		return "/hr/departmentList.jsp";
	}
	
	
}
