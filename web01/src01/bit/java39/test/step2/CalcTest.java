package bit.java39.test.step2;

public class CalcTest {

	public static void main(String[] args) {
		// (10 + 20) * 3 / 2 = 45.0
		
		Calculator.plus(10);
		Calculator.plus(20);
		Calculator.multiple(3);
		Calculator.divide(2);

		System.out.println(Calculator.result);
	}

}
