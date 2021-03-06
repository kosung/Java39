package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
public class EmployeeList01 extends HttpServlet {
	
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
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>직원정보2</h1>");
		
		try {
			out.println("<p><a href='addEmp'>[신규]</a></p>");
			
			out.println("<table>");
			out.println("<tr>");
			out.println("	<th>사원번호</th>");
			out.println("	<th>사원명</th>");
			out.println("	<th>직무</th>");
			out.println("	<th>급여</th>");
			out.println("	<th>부서명</th>");
			out.println("	<th>지역</th>");
			out.println("	<th>상관명</th>");
			out.println("</tr>");
			
			ArrayList<Employee> list = 
					((EmployeeDao) this.getServletContext()
									.getAttribute("employeeDao"))
									.getEmployeeList(deptno);
			
			for( Employee emp : list ) { 
				out.println("<tr>");
				out.print("<td><a href='retrieveEmp?empno=" + 
						emp.getNo() + "'>" + 
						emp.getNo() + "</a></td>");
				out.print("<td>" + emp.getName() + "</td>");
				out.print("<td>" + emp.getJob() + "</td>");
				out.print("<td>" + emp.getSalary() + "</td>");
				out.print("<td>" + emp.getDepartmentName() + "</td>");
				out.print("<td>" + emp.getDepartmentLocation() + "</td>");
				out.println("<td>" + emp.getManagerName() + "</td>");
				out.println("</tr>");
			}

		} catch (Exception e) {
			out.println("<p>" + e.toString() + "</p>");
			
		} 
		
		out.println("</table></body></html>");
	}
	
	
}
