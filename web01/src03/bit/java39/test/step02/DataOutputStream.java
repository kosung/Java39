package bit.java39.test.step02;

import java.io.IOException;
import java.io.OutputStream;

public class DataOutputStream extends OutputStream {
	OutputStream out;
	
	public DataOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public void write(int b) throws IOException {
		this.out.write(b);
	}

	public void writeInt(int value) throws IOException {
		this.out.write(value >> 24);
		this.out.write(value >> 16);
		this.out.write(value >> 8);
		this.out.write(value);
	}
	
}
