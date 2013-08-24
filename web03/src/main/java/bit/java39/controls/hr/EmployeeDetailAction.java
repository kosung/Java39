package bit.java39.controls.hr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

//@Controller
public class EmployeeDetailAction {
	EmployeeDao employeeDao;
	
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@RequestMapping("/hr/retrieveEmp")
	public String execute(
			@RequestParam("empno") int empno, ModelMap modelMap) throws Exception {
		Employee emp = employeeDao.getEmployee(empno);
		
		modelMap.addAttribute("employee", emp);
		
		if (emp != null) {
			return "hr/employeeDetail";
			
		} else {
			throw new Exception ("해당 직원을 찾을 수 없습니다.");
		}
	}
}
