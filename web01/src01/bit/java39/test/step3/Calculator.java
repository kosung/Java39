package bit.java39.test.step3;

public class Calculator {
	double result;
	
	static public void plus(Calculator that, double a) {
		that.result = that.result + a;
	}
	
	static public void minus(Calculator that, double a) {
		that.result -= a;
	}
	
	static public void multiple(Calculator that, double a) {
		that.result *= a;
	}
	
	static public void divide(Calculator that, double a) {
		that.result /= a;
	}
}
