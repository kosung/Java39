package bit.java39.test.step02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamTest {
	public static void main(String[] args) throws Exception {
		//test01();
		//test02();
		
		byte b = 20;  // 0x14
		int i = 23456; // 
		
		FileOutputStream out0 = new FileOutputStream("test03.dat");
		DataOutputStream out = new DataOutputStream(out0);
		out.write(b);
		out.writeInt(i);
		out.close();
	}

	private static void test02() throws FileNotFoundException, IOException,
			Exception {
		byte b = 20;  // 0x14
		int i = 23456; // 
		
		MyFileOutputStream out = new MyFileOutputStream("test02.dat");
		out.write(b);
		out.writeInt(i);
		out.close();
	}

	private static void test01() throws FileNotFoundException, IOException {
		byte b = 20;  // 0x14
		int i = 23456; // 
		
		FileOutputStream out = new FileOutputStream("test01.dat");
		out.write(b);
		out.write(i >> 24);
		out.write(i >> 16);
		out.write(i >> 8);
		out.write(i);
		
		
		out.close();
	}

}
