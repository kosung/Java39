package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.DepartmentDao;
import bit.java39.vo.Department;

@SuppressWarnings("serial")
public class DepartmentList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>부서정보</h1>");
		
		try {
			out.println("<p><a href='addDept'>[신규]</a></p>");
			
			out.println("<table>");
			out.println("<tr>");
			out.println("	<th>부서번호</th>");
			out.println("	<th>사원명</th>");
			out.println("</tr>");
			
			ArrayList<Department> list = 
					((DepartmentDao) this.getServletContext()
									.getAttribute("departmentDao"))
									.getDepartmentList();
			
			for( Department dept : list ) { 
				out.println("<tr>");
				out.print("<td><a href='retrieveDept?deptno=" + 
						dept.getNo() + "'>" + 
						dept.getNo() + "</a></td>");
				out.print("<td>" + dept.getName() + "</td>");
				out.println("</tr>");
			}

		} catch (Exception e) {
			out.println("<p>" + e.toString() + "</p>");
			
		} 
		
		out.println("</body></html>");
	}
	
	
}
