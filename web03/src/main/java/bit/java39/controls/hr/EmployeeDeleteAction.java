package bit.java39.controls.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import bit.java39.dao.EmployeeDao;

//@Controller
public class EmployeeDeleteAction {
	EmployeeDao employeeDao;
	
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@RequestMapping("/hr/deleteEmp")
	public String execute(int empno) throws Exception {
		
		int count = employeeDao.delete(empno);
		
		if (count > 0) {
			return "redirect:searchEmp.do";
		} else {
			throw new Exception("삭제할 직원을 찾을 수 없습니다.");
		}
	}
	
}
