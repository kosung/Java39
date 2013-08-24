package bit.java39.test.step4;

public class CalcTest {

	public static void main(String[] args) {
		// (10 + 20) * 3 / 2 = 45.0
		// (20 - 7) / 6 * 8 = 17.33333
		Calculator c1 = new Calculator();
		Calculator c2 = new Calculator();
		
		c1.plus(10);
		c2.plus(20);
		
		c1.plus(20);
		c2.minus(7);
		
		c1.multiple(3);
		c2.divide(6);
		
		c1.divide(2);
		c2.multiple(8);
		
		System.out.println(c1.result);
		System.out.println(c2.result);
		
	}

}
