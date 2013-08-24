package bit.java39.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/* jdbc02. 데이터베이스 질의하기 및 결과 가져오기
 * - java.sql.Connection 객체를 통하여 데이터베이스에 질의를 수행할 객체 얻는다.
 * - executeQuery(sql) 메서드를 사용하여 SQL을 DBMS에 전달한다.
 *   리턴 값은 데이터베이스로부터 결과를 요청할 객체이다. java.sql.ResultSet
 * - next() 메서드를 통해 서버에게 결과 레코드 하나를 요청한다.
 */
public class JDBCTest02 {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.253:1521:xe", "scott", "tiger");
			System.out.println("연결 되었습니다!");
			Statement stmt = con.createStatement();
			
			// * 서버에 질의 전달하기
			ResultSet rs = stmt.executeQuery("select deptno, dname, loc from dept");
			// executeQuery() 메서드가 리턴하는 것은 
			// 서버에서 실행한 결과가 아니다.
			// 서버로부터 결과를 요청하는, 그런 역할을 하는 객체를 리턴한다.
			
			// * 서버에 결과 요청하기
			while(rs.next()) { 
				// -> 서버로부터 레코드 한 개를 가져온다.
				
				// * 서버에서 가져온 결과 꺼내기
				
				System.out.print( rs.getInt("deptno") + ",");
				System.out.print( rs.getString("dname") + ",");
				System.out.println( rs.getString("loc"));
				
				/*
				System.out.print( rs.getInt(1) + ",");
				System.out.print( rs.getString(2) + ",");
				System.out.println( rs.getString(3));
				*/
				// getXXX(컬럼명 또는 인덱스) 메서드를 호출하여 레코드의 컬럼 값을 꺼낸다.
				// XXX - 컬럼 데이터의 타입
				// 컬럼명 또는 인덱스 - select 문에서 질의할 때 사용한 컬럼명 또는 순서(1부터 시작)
			}
			
			rs.close();
			stmt.close();
			con.close();
			System.out.println("연결 끊었습니다!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}









