package bit.java39.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/* jdbc04. 데이터 값 변경하기
 * - executeUpdate(DML 또는 DDL) 메서드를 호출한다. - insert, update, delete, create table, drop table, ...
 */
public class JDBCTest04 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.253:1521:xe", "scott", "tiger");
			Statement stmt = con.createStatement();
			
			// * 서버에 질의 전달하기
			stmt.executeUpdate("update dept set loc='춘천' where deptno=50");
			
			stmt.close();
			con.close();
			System.out.println("변경 성공입니다!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}









