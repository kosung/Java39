package bit.java39.test.step5;

public class TicoCar implements Car {

	@Override
	public void init() {
		System.out.println("티코 준비...");

	}

	@Override
	public void run() {
		System.out.println("티코 달린다..씽씽....");

	}

	@Override
	public void stop() {
		System.out.println("티코 멈춘다....");

	}

}
