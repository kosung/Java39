package bit.java39.test;

import java.util.HashMap;

/* 학습목표:
 * - Object 클래스로부터 상속 받은 메서드의 용도
 * 
 */



public class BasicTest {
	
	public static void main(String[] args) {
		//test01();
		//test02();
		//test03();
		test04();
	}
	
	public static void test04() {
		/* hashCode() 
		 * - Object로부터 상속받은 hashCode()는 인스턴스 마다 고유의 값을 리턴한다.
		 * - 만약, 값은 값을 가진 객체에 대해 같은 hash값을 리턴하게 하려면 재정의하라!
		 * - HashMap이나 Hashtable에서 키로로 사용할 객체라면 반드시 hashCode()와 equals()를 재정의 해야한다.
		 */
		class Student {
			String name;
			int age;
			
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + age;
				result = prime * result
						+ ((name == null) ? 0 : name.hashCode());
				return result;
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Student other = (Student) obj;
				if (age != other.age)
					return false;
				if (name == null) {
					if (other.name != null)
						return false;
				} else if (!name.equals(other.name))
					return false;
				return true;
			}
			
			
			
		}
		
		Student s1 = new Student();
		s1.name = "홍길동";
		s1.age = 20;
		
		Student s2 = new Student();
		s2.name = "홍길동";
		s2.age = 20;
		
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		
		HashMap<Student, String> 냉장고 = new HashMap<Student,String>();
		냉장고.put(s1, "염소엑기스");
		
		System.out.println(냉장고.get(s2));
		
	}
	
	public static void test03() {
		class Student {
			String name;
			int age;
			
			/*
			 * GC가 해당 인스턴스를 힙에서 제거하기 전에 호출함.
			 * - 만약, GC가 가동되지 않는다면 절대 호출 안됨.
			 * - 결론, 이 메서드를 통해 마무리 작업하는 코드를 작성하지 말것.  
			 */
			@Override
			protected void finalize() throws Throwable {
				System.out.println("call finalize()");
			}
		}
		
		Student s1 = new Student();
		s1.name = "홍길동";
		s1.age = 20;
		
		
	}
	
	public static void test02() {
		/* equals()
		 * - Object로부터 상속받은 equals()는 주소를 비교한다.
		 * - 인스턴스의 내용이 같은지를 비교하도록 재정의할 수 있다.
		 */
		class Student {
			String name;
			int age;

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Student other = (Student) obj;
				if (age != other.age)
					return false;
				if (name == null) {
					if (other.name != null)
						return false;
				} else if (!name.equals(other.name))
					return false;
				return true;
			}
		}
		
		Student s1 = new Student();
		s1.name = "홍길동";
		s1.age = 20;
		
		Student s2 = new Student();
		s2.name = "홍길동";
		s2.age = 20;
		
		if (s1 == s2) {
			System.out.println("s1 변수에 들어있는 주소 값과 s2에 들어있는 주소 값이 같다.");
		} else {
			System.out.println("s1 변수에 들어있는 주소 값과 s2에 들어있는 주소 값이 같지 않다.");
		}
		
		if (s1.equals(s2)) {
			System.out.println("s1.equals(s2) => true");
		} else {
			System.out.println("s1.equals(s2) => false");
		}
		
		/*
		String str1 = new String("홍길동");
		String str2 = new String("홍길동");
		*/
		StringBuffer str1 = new StringBuffer("홍길동");
		StringBuffer str2 = new StringBuffer("홍길동");
		
		if (str1.equals(str2)) {
			System.out.println("str1 == str2");
		} else {
			System.out.println("str1 != str2");
		}
			
	}
	
	public static void test01() {
		class Student {
			String name;
			int age;
			
			@Override
			public String toString() {
				return name + age + "^^";
			}
		}
		
		// toString(): 리턴값은 클래스 이름 + 해시값
		//		- 인스턴스의 개요 값을 리턴하도록 보통 재정의 한다.
		// getClass(): 로딩된 클래스 정보를 다루는 객체(java.lang.Class)
		// hashCode(): 인스턴스를 구분하기 위한 ID 값
		Student s = new Student();
		s.name = "홍길동";
		s.age = 20;
		System.out.println(s.toString());
		System.out.println(s); // println() 함수에서는 파라미터로 넘어온 객체의 toString() 리턴값을 출력한다.
		System.out.println(s.getClass().getName());
		System.out.println(Integer.toHexString(s.hashCode()));
	}

}








