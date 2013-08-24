package bit.java39.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import bit.java39.utils.DBConnectionPool;
import bit.java39.vo.Employee;

public class EmployeeDao {
	// * DI(Dependancy Injection;의존객체 주입; 의존성 주입) => IoC (Inversion of Control; 역제어)
	// - 객체의 의존성 관리 방법 => 객체 별로 단위 테스트가 쉽다.
	// 어떤 객체가 자신의 역할을 수행하는 다른 객체를 사용해야 한다면,
	// 해당 객체를 직접 만들지 않고, 외부에서 의존하는 객체(Dependancy Object)를 꽂아 주는 방식. 
	DBConnectionPool dbPool;
	
	public void setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbPool = dbPool;
	}
	
	public ArrayList<Employee> getEmployeeList(int departmentNo) throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			String sql = "select e.empno, e.ename, e.job, e.sal, " +
					" d.dname, d.loc, m.ename as manager" + 
					" from emp e left join dept d on e.deptno=d.deptno" +
					" left join emp m on e.mgr=m.empno";
			
			if (departmentNo > 0) {
				sql += " where e.deptno=" + departmentNo;
			}
			
			sql += " order by empno";
			
			rs = stmt.executeQuery(sql);
			
			ArrayList<Employee> list = new ArrayList<Employee>();
			Employee emp = null;
			
			while(rs.next()) { 
				emp = new Employee();
				emp.setNo( rs.getInt("empno") );
				emp.setName( rs.getString("ename") );
				emp.setJob( rs.getString("job") );
				emp.setSalary( rs.getInt("sal") );
				emp.setDepartmentName( rs.getString("dname") );
				emp.setDepartmentLocation( rs.getString("loc"));
				emp.setManagerName( rs.getString("manager") );
				
				list.add(emp);
			}
			
			return list;

		} catch (Exception e) {
			throw e;
			
		} finally {
			try { rs.close(); } catch (Exception e) {}
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
	}
	
	public int insert(Employee employee) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = dbPool.getConnection();
			
			stmt = con.prepareStatement(
					"insert into emp values (?,?,?,?,?,?,?,?)");
			
			stmt.setInt(1, employee.getNo());
			stmt.setString(2, employee.getName());
			stmt.setString(3, employee.getJob());
			stmt.setInt(4, employee.getManagerNo());
			stmt.setDate(5, employee.getHireDate());
			stmt.setInt(6, employee.getSalary());
			stmt.setInt(7, employee.getCommission());
			stmt.setInt(8, employee.getDepartmentNo());
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
	}
	
	public Employee getEmployee(int empNo) throws Exception {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(
					"select ename,job,mgr,hiredate,sal,comm,deptno" +
					" from emp" +
					" where empno=" + empNo);
			
			if (rs.next()) {
				Employee emp = new Employee();
				emp.setNo(empNo);
				emp.setName(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setManagerNo(rs.getInt("mgr"));
				emp.setHireDate(rs.getDate("hiredate"));
				emp.setSalary(rs.getInt("sal") );
				emp.setCommission(rs.getInt("comm") );
				emp.setDepartmentNo(rs.getInt("deptno"));
				
				return emp;
			} else {
				return null;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try { rs.close(); } catch (Exception e) {}
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
	}
	
	public int delete(int empNo) throws Exception {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			return stmt.executeUpdate("delete emp where empno=" + empNo);

		} catch (Exception e) {
			throw e;
			
		} finally {
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
	}
	
	public int update(Employee employee) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = dbPool.getConnection();
			
			stmt = con.prepareStatement(
					"update emp set " +
					" ename=?,job=?,mgr=?,hiredate=?," +
					" sal=?,comm=?,deptno=?" +
					" where empno=?");
			
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getJob());
			stmt.setInt(3, employee.getManagerNo());
			stmt.setDate(4, employee.getHireDate());
			stmt.setInt(5, employee.getSalary());
			stmt.setInt(6, employee.getCommission());
			stmt.setInt(7, employee.getDepartmentNo());
			stmt.setInt(8, employee.getNo());
			
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
	}
}
















