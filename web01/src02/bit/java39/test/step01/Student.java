package bit.java39.test.step01;

public class Student {
	private String name;
	private int	age;
	
	public void setName(String n) {
		if (n == null) {
			this.name = "홍길동";
		} else {
			this.name = n;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAge(int a) {
		if (a > 18 && a < 100) {
			this.age = a;
		} else {
			this.age = 20;
		}
	}
	
	public int getAge() {
		return this.age;
	}
}











