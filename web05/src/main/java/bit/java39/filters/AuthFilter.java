package bit.java39.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

//@WebFilter(urlPatterns="*.do")
public class AuthFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response,
			FilterChain next) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String servletPath = req.getServletPath();
		
		if (servletPath.startsWith("/auth")) {
			next.doFilter(request, response);
		} else {
			HttpSession session = req.getSession();
			if( session.getAttribute("customer") != null) {
				next.doFilter(request, response);
			} else {
				resp.setContentType("text/plain;charset=UTF-8");
				PrintWriter out = resp.getWriter();
				HashMap<String,String> result = new HashMap<String,String>();
				result.put("status", "not_authorized");
				
				out.println(new Gson().toJson(result));
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
