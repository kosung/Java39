package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.DepartmentDao;
import bit.java39.vo.Department;

@SuppressWarnings("serial")
public class DepartmentUpdate extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>부서정보 변경폼</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>부서정보 변경</h1>");
		
		try {
			Department dept = ((DepartmentDao)this.getServletContext()
					.getAttribute("departmentDao"))
					.getDepartment(deptno);
			
			if (dept != null) {
				out.println("<form action='updateDept' method='post'>");
				out.println("부서번호 <input type='text' readonly name='deptno' value='" +
						deptno + "'><br>");
				out.println("이름 <input type='text' name='dname' value='" +
						dept.getName() + "'><br>");
				out.println("지역 <input type='text' name='loc' value='" +
						dept.getLocation() + "'><br>");
				out.println("<input type='submit' value='변경'>");
				out.println("<a href='searchDept'>[목록]</a>");
				out.println("</form>");
				
			} else {
				out.println("<p>조건에 일치하는 부서를 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		} 
		
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		Department department = new Department();
		department.setNo( Integer.parseInt(request.getParameter("deptno")) );
		department.setName( request.getParameter("dname") );
		department.setLocation( request.getParameter("loc") );
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		try {
			int count = ((DepartmentDao)this.getServletContext()
					.getAttribute("departmentDao"))
					.update(department);
			
			if (count > 0) {
				response.sendRedirect("retrieveDept?deptno=" + department.getNo());
				return;
			} else {
				out.println("<p>변경할 부서를 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		}
		out.println("</body></html>");
		
	}
}

















