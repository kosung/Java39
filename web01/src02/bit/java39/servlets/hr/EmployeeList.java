package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

/* 문제점
 * 1. 호출할 때 마다 DB에 연결한다. 속도가 느리다.
 * 2. 연결 후 SQL 전달 중에 오류가 발생했을 때? Connection을 닫지 못한다.
 * 3. 결과를 가져오는 중에 오류가 발생했을 때? Connection, Statement 못 닫는다.
 */
@SuppressWarnings("serial")
public class EmployeeList extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int deptno = 0;
		if( request.getParameter("deptno") != null) {
			deptno = Integer.parseInt( request.getParameter("deptno") );
		}
		
		try {
			ArrayList<Employee> list = 
					((EmployeeDao) this.getServletContext()
									.getAttribute("employeeDao"))
									.getEmployeeList(deptno);

			request.setAttribute("employeeList", list);
			
			RequestDispatcher rd = 
					request.getRequestDispatcher("/hr/employeeList.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		} 
	}
	
	
}









