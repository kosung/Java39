package bit.java39.test.step5;

public class CarDriver {

	public static void main(String[] args) throws Exception {
		//test01(args[0]);
		//Car c = new Sonata();
		Car c = CarFactory.getCar("SoNaTa");
		
		c.init();
		c.run();
		c.stop();

	}
	
	public static void test01(String carClass) throws Exception {
		Class clazz = Class.forName(carClass);
		Car c = (Car) clazz.newInstance();
		
		c.init();
		c.run();
		c.stop();

	}

}
