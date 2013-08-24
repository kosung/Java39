package bit.java39.test.step04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		//writeStudent();
		
		readStudent();
	}

	private static void readStudent() throws Exception {
		FileInputStream in = new FileInputStream("test3.dat");
		ObjectInputStream in2 = new ObjectInputStream(in);
		
		Student s1 = (Student)in2.readObject();
		
		in2.close();
		in.close();
		
		System.out.println(s1.getName());
		System.out.println(s1.getAge());
		System.out.println(s1.getTel());
	}

	private static void writeStudent() throws IOException {
		Student s1 = new Student();
		
		s1.setName("홍길동");
		s1.setAge(20);
		s1.setTel("222-2222");
		
		FileOutputStream out = new FileOutputStream("test3.dat");
		ObjectOutputStream out2 = new ObjectOutputStream(out);	
		
		// 해당 인스턴스의 클래스 정보 및 값이 출력됨.
		out2.writeObject(s1);
		
		out2.close();
		out.close();
	}

}
