package bit.java39.test.step1;

public class CalcTest {

	public static void main(String[] args) {
		// (10 + 20) * 3 / 2 = 45.0
		
		double result = 0;
		
		result = Calculator.plus(10, 20);
		result = Calculator.multiple(result, 3);
		result = Calculator.divide(result, 2);

		System.out.println(result);
	}

}
