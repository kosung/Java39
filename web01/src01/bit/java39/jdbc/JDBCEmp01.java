package bit.java39.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCEmp01 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.253:1521:xe", "scott", "tiger");
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(
					"select empno, ename, dname " +
					"from emp left outer join dept " +
					"on dept.deptno = emp.deptno");
			while(rs.next()) { 
				System.out.print( rs.getInt("empno") + ",");
				System.out.print( rs.getString("ename") + ",");
				System.out.println( rs.getString("dname"));
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}









