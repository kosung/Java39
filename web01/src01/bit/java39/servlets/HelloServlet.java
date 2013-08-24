package bit.java39.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet {
	ServletConfig config;
	
	// Annotation
	// - 컴파일러와 런타임(JVM)에게 전달하는 특별한 메시지(참고사항,명령,...)
	// - 컴파일러에게 전달하는 주석은 컴파일 때 제거됨.
	// - 런타임에게 전달하는 주석은 컴파일 후에도 클래스 파일에 남아 있음.
	//   실행 중에 참고할 수 있다. 
	// - 다른 일반 주석(/*...*/, //..., /**...*/)들은 컴파일 과정에서 모두 제거됨.
	// @Override - 컴파일러에게 전달하는 메시지-> 이런 메서드를 재정의하겠다.
	//				거짓말하면 바로 들통남!
	@Override
	public void destroy() {
		System.out.println("destroy");
		
	}

	@Override
	public ServletConfig getServletConfig() {
		return this.config;
	}

	@Override
	public String getServletInfo() {
		return "Hello Servlet!";
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		System.out.println("init");
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		System.out.println("service");
		
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("안녕하세요!");
		out.println("Hello");
		
	}

}






