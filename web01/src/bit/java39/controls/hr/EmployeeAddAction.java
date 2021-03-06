package bit.java39.controls.hr;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.annotations.Component;
import bit.java39.dao.EmployeeDao;
import bit.java39.servlets.Action;
import bit.java39.vo.Employee;

@Component("/hr/addEmp.do")
public class EmployeeAddAction implements Action {
	EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			return "/hr/employeeForm.jsp";
			
		} else {
			Employee employee = new Employee();
			employee.setNo( Integer.parseInt(request.getParameter("empno")) );
			employee.setName( request.getParameter("ename") );
			employee.setJob( request.getParameter("job") );
			employee.setManagerNo( Integer.parseInt(request.getParameter("mgr")) );
			String hiredate = request.getParameter("hiredate");
			employee.setHireDate( Date.valueOf(hiredate) );
			employee.setSalary( Integer.parseInt(request.getParameter("sal")) );
			employee.setCommission( Integer.parseInt(request.getParameter("comm")) );
			employee.setDepartmentNo( Integer.parseInt(request.getParameter("deptno")));
			
			int count = employeeDao.insert(employee);
			if (count > 0) {
				return "/hr/employeeAdd.jsp";
				
			} else {
				throw new Exception("등록 실패입니다.");
			}
		}
	}
}

















