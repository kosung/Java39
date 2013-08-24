package bit.java39.test.step01;

public class StudentTest {

	public static void main(String[] args) {
		Student s1 = new Student();
		//s1.name = "홍길동";
		//s1.age = 300;
		
		// 인스턴스 변수에 역할에 맞지 않은 무효한 데이터가 허용되면,
		// 추상화가 무너지게 된다.
		// * 추상화란?
		// - 시스템을 역할에 따라 식별하여 클래스로 정의한 것.
		
		// * 캡슐화(encapsulation: 데이터를 보호)
		// - 추상화가 무너지지 않게 하는 방법?
		//   무효한 데이터를 함부로 넣지 않게 한다.
		// - 데이터의 접근 제한: private, protected, (default), public 
		// - 데이터에 값을 넣고 빼내기 위한 방법(method)를 제공.
		// - 인스턴스가 자신을 역할을 제대로 수행할 수 있도록 유효한 데이터만 넣기 위한 
		//   제어방법.
		
		s1.setName("유관순");
		s1.setAge(300);
		
		System.out.println(s1.getName());
		System.out.println(s1.getAge());
	}

}
