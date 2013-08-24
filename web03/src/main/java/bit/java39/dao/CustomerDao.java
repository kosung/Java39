package bit.java39.dao;

import java.util.Map;

import bit.java39.vo.Customer;

public interface CustomerDao {
	boolean isExist(Map<String,String> params) throws Exception;
	//boolean isExist(String id, String password) throws Exception;
	Customer selectOne(String id) throws Exception;
	/*
	List<Customer> selectList() throws Exception;
	int insert(Customer customer) throws Exception;
	
	int delete(String id) throws Exception;
	int update(Customer customer) throws Exception;
	*/
}
















