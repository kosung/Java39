package bit.java39.test.step02;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MyFileOutputStream extends FileOutputStream {

	public MyFileOutputStream(String name) throws FileNotFoundException {
		super(name);
	}
	
	public void writeInt(int value) throws Exception {
		this.write(value >> 24);
		this.write(value >> 16);
		this.write(value >> 8);
		this.write(value);
		
	}

}
