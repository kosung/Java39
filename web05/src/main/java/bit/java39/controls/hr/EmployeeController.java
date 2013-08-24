package bit.java39.controls.hr;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import bit.java39.service.EmployeeService;
import bit.java39.vo.Employee;

@Controller
@RequestMapping("/hr")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/*
	@RequestMapping("/searchEmp")
	public ResponseEntity<String> list(
			@RequestParam(defaultValue="0") int deptno) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Test", "okok");
		
		return new ResponseEntity<String>(
				new Gson().toJson( employeeDao.getEmployeeList(deptno) ),
				headers,
				HttpStatus.CREATED);
	}
	*/
	
	/*
	@RequestMapping("/searchEmp")
	@ResponseBody
	public String list(
			@RequestParam(defaultValue="0") int deptno) throws Exception {
		return new Gson().toJson( employeeDao.getEmployeeList(deptno) );
	}
	*/
	
	/*
	@RequestMapping(value="/searchEmp")
	public ModelAndView listForJSP(
			@RequestParam(defaultValue="0") int deptno) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("employeeList", employeeDao.getEmployeeList(deptno));
		mv.setViewName("hr/employeeList");
		return mv;
	}
	*/
	
	/* Object => JSON 출력
	 * - Object를 리턴할 때는 JSON으로 변환하는 ViewResolver에게 전달하도록 설정해야 한다.
	 * - @ResponseBody String을 리턴할 때는 직접 출력하는 ViewResolver에게 전달하도록 설정.
	 * - 일반 String일 경우는 JSP로 포워딩 하는 ViewResolver에게 전달하도록 설정한다.
	 * - 이것을 중간에서 중재해주는 ViewResolver가 있다. 
	 */
	@RequestMapping(value="/searchEmp")
	@ResponseBody
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="5") int pageSize) throws Exception {
		HashMap<String,Object> responseResult = new HashMap<String,Object>();
		try {
			responseResult.put("employeeList", employeeService.getEmployeeList(pageNo, pageSize));
			responseResult.put("status", "success");
			responseResult.put("pageNo", pageNo);
			responseResult.put("pageSize", pageSize);
			
			int empCount = employeeService.countAll();
			int totalPage =  empCount / pageSize;
			if (empCount % pageSize > 0) {
				totalPage++; 
			}
			responseResult.put("totalPage", totalPage);
			
		} catch (Exception e) {
			responseResult.put("status", "fail");
			
			StringWriter temp = new StringWriter();
			PrintWriter out = new PrintWriter(temp);
			e.printStackTrace(out);
			
			responseResult.put("message", temp.toString());
		}
		return responseResult;
		
		
		
	}
	
	@RequestMapping(value="/addEmp",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> add(Employee employee, BindingResult result) throws Exception {
		HashMap<String,Object> responseResult = new HashMap<String,Object>();
		try {
			int count = employeeService.addEmployee(employee);
			
			if (count > 0) {
				responseResult.put("status", "success");
			} else {
				responseResult.put("status", "fail");
				responseResult.put("message", "Insert count is 0.");
			}
		} catch (Exception e) {
			responseResult.put("status", "fail");
			
			StringWriter temp = new StringWriter();
			PrintWriter out = new PrintWriter(temp);
			e.printStackTrace(out);
			
			responseResult.put("message", temp.toString());
		}
		return responseResult;
	}
	
	@RequestMapping("/retrieveEmp")
	@ResponseBody
	public Object retrieve(
			@RequestParam("empno") int empno, 
			ModelMap modelMap) throws Exception {
		HashMap<String,Object> responseResult = new HashMap<String,Object>();
		try {
			Employee emp = employeeService.getEmployee(empno);
			if (emp != null) {
				responseResult.put("status", "success");
				responseResult.put("data", emp);
			} else {
				responseResult.put("status", "fail");
				responseResult.put("message", "해당 직원이 없습니다!");
			}
		} catch (Exception e) {
			responseResult.put("status", "fail");
			
			StringWriter temp = new StringWriter();
			PrintWriter out = new PrintWriter(temp);
			e.printStackTrace(out);
			
			responseResult.put("message", temp.toString());
		}
		
		return responseResult;
	}

	@RequestMapping(value="/updateEmp", method=RequestMethod.POST)
	@ResponseBody
	public Object update(Employee employee, BindingResult result) throws Exception {
		HashMap<String,Object> responseResult = new HashMap<String,Object>();
		try {
			int count = employeeService.changeEmployee(employee);
		
			if (count > 0) {
				responseResult.put("status", "success");
			} else {
				responseResult.put("status", "fail");
				responseResult.put("message", "해당 직원이 없습니다!");
			}
		} catch (Exception e) {
			responseResult.put("status", "fail");
			
			StringWriter temp = new StringWriter();
			PrintWriter out = new PrintWriter(temp);
			e.printStackTrace(out);
			
			responseResult.put("message", temp.toString());
		}
		
		return responseResult;
	}
	
	@RequestMapping("/deleteEmp")
	@ResponseBody
	public Object delete(
			@RequestParam(value="empno",defaultValue="0") int empno) throws Exception {
		HashMap<String,Object> responseResult = new HashMap<String,Object>();
		try {
			int count = employeeService.removeEmployee(empno);
		
			if (count > 0) {
				responseResult.put("status", "success");
			} else {
				responseResult.put("status", "fail");
				responseResult.put("message", "해당 직원이 없습니다!");
			}
		} catch (Exception e) {
			responseResult.put("status", "fail");
			
			StringWriter temp = new StringWriter();
			PrintWriter out = new PrintWriter(temp);
			e.printStackTrace(out);
			
			responseResult.put("message", temp.toString());
		}
		
		return responseResult;
	}
	
}









