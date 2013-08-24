package bit.java39.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/* 디자인 패턴
 * - Singleton 패턴: DBConnectionPool 객체를 하나만 만들게 했다.
 * 	*.*? 여러 DB에 동시에 접근하는 경우 관리가 힘들다.
 * => 일반 클래스로 만든다. 그 대신 이 객체의 관리는 서블릿 컨테이너가 한다.
 */
public class DBConnectionPool {
	ArrayList<Connection> conList = new ArrayList<Connection>();
	
	private String driverClass;
	private String dburl;
	private String id;
	private String pwd;
	
	public DBConnectionPool(
			String driverClass, String dburl, String id, String pwd) 
			throws ClassNotFoundException{
		this.driverClass = driverClass;
		this.dburl = dburl;
		this.id = id;
		this.pwd = pwd;
		
		Class.forName(this.driverClass);
	}
	
	public Connection getConnection() throws SQLException {
		if (conList.size() > 0) {
			return conList.remove(0);
		} else {
			return DriverManager.getConnection(dburl, id, pwd);
		}
	}
	
	public void returnConnection(Connection con) {
		conList.add(con);
	}
	
	public void close() {
		for(Connection con : conList) {
			try { con.close(); } catch (SQLException e) {}
		}
	}
}




