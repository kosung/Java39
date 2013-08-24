package bit.java39.test.step4;

public class Calculator {
	double result;
	
	public void plus(/*Calculator this,*/ double a) {
		this.result += a;
	}
	
	public void minus(/*Calculator this,*/ double a) {
		this.result -= a;
	}
	
	public void multiple(/*Calculator this,*/ double a) {
		this.result *= a;
	}
	
	public void divide(/*Calculator this,*/ double a) {
		this.result /= a;
	}
}
