package bit.java39.controls.hr;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

//@Controller
public class EmployeeAddAction {
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
	
	// 2. GET 또는 POST에 따라 호출될 메서드를 구분
	@RequestMapping(value="/hr/addEmp",method=RequestMethod.GET)
	public String form() throws Exception {
		return "hr/employeeForm";
	}
	
	// 3. Binding 결과를 알 수 있는 방법
	// - 요청 파라미터를 받는 변수 다음에 즉시 BindingResult 파라미터를 선언해야 한다.
	// - 요청 파라미터의 값을 VO 객체에 넣는 과정(데이터 바인딩)에 문제 발생 시 디버깅 가능!
	@RequestMapping(value="/hr/addEmp",method=RequestMethod.POST)
	public String add(Employee employee, BindingResult result) throws Exception {
		int count = employeeDao.insert(employee);
		if (count > 0) {
			return "hr/employeeAdd";
		} else {
			throw new Exception("등록 실패입니다.");
		}
	}
	
	// 2. 입력 값을 VO 객체로 바로 받는 방법
	// - 만약, 요청 파라미터 값과 VO 프로퍼티의 타입과 일치하지 않으면 오류 발생!
	// - 오류 내용을 확인할 수 없다.
	/*
	@RequestMapping(value="/hr/addEmp",method=RequestMethod.POST)
	public String add(Employee employee) throws Exception {
		int count = employeeDao.insert(employee);
		if (count > 0) {
			return "/hr/employeeAdd.jsp";
		} else {
			throw new Exception("등록 실패입니다.");
		}
	}
	*/
	
	// 1. 일반적인 형태
	/*
	@RequestMapping("/hr/addEmp")
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
	*/
}

















