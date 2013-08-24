package bit.java39.test;

import java.util.ArrayList;

import org.springframework.beans.factory.config.AbstractFactoryBean;

public class EngineFactoryBean extends AbstractFactoryBean<Engine> {
	ArrayList<Engine> pool = new ArrayList<Engine>();
	@Override
	protected Engine createInstance() throws Exception {
		System.out.println("createInstance.....");
		if (pool.size() > 0) {
			return pool.remove(0);
		} else {
			return new Engine();
		}
	}

	@Override
	public Class<?> getObjectType() {
		return Engine.class;
	}

}
