package bit.java39.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/* 디자인 패턴
 * - Singleton 패턴: DBConnectionPool 객체를 하나만 만들게 했다.
 * 	*.*? 여러 DB에 동시에 접근하는 경우 관리가 힘들다.
 * - Flyweight 패턴: 공유 객체를 관리. 필요할 때 마다 객체를 생성하지 않는다. 속도 빠르다.
 *   특히, 객체 생성하는데 시간이 많이 걸리는 경우(DB Connection, Socket, Thread, etc)
 */
public class DBConnectionPool01 {
	ArrayList<Connection> conList = new ArrayList<Connection>();
	
	static private DBConnectionPool01 instance;
	static private String driverClass;
	static private String dburl;
	static private String id;
	static private String pwd;
	
	static public DBConnectionPool01 getInstance(
			String driverClass, String dburl, String id, String pwd) 
		throws Exception {
		
		if (instance == null) {
			DBConnectionPool01.driverClass = driverClass;
			DBConnectionPool01.dburl = dburl;
			DBConnectionPool01.id = id;
			DBConnectionPool01.pwd = pwd;
			instance = new DBConnectionPool01();
		}
			
		return instance;
	}
	
	private DBConnectionPool01() throws ClassNotFoundException{
		Class.forName(DBConnectionPool01.driverClass);
	}
	
	public Connection getConnection() throws SQLException {
		System.out.println("getConnection....");
		if (conList.size() > 0) {
			return conList.remove(0);
		} else {
			return DriverManager.getConnection(
					DBConnectionPool01.dburl, 
					DBConnectionPool01.id, 
					DBConnectionPool01.pwd);
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




