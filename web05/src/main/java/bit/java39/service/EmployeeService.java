package bit.java39.service;

import java.util.List;

import bit.java39.vo.Employee;

// Controller 또는 다른 Service 컴포넌트가 사용한다.
// - 메서드의 이름은 업무에 사용되는 용어를 쓴다.
// - 파라미터 선언도 업무에 맞추어 사용한다.
public interface EmployeeService {
	List<Employee> getEmployeeList(int pageNo, int pageSize) throws Exception;
	int addEmployee(Employee employee) throws Exception;
	Employee getEmployee(int empNo) throws Exception;
	int removeEmployee(int empNo) throws Exception;
	int changeEmployee(Employee employee) throws Exception;
	int countAll() throws Exception;
}
