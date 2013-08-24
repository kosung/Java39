package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

public class EmployeeUpdate01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 직원정보 변경폼 출력
	@Override
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>직원정보 변경폼</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>직원정보 변경</h1>");
		
		try {
			Employee emp = ((EmployeeDao)this.getServletContext()
					.getAttribute("employeeDao"))
					.getEmployee(empno);
			
			if (emp != null) {
				out.println("<form action='updateEmp' method='post'>");
				out.println("직원번호 <input type='text' readonly name='empno' value='" +
						empno + "'><br>");
				out.println("이름 <input type='text' name='ename' value='" +
						emp.getName() + "'><br>");
				out.println("직무 <input type='text' name='job' value='" +
						emp.getJob() + "'><br>");
				out.println("관리자 <input type='text' name='mgr' value='" +
						emp.getManagerNo() + "'><br>");
				out.println("입사일 <input type='text' name='hiredate' value='" +
						emp.getHireDate() + "'><br>");
				out.println("급여 <input type='text' name='sal' value='" +
						emp.getSalary() + "'><br>");
				out.println("상여금 <input type='text' name='comm' value='" +
						emp.getCommission() + "'><br>");
				out.println("부서번호 <input type='text' name='deptno' value='" +
						emp.getDepartmentNo() + "'><br>");
				out.println("<input type='submit' value='변경'>");
				out.println("<a href='searchEmp'>[목록]</a>");
				out.println("</form>");
				
			} else {
				out.println("<p>조건에 일치하는 직원을 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		} 
		
		out.println("</body>");
		out.println("</html>");
	}
	
	// 직원정보 DB에 입력하고 결과 출력
	@Override
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
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
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		try {
			int count = ((EmployeeDao)this.getServletContext()
					.getAttribute("employeeDao"))
					.update(employee);
			
			if (count > 0) {
				response.sendRedirect("retrieveEmp?empno=" + employee.getNo());
				return;
			} else {
				out.println("<p>변경할 직원을 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		}
		out.println("</body></html>");
		
	}
}

















