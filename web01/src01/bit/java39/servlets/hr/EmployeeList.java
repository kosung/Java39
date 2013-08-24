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

/* 문제점
 * 1. 호출할 때 마다 DB에 연결한다. 속도가 느리다.
 * 2. 연결 후 SQL 전달 중에 오류가 발생했을 때? Connection을 닫지 못한다.
 * 3. 결과를 가져오는 중에 오류가 발생했을 때? Connection, Statement 못 닫는다.
 */
public class EmployeeList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deptno = request.getParameter("deptno");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>직원정보</h1>");
		
		DBConnectionPool dbPool = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			dbPool = (DBConnectionPool) this.getServletContext().getAttribute("dbPool");
			
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			String sql = "select e.empno, e.ename, e.job, e.sal, " +
					" d.dname, d.loc, m.ename as manager" + 
					" from emp e left join dept d on e.deptno=d.deptno" +
					" left join emp m on e.mgr=m.empno";
			
			if (deptno != null) {
				sql += " where e.deptno=" + deptno;
			}
			
			rs = stmt.executeQuery(sql);
			
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
			
			while(rs.next()) { 
				out.println("<tr>");
				out.print("<td><a href='retrieveEmp?empno=" + 
						rs.getInt("empno") + "'>" + 
						rs.getInt("empno") + "</a></td>");
				out.print("<td>" + rs.getString("ename") + "</td>");
				out.print("<td>" + rs.getString("job") + "</td>");
				out.print("<td>" + rs.getString("sal") + "</td>");
				out.print("<td>" + rs.getString("dname") + "</td>");
				out.print("<td>" + rs.getString("loc") + "</td>");
				out.println("<td>" + rs.getString("manager") + "</td>");
				out.println("</tr>");
			}

		} catch (Exception e) {
			out.println("<p>" + e.toString() + "</p>");
			
		} finally {
			try { rs.close(); } catch (Exception e) {}
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
		
		out.println("</body></html>");
	}
	
	
}
