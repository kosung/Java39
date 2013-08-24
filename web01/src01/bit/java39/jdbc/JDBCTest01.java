package bit.java39.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/* jdbc01. 드라이버 클래스 로딩 테스트
 * - java.sql.Driver 인테페이스를 구현한 클래스를 로딩
 * - Connection 객체를 생성해서 리턴해주기 때문.
 */
public class JDBCTest01 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.253:1521:xe", "scott", "tiger");
			System.out.println("연결 되었습니다!");
			//Statement stmt = con.createStatement();
			//ResultSet rs = stmt.executeQuery("select deptno, dname, loc from dept");
			
			//rs.close();
			//stmt.close();
			con.close();
			System.out.println("연결 끊었습니다!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}









