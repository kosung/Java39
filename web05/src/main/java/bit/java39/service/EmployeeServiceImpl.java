package bit.java39.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.java39.dao.EmployeeDao;
import bit.java39.vo.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public List<Employee> getEmployeeList(int pageNo, int pageSize)
			throws Exception {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		
		return employeeDao.getEmployeeList(params);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int addEmployee(Employee employee) throws Exception {
		employeeDao.insert(employee);
		/*
		employee.setNo(employee.getNo() + 1);
		employee.setName(employee.getName() + "****");
		employeeDao.insert(employee);
		
		employee.setNo(employee.getNo() + 1);
		employee.setName(employee.getName() + "####");
		employeeDao.insert(employee);
		*/
		return 1;
	}

	@Override
	public Employee getEmployee(int empNo) throws Exception {
		return employeeDao.getEmployee(empNo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int removeEmployee(int empNo) throws Exception {
		return employeeDao.delete(empNo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int changeEmployee(Employee employee) throws Exception {
		return employeeDao.update(employee);
	}

	@Override
	public int countAll() throws Exception {
		return employeeDao.countAll();
	}

}














