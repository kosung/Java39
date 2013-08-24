package bit.java39.test;

import java.util.HashMap;

//1. plus, minus, multiple, divide, mod 메서드 정의
//2. opMap 인스턴스 변수 선언
//3. 생성자에서 opMap 초기화
//3. Singleton 패턴 적용
//4. calculate() 메서드 정의 
//   1) switch() 문 사용하여 처리
//	 2) refrection API 사용하여 처리
public class Calculator {
	
	//public Calculator() {}
	
	public double calculate(double a, double b, String op) 
			throws Exception {
		switch(op) {
		case "plus":
			return plus(a, b);
		case "minus":
			return minus(a, b);
		case "multiple":
			return multiple(a, b);
		case "divide":
			return divide(a, b);
		case "mod":
			return mod(a, b);
		default:
			throw new Exception("연산자가 없습니다!");
		}
	}
	
	public double plus(double a, double b) {
		return a + b;
	}
	
	public double minus(double a, double b) {
		return a - b;
	}
	
	public double multiple(double a, double b) {
		return (a * b);
	}
	
	public double divide(double a, double b) {
		double r = a / b;
		return r;
	}
	
	public double mod(double a, double b) {
		return a % b;
	}
	
}
