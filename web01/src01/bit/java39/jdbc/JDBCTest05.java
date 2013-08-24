package bit.java39.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/* jdbc05. 데이터 값 삭제하기
 * - executeUpdate(DML 또는 DDL) 메서드를 호출한다. - insert, update, delete, create table, drop table, ...
 */
public class JDBCTest05 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.253:1521:xe", "scott", "tiger");
			Statement stmt = con.createStatement();
			
			// * executeUpdate() 메서드는 입력, 변경, 삭제된 레코드의 수를 리턴한다.
			int count = stmt.executeUpdate("delete dept where deptno=50");
			
			if (count > 0) {
				System.out.println("삭제 성공입니다!");
			} else {
				System.out.println("삭제할 레코드를 찾지 못했습니다!");
			}
			
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}









