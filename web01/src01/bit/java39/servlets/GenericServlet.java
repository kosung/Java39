package bit.java39.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// 추상클래스
// - 추상 메서드를 가진 클래스는 인스턴스 생성을 막아야 한다.
// - 안그러면, 인스턴스 생성 후 구현하지 않는 메서드를 호출할 수 있다.
// GenericServlet p = new GenericServlet();
// p.service(null, null);
// - 추상 클래스의 사용 목적: 서브클래스에게 공통 속성과 공통 메서드를 상속해 주는 용도.
//   추상 클래스 그 자신은 직접 사용되지 않는다. 

abstract public class GenericServlet implements Servlet {
	protected ServletConfig config;
	
	public void destroy() {}

	public ServletConfig getServletConfig() {
		return this.config;
	}

	public String getServletInfo() {
		return "default";
	}

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	// 추상 메서드
	// - 수퍼클래스에서 구현할 필요가 없는 메서드. 
	// - 서브클래스에서 구현해야 할 메서드.
	abstract public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException;
}









