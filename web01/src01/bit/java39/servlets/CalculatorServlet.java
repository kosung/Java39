package bit.java39.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CalculatorServlet extends javax.servlet.GenericServlet {
	public double plus(double a, double b) {
		return a + b;
	}
	
	public double multiple(double a, double b) {
		return a * b;
	}
	
	public double minus(double a, double b) {
		return a - b;
	}
	
	public double divide(double a, double b) {
		return a / b;
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int v1 = Integer.parseInt(request.getParameter("v1"));
		int v2 = Integer.parseInt(request.getParameter("v2"));
		String op = request.getParameter("op");
		
		double result = 0;
		
		switch(op) {
		case "plus":
			result = plus(v1, v2);
			break;
		case "minus":
			result = minus(v1, v2);
			break;
		case "multiple":
			result = multiple(v1, v2);
			break;
		case "divide":
			result = divide(v1, v2);
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













