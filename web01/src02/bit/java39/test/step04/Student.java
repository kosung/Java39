package bit.java39.test.step04;

public class Student implements java.io.Serializable {
	// 파일에 출력된 데이터를 구분하기 위한 버전 번호로 사용된다.
	private static final long serialVersionUID = 2L;
	
	private String name;
	private int	age;
	private String tel;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}











