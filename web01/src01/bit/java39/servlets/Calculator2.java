package bit.java39.servlets;

import java.lang.reflect.Method;
import java.util.HashMap;

// Class == Role(역할을 정의한 것)
public class Calculator2 {
	// 인스턴스 변수
	// - 인스턴스가 역할을 수행하는데 필요한 값을 보관하는 변수
	HashMap<String,String> opMap = new HashMap<String,String>();
	
	// 생성자
	// - 역할을 수행하기 위해 필요한 작업을 준비하는 것. 
	private Calculator2() {
		opMap.put("plus", "+");
		opMap.put("minus", "-");
		opMap.put("multiple", "*");
		opMap.put("divide", "/");
		opMap.put("mod", "%");
		
	}
	
	static Calculator2 instance;
	static public Calculator2 getInstance() {
		if (instance == null) {
			instance = new Calculator2();
		}
		
		return instance;
	}
	
	public double calculate(double a, double b, String operator) 
		throws Exception {
		//1. 클래스 정보를 볼 수 있는 거울 객체를 얻는다.
		Class c = this.getClass();
		
		//2. 해당 클래스의 public 메서드 목록을 얻는다.
		Method[] methodList = c.getMethods();
		
		for (Method m : methodList) {
			//operator와 같은 이름이 같은 메서드를 검사한다.
			if (m.getName().equals(operator)) {
				//Double temp = (Double) m.invoke(this, a, b); // boxing
				//result = temp; // unboxing
				return (Double) m.invoke(this, a, b);
				
				// 계산하고 리턴.
			}
		}
		
		throw new Exception("오호라.. 해당 연산자 없어요.");
	}
	
	public String toOperator(String opTitle) {
		return opMap.get(opTitle);
	}
	
	public double plus(double a, double b) {
		return a + b;
	}
	
	public double multiple(double a, double b) {
		return a * b;
	}
	
	public double minus(double a, double b) {
		return a - b;
	}
	
	public double divide(double a, double b) {
		return a / b;
	}
	
	public double mod(double a, double b) {
		return a % b;
	}
}










