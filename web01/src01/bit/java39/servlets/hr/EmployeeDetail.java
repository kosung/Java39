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

public class EmployeeDetail extends HttpServlet {
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
		out.println("<h1>직원 상세정보</h1>");
		
		DBConnectionPool dbPool = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			dbPool = (DBConnectionPool) this.getServletContext().getAttribute("dbPool");
			
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(
					"select ename,job,mgr,hiredate,sal,comm,deptno" +
					" from emp" +
					" where empno=" + empno);
			
			if (rs.next()) {
				out.println("<table>");
				out.println("<tr><th>사원번호</th>");
				out.println("<td>" + empno + "</td></tr>");
				out.println("<tr><th>이름</th>");
				out.println("<td>" + rs.getString("ename") + "</td></tr>");
				out.println("<tr><th>직무</th>");
				out.println("<td>" + rs.getString("job") + "</td></tr>");
				out.println("<tr><th>관리자</th>");
				out.println("<td>" + rs.getInt("mgr") + "</td></tr>");
				out.println("<tr><th>입사일</th>");
				out.println("<td>" + rs.getDate("hiredate") + "</td></tr>");
				out.println("<tr><th>급여</th>");
				out.println("<td>" + rs.getInt("sal") + "</td></tr>");
				out.println("<tr><th>상여금</th>");
				out.println("<td>" + rs.getInt("comm") + "</td></tr>");
				out.println("<tr><th>부서번호</th>");
				out.println("<td>" + rs.getInt("deptno") + "</td></tr>");
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
			
		} finally {
			try { rs.close(); } catch (Exception e) {}
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
		
		out.println("</body></html>");
	}
	
	
}
