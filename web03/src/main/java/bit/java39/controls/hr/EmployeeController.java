package bit.java39.controls.hr;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

@Controller
@RequestMapping("/hr")
public class EmployeeController {
	@Autowired
	EmployeeDao employeeDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping("/searchEmp")
	public ModelAndView list(
			@RequestParam(defaultValue="0") int deptno) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("employeeList", employeeDao.getEmployeeList(deptno));
		mv.setViewName("hr/employeeList");
		
		System.out.println("[Controller] searchEmp...");
		return mv;
	}

	@RequestMapping(value="/addEmp",method=RequestMethod.GET)
	public String form() throws Exception {
		return "hr/employeeForm";
	}
	
	@RequestMapping(value="/addEmp",method=RequestMethod.POST)
	public String add(Employee employee, BindingResult result) throws Exception {
		int count = employeeDao.insert(employee);
		if (count > 0) {
			return "hr/employeeAdd";
		} else {
			throw new Exception("등록 실패입니다.");
		}
	}
	
	@RequestMapping("/retrieveEmp")
	public String retrieve(
			@RequestParam("empno") int empno, 
			ModelMap modelMap) throws Exception {
		Employee emp = employeeDao.getEmployee(empno);
		
		modelMap.addAttribute("employee", emp);
		
		if (emp != null) {
			return "hr/employeeDetail";
			
		} else {
			throw new Exception ("해당 직원을 찾을 수 없습니다.");
		}
	}

	@RequestMapping(value="/updateEmp", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam(value="empno",defaultValue="0") int empno, 
			Model model) throws Exception{
		Employee emp = employeeDao.getEmployee(empno);
		if (emp != null) {
			model.addAttribute("employee", emp);
			return "hr/employeeUpdateForm";
		} else {
			throw new Exception("조건에 일치하는 직원을 찾을 수 없습니다.");
		}
	}
	
	@RequestMapping(value="/updateEmp", method=RequestMethod.POST)
	public String update(Employee employee, BindingResult result) throws Exception {
			int count = employeeDao.update(employee);
		if (count > 0) {
			return "redirect:retrieveEmp.do?empno=" + employee.getNo();
		} else {
			throw new Exception("변경할 직원을 찾을 수 없습니다.");
		}
	}
	
	@RequestMapping("/deleteEmp")
	public String delete(
			@RequestParam(value="empno",defaultValue="0") int empno) throws Exception {
		int count = employeeDao.delete(empno);
		
		if (count > 0) {
			return "redirect:searchEmp.do";
		} else {
			throw new Exception("삭제할 직원을 찾을 수 없습니다.");
		}
	}
	
}









