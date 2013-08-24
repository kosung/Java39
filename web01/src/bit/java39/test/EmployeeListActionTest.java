package bit.java39.test;

import java.util.ArrayList;
import java.util.List;

import bit.java39.controls.hr.EmployeeListAction;
import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

public class EmployeeListActionTest {

	public static void main(String[] args) {
		class EmployeeDaoMockup implements EmployeeDao {
			@Override
			public List<Employee> getEmployeeList(int departmentNo)
					throws Exception {
				ArrayList<Employee> list = new ArrayList<Employee>();
				list.add(new Employee()
							.setNo(1)
							.setName("홍길동")
							.setJob("관리자")
							.setDepartmentName("영업")
							.setSalary(3000));
				
				list.add(new Employee()
							.setNo(2)
							.setName("홍길동2")
							.setJob("관리자")
							.setDepartmentName("영업")
							.setSalary(3000));
				
				list.add(new Employee()
							.setNo(3)
							.setName("홍길동3")
							.setJob("관리자")
							.setDepartmentName("영업")
							.setSalary(3000));
				
				return list;
			}

			@Override
			public int insert(Employee employee) throws Exception {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Employee getEmployee(int empNo) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int delete(int empNo) throws Exception {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int update(Employee employee) throws Exception {
				// TODO Auto-generated method stub
				return 0;
			}
		}
		
		EmployeeListAction action = new EmployeeListAction();
		action.setEmployeeDao(new EmployeeDaoMockup());
		//action.execute(request, response);
		
	}

}






