package bit.java39.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bit.java39.dao.DepartmentDao;
import bit.java39.dao.EmployeeDao;
import bit.java39.utils.DBConnectionPool;

/* 
 * 학습목표:
 * 1) ServletContextListener의 두 메서드가 언제 호출되는지 알기위함.
 * 2) ServletContext 객체 사용법 
 *   - 웹 애플리케이션 마다 1개만 존재
 *   - 웹 애플리케이션에 대한 정보 조회
 *   - 웹 애플리케이션에서 공유되는 객체를 보관 (*****)
 */
public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// 웹 애플리케이션이 종료되기 직전에 호출된다.
		// 마무리 작업. DB연결을 close....
		System.out.println("contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 웹 애플리케이션이 시작된 후 즉시 호출 
		// 서블릿이 작업하는데 필요한 준비작업
		System.out.println("contextInitialized");
		try {
			DBConnectionPool pool = new DBConnectionPool(
				"oracle.jdbc.driver.OracleDriver", 
				"jdbc:oracle:thin:@192.168.0.253:1521:xe", 
				"scott", "tiger");
			
			ServletContext ctx = event.getServletContext();
			
			EmployeeDao employeeDao = new EmployeeDao();
			employeeDao.setDBConnectionPool(pool);
			ctx.setAttribute("employeeDao", employeeDao);
			
			DepartmentDao departmentDao = new DepartmentDao();
			departmentDao.setDBConnectionPool(pool);
			ctx.setAttribute("departmentDao", departmentDao);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}






