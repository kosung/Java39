package bit.java39.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class MiniBrowser {

	public static void main(String[] args) throws Exception {
		Socket s = new Socket("www.daum.net", 80);
		
		InputStream in = s.getInputStream();
		InputStreamReader adapter = new InputStreamReader(in);
		BufferedReader in2 = new BufferedReader(adapter); 
		
		OutputStream out = s.getOutputStream();
		PrintStream out2 = new PrintStream(out);
		
		out2.println("GET / HTTP/1.1");
		out2.println("Host: www.daum.net");
		out2.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		out2.println("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
		out2.println();
		out2.flush();
		
		String line = null;
		while((line = in2.readLine()) != null) {
			System.out.println(line);
		}
		
		out2.close();
		in2.close();
		s.close();
		
		
		
		
		
		
	}

}

















