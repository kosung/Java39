package bit.java39.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bit.java39.annotations.Component;
import bit.java39.utils.DBConnectionPool;
import bit.java39.vo.Department;

@Component("departmentDao")
public class DepartmentDao {
	DBConnectionPool dbPool;
	
	public void setDBConnectionPool(DBConnectionPool dbPool) {
		this.dbPool = dbPool;
	}
	
	public ArrayList<Department> getDepartmentList() throws Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("select deptno, dname" +
					" from dept" +
					" order by deptno");
			
			ArrayList<Department> list = new ArrayList<Department>();
			Department dept = null;
			
			while(rs.next()) { 
				dept = new Department();
				dept.setNo(rs.getInt("deptno"));
				dept.setName(rs.getString("dname"));
				
				list.add(dept);
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
	
	public int insert(Department department) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = dbPool.getConnection();
			
			stmt = con.prepareStatement(
					"insert into dept values (?,?,?)");
			
			stmt.setInt(1, department.getNo());
			stmt.setString(2, department.getName());
			stmt.setString(3, department.getLocation());

			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
	}
	
	public Department getDepartment(int deptNo) throws Exception {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(
					"select deptno, dname, loc" +
					" from dept" +
					" where deptno=" + deptNo);
			
			if (rs.next()) {
				Department dept = new Department();
				dept.setNo(rs.getInt("deptno"));
				dept.setName(rs.getString("dname"));
				dept.setLocation(rs.getString("loc"));
				return dept;
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
	
	public int delete(int deptNo) throws Exception {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = dbPool.getConnection();
			stmt = con.createStatement();
			
			return stmt.executeUpdate("delete dept where deptno=" + deptNo);

		} catch (Exception e) {
			throw e;
			
		} finally {
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
	}
	
	public int update(Department department) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = dbPool.getConnection();
			
			stmt = con.prepareStatement(
					"update dept set " +
					" dname=?,loc=?" +
					" where deptno=?");
			
			stmt.setString(1, department.getName());
			stmt.setString(2, department.getLocation());
			stmt.setInt(3, department.getNo());
			
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try { stmt.close(); } catch (Exception e) {}
			try { dbPool.returnConnection(con); } catch (Exception e) {}
		}
	}
}
