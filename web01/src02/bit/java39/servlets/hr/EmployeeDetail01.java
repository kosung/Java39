package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

@SuppressWarnings("serial")
public class EmployeeDetail01 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>직원 상세정보</h1>");
		
		try {
			Employee emp = ((EmployeeDao)this.getServletContext()
									.getAttribute("employeeDao"))
									.getEmployee(empno);
			if (emp != null) {
				out.println("<table>");
				out.println("<tr><th>사원번호</th>");
				out.println("<td>" + empno + "</td></tr>");
				out.println("<tr><th>이름</th>");
				out.println("<td>" + emp.getName() + "</td></tr>");
				out.println("<tr><th>직무</th>");
				out.println("<td>" + emp.getJob() + "</td></tr>");
				out.println("<tr><th>관리자</th>");
				out.println("<td>" + emp.getManagerNo() + "</td></tr>");
				out.println("<tr><th>입사일</th>");
				out.println("<td>" + emp.getHireDate() + "</td></tr>");
				out.println("<tr><th>급여</th>");
				out.println("<td>" + emp.getSalary() + "</td></tr>");
				out.println("<tr><th>상여금</th>");
				out.println("<td>" + emp.getCommission() + "</td></tr>");
				out.println("<tr><th>부서번호</th>");
				out.println("<td>" + emp.getDepartmentNo() + "</td></tr>");
				out.println("</table>");
				
				out.println("<a href='updateEmp?empno=" + empno + "'>[변경]</a>");
				out.println("<a href='deleteEmp?empno=" + empno + "'>[삭제]</a>");
				out.println("<a href='searchEmp'>[목록]</a>");
				
			} else {
				out.println("<p>조건에 일치하는 직원을 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		}
		
		out.println("</body></html>");
	}
	
	
}
