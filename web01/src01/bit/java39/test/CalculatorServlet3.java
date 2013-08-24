package bit.java39.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet3 extends HttpServlet {
	Calculator calc;
	
	public CalculatorServlet3() {
		calc = new Calculator();
	}
	
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int a = Integer.parseInt( request.getParameter("v1") );
		int b = Integer.parseInt( request.getParameter("v2") );
		String op = request.getParameter("op");
				
		
		// 브라우저에 출력
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//double result = calc.plus(a, b);
		try {
			out.println(a + " " + op + " " + b + " = " 
					+ calc.calculate(a, b, op));
		} catch (Exception e) {
			out.println("오류:" + e.toString());
		}
		// 10 op 20 = 결과
		
		//console 창에 출력 -> 테스트	
		/*
		System.out.println(a);
		System.out.println(b);
		System.out.println(op);
		*/
		
	}
}














