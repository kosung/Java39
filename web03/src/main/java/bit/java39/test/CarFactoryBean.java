package bit.java39.test;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class CarFactoryBean extends AbstractFactoryBean<Car>{

	@Override
	protected Car createInstance() throws Exception {
		return new Car();
	}

	@Override
	public Class<?> getObjectType() {
		return Car.class;  // Class.forName("bit.java39.test.Car")
	}

}
