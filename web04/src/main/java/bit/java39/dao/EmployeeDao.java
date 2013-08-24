package bit.java39.dao;

import java.util.List;
import java.util.Map;

import bit.java39.vo.Employee;

public interface EmployeeDao {
	List<Employee> getEmployeeList(Map<String,Object> params) throws Exception;
	int insert(Employee employee) throws Exception;
	Employee getEmployee(int empNo) throws Exception;
	int delete(int empNo) throws Exception;
	int update(Employee employee) throws Exception;
	int countAll() throws Exception;
}
















