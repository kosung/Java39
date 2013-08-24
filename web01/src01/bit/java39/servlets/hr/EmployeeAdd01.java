package bit.java39.servlets.hr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.utils.DBConnectionPool;

public class EmployeeAdd01 extends HttpServlet {
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
		out.println("<title>직원정보 입력폼</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='addEmp' method='post'>");
		out.println("직원번호 <input type='text' name='empno'><br>");
		out.println("이름 <input type='text' name='ename'><br>");
		out.println("직무 <input type='text' name='job'><br>");
		out.println("관리자 <input type='text' name='mgr'><br>");
		out.println("입사일 <input type='text' name='hiredate'><br>");
		out.println("급여 <input type='text' name='sal'><br>");
		out.println("상여금 <input type='text' name='comm'><br>");
		out.println("부서번호 <input type='text' name='deptno'><br>");
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
		out.println("<html><body>");
		
		DBConnectionPool dbPool = null;
		Connection con = null;
		Statement stmt = null;
		
		try {
			dbPool = (DBConnectionPool) this.getServletContext().getAttribute("dbPool");
			
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			int count = stmt.executeUpdate(
					"insert into emp" +
					" values (" + 
					empno + ",'" + 
					ename + "','" +
					job + "'," +
					mgr + ",'" +
					hiredate + "'," +
					sal + "," +
					comm + "," +
					deptno + ")");
			
			if (count > 0) {
				out.println("<p>등록 성공입니다.</p>");
			} else {
				out.println("<p>등록 실패입니다.</p>");
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

















