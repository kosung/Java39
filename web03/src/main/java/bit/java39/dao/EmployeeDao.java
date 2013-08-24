package bit.java39.dao;

import java.util.List;

import bit.java39.vo.Employee;

public interface EmployeeDao {
	List<Employee> getEmployeeList(int departmentNo) throws Exception;
	int insert(Employee employee) throws Exception;
	Employee getEmployee(int empNo) throws Exception;
	int delete(int empNo) throws Exception;
	int update(Employee employee) throws Exception;
}
















