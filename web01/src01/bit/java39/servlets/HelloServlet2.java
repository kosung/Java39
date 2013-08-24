package bit.java39.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet2 implements Servlet {
	ServletConfig config;
	
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
		
		// 최초로 파라미터 값을 꺼내기 전에 클라이언트가 보낸 문자의 코드를 먼저 설정해야 한다.
		// 단, POST 방식에만 해당!
		// GET? 서블릿 컨테이너 마다 달라요!
		// 톰캣은? 구글링!
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt( request.getParameter("age") );
		
		char c;
		for(int i = 0; i < name.length(); i++) {
			c = name.charAt(i);
			System.out.println( Integer.toHexString(c) );
		}
		
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("안녕하세요!");
		out.println("이름: " + name);
		out.println("나이: " + age);
		
	}

}






