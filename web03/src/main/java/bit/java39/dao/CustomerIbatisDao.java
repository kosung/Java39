package bit.java39.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.java39.vo.Customer;

//@Repository
public class CustomerIbatisDao implements CustomerDao {
	SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/*
	public List<Employee> getEmployeeList(int departmentNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			return sqlSession.selectList("bit.java39.dao.EmployeeDao.getEmployeeList");

		} catch (Exception e) {
			throw e;
			
		} finally {
			sqlSession.close();
		}
	}

	public int insert(Employee employee) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int count = sqlSession.insert("bit.java39.dao.EmployeeDao.insert", employee);
			sqlSession.commit(); // SQL 작업을 DB에 적용할 것을 명령!
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback(); // 작업한 것을 모두 취소.
			throw e;
			
		} finally {
			sqlSession.close();
		}
	}
	
	
	
	public int delete(int empNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int count = sqlSession.delete("bit.java39.dao.EmployeeDao.delete", empNo);
			sqlSession.commit();
			return count;
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			sqlSession.close();
		}
	}
	
	public int update(Employee employee) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			int count = sqlSession.update("bit.java39.dao.EmployeeDao.update", employee);
			sqlSession.commit();
			return count;

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
			
		} finally {
			sqlSession.close();
		}
	}
*/
	@Override
	public boolean isExist(Map<String,String> params /*String id, String password*/) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			/*
			HashMap<String,String> values = new HashMap<String,String>();
			values.put("id", id);
			values.put("password", password);
			*/
			
			Integer count = sqlSession.selectOne(
					"bit.java39.dao.CustomerDao.isExist", params);
			if(count > 0) { /* auto unboxing: Integer 객체 > int 값으로 변환  */
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw e;
			
		} finally {
			sqlSession.close();
		}
	}
	
	public Customer selectOne(String id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			return sqlSession.selectOne(
					"bit.java39.dao.CustomerDao.selectOne", id);

		} catch (Exception e) {
			throw e;
			
		} finally {
			sqlSession.close();
		}
	}

}
















