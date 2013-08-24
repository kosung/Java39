package bit.java39.test;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameTest {

	public static void main(String[] args) {
		Frame f = new Frame("okok");
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		f.setLayout(new FlowLayout());
		Button btn = new Button("나야 버튼1...");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("오호라.. 자바스크립트와 비슷하네..");
				
			}
		});

		f.add(btn);
		f.add(new Button("나야 버튼2..."));
		f.add(new Button("나야 버튼3..."));
		
		f.setSize(new Dimension(400, 300));
		f.setVisible(true);
	}

}
