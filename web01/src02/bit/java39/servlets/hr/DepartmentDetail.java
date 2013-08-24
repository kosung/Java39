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
public class DepartmentDetail extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>부서 상세정보</h1>");
		
		try {
			Department dept = ((DepartmentDao)this.getServletContext()
					.getAttribute("departmentDao"))
					.getDepartment(deptno);
			if (dept != null) {
				out.println("<table>");
				out.println("<tr><th>부서번호</th>");
				out.println("<td>" + deptno + "</td></tr>");
				out.println("<tr><th>이름</th>");
				out.println("<td>" + dept.getName() + "</td></tr>");
				out.println("<tr><th>직무</th>");
				out.println("<td>" + dept.getLocation() + "</td></tr>");
				out.println("</table>");
				
				out.println("<a href='updateDept?deptno=" + deptno + "'>[변경]</a>");
				out.println("<a href='deleteDept?deptno=" + deptno + "'>[삭제]</a>");
				out.println("<a href='searchDept'>[목록]</a>");
				
			} else {
				out.println("<p>조건에 일치하는 부서를 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		}
		
		out.println("</body></html>");
	}
	
	
}
