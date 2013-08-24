package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.utils.DBConnectionPool;

public class EmployeeUpdate extends HttpServlet {
	// 직원정보 변경폼 출력
	@Override
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		String empno = request.getParameter("empno");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>직원정보 변경폼</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>직원정보 변경</h1>");
		
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
				out.println("<form action='updateEmp' method='post'>");
				out.println("직원번호 <input type='text' readonly name='empno' value='" +
						empno + "'><br>");
				out.println("이름 <input type='text' name='ename' value='" +
						rs.getString("ename") + "'><br>");
				out.println("직무 <input type='text' name='job' value='" +
						rs.getString("job") + "'><br>");
				out.println("관리자 <input type='text' name='mgr' value='" +
						rs.getInt("mgr") + "'><br>");
				out.println("입사일 <input type='text' name='hiredate' value='" +
						rs.getDate("hiredate") + "'><br>");
				out.println("급여 <input type='text' name='sal' value='" +
						rs.getInt("sal") + "'><br>");
				out.println("상여금 <input type='text' name='comm' value='" +
						rs.getInt("comm") + "'><br>");
				out.println("부서번호 <input type='text' name='deptno' value='" +
						rs.getInt("deptno") + "'><br>");
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
			
		} finally {
			try { rs.close(); } catch (Exception e) {}
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
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
		int empno = Integer.parseInt(request.getParameter("empno"));
		String ename = request.getParameter("ename");
		String job = request.getParameter("job");
		int mgr = Integer.parseInt(request.getParameter("mgr"));
		String hiredate = request.getParameter("hiredate");
		int sal = Integer.parseInt(request.getParameter("sal"));
		int comm = Integer.parseInt(request.getParameter("comm"));
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		
		DBConnectionPool dbPool = null;
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			dbPool = (DBConnectionPool) this.getServletContext().getAttribute("dbPool");
			
			con = dbPool.getConnection();
			
			// 값이 들어갈 자리는 ?(in-parameter)로 채운다.
			stmt = con.prepareStatement(
					"update emp set " +
					" ename=?,job=?,mgr=?,hiredate=?," +
					" sal=?,comm=?,deptno=?" +
					" where empno=?");
			
			stmt.setString(1, ename);
			stmt.setString(2, job);
			stmt.setInt(3, mgr);
			stmt.setString(4, hiredate);
			stmt.setInt(5, sal);
			stmt.setInt(6, comm);
			stmt.setInt(7, deptno);
			stmt.setInt(8, empno);
			
			int count = stmt.executeUpdate();
			
			if (count > 0) {
				response.sendRedirect("retrieveEmp?empno=" + empno);
				return;
			} else {
				out.println("<p>변경할 직원을 찾을 수 없습니다.</p>");
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

















