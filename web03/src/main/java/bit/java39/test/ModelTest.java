package bit.java39.test;

import java.util.HashMap;
import java.util.Map;

public class ModelTest {
	public static void main(String[] args) {
		HashMap<String,String> realbaguni = new HashMap<String,String>();
		
		execute(10, realbaguni);
		
		System.out.println(realbaguni.get("name"));
	}
	
	public static void execute(int deptno, Map<String,String> baguni) {
		baguni.put("name", "홍길동");
		
	}
}
