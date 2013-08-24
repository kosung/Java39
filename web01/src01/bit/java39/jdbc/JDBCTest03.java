package bit.java39.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/* jdbc03. 데이터베이스에 입력하기
 * - executeUpdate(DML 또는 DDL) 메서드를 호출한다. - insert, update, delete, create table, drop table, ...
 * - executeQuery(DQL) - select
 */
public class JDBCTest03 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.253:1521:xe", "scott", "tiger");
			Statement stmt = con.createStatement();
			
			// * 서버에 질의 전달하기
			stmt.executeUpdate("insert into dept values(50,'개발1팀','서울')");
			
			stmt.close();
			con.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}









