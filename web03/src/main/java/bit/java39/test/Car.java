package bit.java39.test;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {
	String name;
	String maker;

	/*
	 * 인스턴스에 @Autowired를 선언해도 자동으로 의존객체를 주입해준다.
	 * 그런데, 인스턴스가 캡슐화가 되어 있어 원래는 접근 불가인데,
	 * 이런 경우 가능하다. => 캡슐화의 의미가 사라진다. => OOP의 특징이 무너진다.
	 * => 많은 OOP 개발자에게서 비난 받는다.
	 * 
	 */
	@Autowired
	@Qualifier("ohengine")
	Engine engine;
	
	List<Tire> tireList;
	Map<Object,Object> options;
	
	public Car() {}
	
	public Car(String name, String maker, Engine engine) {
		this.name = name;
		this.maker = maker;
		this.engine = engine;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public Engine getEngine() {
		return engine;
	}
	/*
	@Autowired
	//@Qualifier("ohengine2")
	public void setEngine(@Qualifier("ohengine2") Engine engine) {
		this.engine = engine;
	}
	
	//@Autowired
	public void setOkbari(Engine engine, Tire tire) {
		System.out.println(engine);
		System.out.println(tire);
	}
	
	public List<Tire> getTireList() {
		return tireList;
	}
	
	@Autowired
	public void setTireList(List<Tire> tireList) {
		this.tireList = tireList;
	}
	*/
	public Map<Object, Object> getOptions() {
		return options;
	}

	public void setOptions(Map<Object, Object> options) {
		this.options = options;
	}
	

}









