package bit.java39.servlets;

public class Calculator {
	
	// static method
	// - 인스턴스를 구분하여 작업을 수행하는 메서드가 아니라면, 
	//   클래스 메서드(스태틱 메서드)로 만든다. 
	static public double plus(double a, double b) {
		return a + b;
	}
	
	static public double multiple(double a, double b) {
		return a * b;
	}
	
	static public double minus(double a, double b) {
		return a - b;
	}
	
	static public double divide(double a, double b) {
		return a / b;
	}
}
