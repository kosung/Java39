package bit.java39.servlets;

public class Calculator2_b01 {
	
	private Calculator2_b01() {}
	
	static Calculator2_b01 instance;
	static public Calculator2_b01 getInstance() {
		if (instance == null) {
			instance = new Calculator2_b01();
		}
		
		return instance;
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
}
