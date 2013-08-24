package bit.java39.controls.hr;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

//@Controller
public class EmployeeUpdateAction {
	EmployeeDao employeeDao;
	
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@RequestMapping(value="/hr/updateEmp", method=RequestMethod.GET)
	public String form(int empno, Model model) throws Exception{
		Employee emp = employeeDao.getEmployee(empno);
		if (emp != null) {
			model.addAttribute("employee", emp);
			return "hr/employeeUpdateForm";
		} else {
			throw new Exception("조건에 일치하는 직원을 찾을 수 없습니다.");
		}
		
		
	}
	
	@RequestMapping(value="/hr/updateEmp", method=RequestMethod.POST)
	public String execute(Employee employee, BindingResult result) throws Exception {
			int count = employeeDao.update(employee);
			
			if (count > 0) {
				return "redirect:retrieveEmp.do?empno=" + employee.getNo();
			} else {
				throw new Exception("변경할 직원을 찾을 수 없습니다.");
			}
		}
	
}

















