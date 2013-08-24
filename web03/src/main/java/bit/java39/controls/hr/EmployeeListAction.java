package bit.java39.controls.hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bit.java39.dao.EmployeeDao;

//@Controller("/hr/searchEmp.do")  /* 기본으로 employeeListAction이 인스턴스 이름이다.*/
//@Controller
public class EmployeeListAction {
	EmployeeDao employeeDao;
	
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	// 4. 바구니와 View URL을 함께 리턴하기
	// => ModelAndView 객체를 리턴한다면,
	//	  DispatcherServlet은 이 객체로부터 저장된 값을 꺼내 ServletRequest에 옮기고,
	//	  View URL 정보를 꺼내 해당 JSP로 포워딩 시킨다.
	@RequestMapping("/hr/searchEmp")
	public ModelAndView execute(
			@RequestParam(defaultValue="0") int deptno) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("employeeList", employeeDao.getEmployeeList(deptno));
		mv.setViewName("hr/employeeList");
		
		return mv;
	}
	
	// 3. 클라이언트가 보낸 파라미터를 꺼내고 싶다면, 메서드의 파라미터로 선언하라.
	// - JSP에 데이터를 보내야 하는데, 바구니 줘!
	// - @RequestParam : 
	//		=> 요청 파라미터 이름과 메서드 파라미터 이름이 다를 경우
	//		=> 요청 파라미터 이름과 메서드 파라미터 이름이 같을 경우 생략 가능.
	//		=> 요청 파라미터의 값이 누락될 경우, 메서드 파라미터의 기본 값을 설정할 수 있다.
	// - JSP에 데이터를 전달하고 싶다면, DispatcherServlet에 바구니 역할을 할 객체를 요청한다.
	//		=> execute(..., Model baguni) ....
	// 		=> DispatcherServlet은 객체를 담을 수 있는 Model 구현 객체를 만들어 넘겨준다.
	//		=> Model 객체에 담겨진 값은 DispatcherServlet이 ServletRequest로 옮긴다.
	//		=> 따라서, JSP는 이전처럼 아무런 변경없이 꺼내 쓸 수 있다.
	/*
	@RequestMapping("/hr/searchEmp")
	public String execute(
			@RequestParam(defaultValue="0") int deptno, 
			//@RequestParam(value="deptno", defaultValue="0") int no, 
			Model baguni) throws Exception {
		baguni.addAttribute("employeeList", employeeDao.getEmployeeList(deptno));
		return "/hr/employeeList.jsp";
	}
	*/
	
	// 2. 파라미터로 원하는 값만 받을 수 있다.
	// - 그냥 필요한 객체가 있다면 선언하면 된다.
	// - DispatcherServlet이 호출할 때 넘겨준다.
	/*
	@RequestMapping("/hr/searchEmp")
	public String execute(HttpServletRequest request) throws Exception {
		int deptno = 0;
		if( request.getParameter("deptno") != null) {
			deptno = Integer.parseInt( request.getParameter("deptno") );
		}
		request.setAttribute("employeeList", employeeDao.getEmployeeList(deptno));
		
		return "/hr/employeeList.jsp";
	}
	*/
	
	/* 1. 일반적인 형태
	@RequestMapping("/hr/searchEmp")
	public String execute(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int deptno = 0;
		if( request.getParameter("deptno") != null) {
			deptno = Integer.parseInt( request.getParameter("deptno") );
		}
		request.setAttribute("employeeList", employeeDao.getEmployeeList(deptno));
		
		return "/hr/employeeList.jsp";
	}*/
}









