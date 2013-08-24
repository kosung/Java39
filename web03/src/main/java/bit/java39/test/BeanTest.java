package bit.java39.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("bit/java39/test/beans.xml"); 
		
		if (ctx.getBean("car") != null) {
			System.out.println("Car 있다.");
		}
		
		//if (ctx.getBean("engine") != null) {
		//	System.out.println("Engine 있다.");
		//}
		
		if (ctx.getBean("tire") != null) {
			System.out.println("Tire 있다.");
		}
		
		Car car = (Car)ctx.getBean("car");
		if (car.getEngine() != null) {
			System.out.println("Engine가 꼽혀있네...헐..");
		} else {
			System.out.println("엥~~ Engine가 없는데?");
		}
		/* @Component
		 * - 인스턴스를 생성하도록 지시
		 * 
		 * @Autowired
		 * - setter 메서드의 파라미터 타입과 일치하는 객체를 찾아서 꼽아라.
		 * - 프로퍼티 이름으로 객체를 찾지 않는다.
		 * - 파라미터의 변수명과 일치하는 객체를 찾지 않는다.
		 * - setter 메서드의 이름과 파라미터의 이름에 상관없이,
		 *   파라미터의 타입과 일치하는 객체를 찾는다.
		 * 
		 * @Qualifier("빈이름")
		 * - 만약 같은 타입의 객체가 여러 개 있을 경우, 
		 *   스프링 프레임워크는 어떤 객체를 꼽아야 할지 모른다. => 오류 발생!
		 * - 어떤 객체인지 이름으로 명시해야 한다.
		 */
		
		
		
		//test05();
		//test04(ctx);
		//test03(ctx);
		//test02(ctx);
		//test01(ctx);
	}

	private static void test05() throws Exception {
		/*
		Object engine = ctx.getBean("engine");
		Object engine2 = ctx.getBean("engine");
		Object engine3 = ctx.getBean("engine");
		*/
		
		HashMap<String,Object> beanContainer = new HashMap<String,Object>();
		beanContainer.put("engine", new EngineFactoryBean());
		beanContainer.put("car", new Car());
	
		Car car = (Car)beanContainer.get("car");
		Object obj = beanContainer.get("engine");
		if (obj instanceof AbstractFactoryBean<?>) {
			//car.setEngine((Engine)((AbstractFactoryBean)obj).getObject());
		} else {
			//car.setEngine((Engine)obj);
		}
	}

	private static void test04(ApplicationContext ctx) {
		Object obj = ctx.getBean("car");
		if (obj instanceof Car) {
			System.out.println("Car의 인스턴스이다.");
		} else if (obj instanceof CarFactory) {
			System.out.println("CarFactory의 인스턴스이다.");
		} else {
			System.out.println(obj.getClass().getName());
		}
		
		Object obj2 = ctx.getBean("car2");
		if (obj2 instanceof Car) {
			System.out.println("car2는 Car의 인스턴스이다.");
		} else if (obj2 instanceof CarFactoryBean) {
			System.out.println("car2는 CarFactoryBean의 인스턴스이다.");
		} else {
			System.out.println(obj2.getClass().getName());
		}
		
		Object obj3 = ctx.getBean("car3");
		System.out.println(obj3.getClass().getName());
	}

	private static void test03(ApplicationContext ctx) {
		Car car = (Car)ctx.getBean("car");
		//List<Tire> list = car.getTireList();
		//System.out.println(list.size());
		
		Map<Object,Object> map = car.getOptions();
		System.out.println(map.get("에어콘"));
		System.out.println(map.get("썬루프"));
	}

	private static void test02(ApplicationContext ctx) {
		Car car = (Car)ctx.getBean("car");
		System.out.println(car.getName());
		System.out.println(car.getMaker());
		System.out.println(car.getEngine());
	}

	private static void test01(ApplicationContext ctx) {
		if (ctx.getBean("engine") != null) {
			System.out.println("엔진 객체 있음!");
		}
	}


}
