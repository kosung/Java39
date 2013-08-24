package bit.java39.dao;

import bit.java39.vo.Customer;

public interface CustomerDao {
	boolean isExist(String id, String password) throws Exception;
	Customer selectOne(String id) throws Exception;
	/*
	List<Customer> selectList() throws Exception;
	int insert(Customer customer) throws Exception;
	
	int delete(String id) throws Exception;
	int update(Customer customer) throws Exception;
	*/
}
















