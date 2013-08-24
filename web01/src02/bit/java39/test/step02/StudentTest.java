package bit.java39.test.step02;

import java.io.FileWriter;
import java.io.PrintWriter;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		Student s1 = new Student();
		
		s1.setName("유관순");
		s1.setAge(30);
		
		FileWriter out = new FileWriter("test.dat");
		PrintWriter out2 = new PrintWriter(out);
		
		out2.print(Student.versionUID);
		out2.print(",");
		out2.print(s1.getName());
		out2.print(",");
		out2.println(s1.getAge());
		
		out2.close();
		out.close();
		
		
	}

}
