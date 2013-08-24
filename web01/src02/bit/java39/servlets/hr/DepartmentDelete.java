package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.dao.DepartmentDao;

@SuppressWarnings("serial")
public class DepartmentDelete extends HttpServlet {
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
		out.println("<h1>부서 삭제</h1>");
		
		try {
			int count = ((DepartmentDao)this.getServletContext()
					.getAttribute("departmentDao"))
					.delete(deptno);
			
			if (count > 0) {
				response.sendRedirect("searchDept");
				return;
			} else {
				out.println("<p>삭제할 부서를 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		} 
		
		out.println("</body></html>");
	}
	
	
}
