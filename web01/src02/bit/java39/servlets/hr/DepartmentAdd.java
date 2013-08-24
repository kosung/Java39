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
public class DepartmentAdd extends HttpServlet {
	// 직원정보 입력폼 출력
	@Override
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>부서정보 입력폼</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='addDept' method='post'>");
		out.println("부서번호 <input type='text' name='deptno'><br>");
		out.println("이름 <input type='text' name='dname'><br>");
		out.println("지역 <input type='text' name='loc'><br>");
		out.println("<input type='submit' value='등록'>");
		out.println("<input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	// 직원정보 DB에 입력하고 결과 출력
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
									.insert(department);
			if (count > 0) {
				response.sendRedirect("searchDept");
				return;
			} else {
				out.println("<p>등록 실패입니다.</p>");
			}
		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
		} 
		out.println("</body></html>");
	}
}

















