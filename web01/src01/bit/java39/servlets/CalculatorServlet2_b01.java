package bit.java39.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet2_b01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		int v1 = Integer.parseInt(request.getParameter("v1"));
		int v2 = Integer.parseInt(request.getParameter("v2"));
		String op = request.getParameter("op");
		
		double result = 0;
		
		//방법1. 매번 인스턴스를 만든다. -> 가비지 생성
		//Calculator calc = new Calculator(); 
		
		//방법2. plus(), minus()... 클래스 메서드로 만든다.
		
		//방법3. Singleton 패턴을 적용한다.
		Calculator2 calc = Calculator2.getInstance();
		
		switch(op) {
		case "plus":
			result = calc.plus(v1, v2);
			break;
		case "minus":
			result = calc.minus(v1, v2);
			break;
		case "multiple":
			result = calc.multiple(v1, v2);
			break;
		case "divide":
			result = calc.divide(v1, v2);
			break;
		}
		
		HashMap<String,String> opMap = new HashMap<String,String>();
		opMap.put("plus", "+");
		opMap.put("minus", "-");
		opMap.put("multiple", "*");
		opMap.put("divide", "/");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>");
		out.println(v1 + " " + opMap.get(op) + " " + v2 + " = " + result);
		out.println("</h1></body></html>");
	}

	
}
