package bit.java39.test.step3;

public class CalcTest {

	public static void main(String[] args) {
		// (10 + 20) * 3 / 2 = 45.0
		// (20 - 7) / 6 * 8 = 17.33333
		Calculator c1 = new Calculator();
		Calculator c2 = new Calculator();
		
		Calculator.plus(c1, 10);
		Calculator.plus(c2, 20);
		
		Calculator.plus(c1, 20);
		Calculator.minus(c2, 7);
		
		Calculator.multiple(c1, 3);
		Calculator.divide(c2, 6);
		
		Calculator.divide(c1, 2);
		Calculator.multiple(c2, 8);
		
		System.out.println(c1.result);
		System.out.println(c2.result);
		
	}

}
