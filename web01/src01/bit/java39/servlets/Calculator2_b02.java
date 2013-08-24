package bit.java39.servlets;

public class Calculator2_b02 {
	
	private Calculator2_b02() {}
	
	static Calculator2_b02 instance;
	static public Calculator2_b02 getInstance() {
		if (instance == null) {
			instance = new Calculator2_b02();
		}
		
		return instance;
	}
	
	public double calculate(double a, double b, String operator) {
		double result = 0;
		
		switch(operator) {
		case "plus":
			result = plus(a, b);
			break;
		case "minus":
			result = minus(a, b);
			break;
		case "multiple":
			result = multiple(a, b);
			break;
		case "divide":
			result = divide(a, b);
			break;
		case "mod":
			result = divide(a, b);
			break;	
		}
		
		return result;
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










