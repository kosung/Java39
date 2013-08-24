package bit.java39.test.step5;

public class SonataCar implements Car {

	@Override
	public void init() {
		System.out.println("소나타 준비...");

	}

	@Override
	public void run() {
		System.out.println("소나타 슝슝....");

	}

	@Override
	public void stop() {
		System.out.println("소나타 멈춤...");

	}

}
