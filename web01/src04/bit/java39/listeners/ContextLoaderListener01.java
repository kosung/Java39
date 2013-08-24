package bit.java39.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bit.java39.controls.hr.DepartmentAddAction;
import bit.java39.controls.hr.DepartmentDeleteAction;
import bit.java39.controls.hr.DepartmentDetailAction;
import bit.java39.controls.hr.DepartmentListAction;
import bit.java39.controls.hr.DepartmentUpdateAction;
import bit.java39.controls.hr.EmployeeAddAction;
import bit.java39.controls.hr.EmployeeDeleteAction;
import bit.java39.controls.hr.EmployeeDetailAction;
import bit.java39.controls.hr.EmployeeListAction;
import bit.java39.controls.hr.EmployeeUpdateAction;
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
public class ContextLoaderListener01 implements ServletContextListener {

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
			
			EmployeeListAction empListAction = new EmployeeListAction();
			empListAction.setEmployeeDao(employeeDao);
			ctx.setAttribute("/hr/searchEmp.do", empListAction);
			
			EmployeeDetailAction empDetailAction = new EmployeeDetailAction();
			empDetailAction.setEmployeeDao(employeeDao);
			ctx.setAttribute("/hr/retrieveEmp.do", empDetailAction);
			
			EmployeeAddAction empAddAction = new EmployeeAddAction();
			empAddAction.setEmployeeDao(employeeDao);
			ctx.setAttribute("/hr/addEmp.do", empAddAction);
			
			EmployeeDeleteAction empDeleteAction = new EmployeeDeleteAction();
			empDeleteAction.setEmployeeDao(employeeDao);
			ctx.setAttribute("/hr/deleteEmp.do", empDeleteAction);
			
			EmployeeUpdateAction empUpdateAction = new EmployeeUpdateAction();
			empUpdateAction.setEmployeeDao(employeeDao);
			ctx.setAttribute("/hr/updateEmp.do", empUpdateAction);
			
			DepartmentListAction deptListAction = new DepartmentListAction();
			deptListAction.setDepartmentDao(departmentDao);
			ctx.setAttribute("/hr/searchDept.do", deptListAction);
			
			DepartmentAddAction deptAddAction = new DepartmentAddAction();
			deptAddAction.setDepartmentDao(departmentDao);
			ctx.setAttribute("/hr/addDept.do", deptAddAction);
			
			DepartmentDetailAction deptDetailAction = new DepartmentDetailAction();
			deptDetailAction.setDepartmentDao(departmentDao);
			ctx.setAttribute("/hr/retrieveDept.do", deptDetailAction);
			
			DepartmentDeleteAction deptDeleteAction = new DepartmentDeleteAction();
			deptDeleteAction.setDepartmentDao(departmentDao);
			ctx.setAttribute("/hr/deleteDept.do", deptDeleteAction);
			
			DepartmentUpdateAction deptUpdateAction = new DepartmentUpdateAction();
			deptUpdateAction.setDepartmentDao(departmentDao);
			ctx.setAttribute("/hr/updateDept.do", deptUpdateAction);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}






