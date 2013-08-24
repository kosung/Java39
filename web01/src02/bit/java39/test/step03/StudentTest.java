package bit.java39.test.step03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		//writeStudent();
		
		readStudent();
	}

	private static void readStudent() throws FileNotFoundException, IOException {
		FileReader in = new FileReader("test2.dat");
		BufferedReader in2 = new BufferedReader(in);
		
		String line = in2.readLine(); //홍길동,23,111-1111
		String[] items = line.split(",");
		
		in2.close();
		in.close();
		
		Student s1 = new Student();
		s1.setName(items[1]);
		s1.setAge(Integer.parseInt(items[2]));
		
		if (items[0].equals("2")) {
			s1.setTel(items[3]);
		}
		
		System.out.println(s1.getName());
		System.out.println(s1.getAge());
		System.out.println(s1.getTel());
	}

	private static void writeStudent() throws IOException {
		Student s1 = new Student();
		
		s1.setName("유관순");
		s1.setAge(30);
		s1.setTel("111-1111");
		
		FileWriter out = new FileWriter("test2.dat");
		PrintWriter out2 = new PrintWriter(out);
		
		out2.print(Student.versionUID);
		out2.print(",");
		out2.print(s1.getName());
		out2.print(",");
		out2.print(s1.getAge());
		out2.print(",");
		out2.println(s1.getTel());
		
		out2.close();
		out.close();
	}

}
