package bit.java39.test;

import java.util.Properties;

public class SystemPropertyTest {
	public static void main(String[] args) {
		for (String arg : args) {
			System.out.println("=>" + arg);
		}
		
		for (int i = 0; i < 40; i++) {
			System.out.print("-");
		}
		System.out.println();
		
		Properties props = System.getProperties();
		for(Object key : props.keySet()) {
			System.out.println(key + "=" + props.getProperty((String)key));
		}
	}

}
