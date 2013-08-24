package bit.java39.test.step5;

public class CarFactory {
	/* factory method
	 * - 객체 생성이 복잡한 경우. 초기화를 복잡하게 해야 하는 경우.
	 */
	static public Car getCar(String model) {
		String carModel = model.toLowerCase();
		if ("tico".equals( carModel )) {
			return new TicoCar();
		} else if ("sonata".equals( carModel )) {
			return new SonataCar();
		} else {
			return null;
		}
	}
}
