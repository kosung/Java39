package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.utils.DBConnectionPool;

public class EmployeeDelete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empno = request.getParameter("empno");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>직원 삭제</h1>");
		
		DBConnectionPool dbPool = null;
		Connection con = null;
		Statement stmt = null;
		
		try {
			dbPool = (DBConnectionPool) this.getServletContext().getAttribute("dbPool");
			
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			int count = stmt.executeUpdate(
					"delete emp where empno=" + empno);
			
			if (count > 0) {
				response.sendRedirect("searchEmp");
				return;
			} else {
				out.println("<p>삭제할 직원을 찾을 수 없습니다.</p>");
			}

		} catch (Exception e) {
			out.println("<pre>");
			e.printStackTrace(out);
			out.println("</pre>");
			
		} finally {
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
		
		out.println("</body></html>");
	}
	
	
}
