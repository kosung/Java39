package bit.java39.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {		
		//request.setCharacterEncoding("UTF-8");
		int v1 = Integer.parseInt(request.getParameter("v1"));
		int v2 = Integer.parseInt(request.getParameter("v2"));
		String op = request.getParameter("op");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		
		try {
			// 개선: 연산을 Calculator2 객체로 위임한다.
			Calculator2 calc = Calculator2.getInstance();
			double result = calc.calculate(v1, v2, op);
			out.println("<h1>");
			out.println(v1 + " " + calc.toOperator(op) + " " + v2 + " = " + result);
			out.println("</h1>");
			
		} catch (Exception e) {
			out.println("<p>" + e.toString() + "</p>");
		}
		
		out.println("</body></html>");
		
		System.out.println("service....");
	}

	
}









