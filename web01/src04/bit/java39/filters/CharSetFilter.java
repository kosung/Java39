package bit.java39.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharSetFilter implements Filter {
	FilterConfig config;
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(
			ServletRequest request, 
			ServletResponse response,
			FilterChain nextFilter) throws IOException, ServletException {
		String encoding = config.getInitParameter("encoding");
		
		//System.out.println("before filter....");
		request.setCharacterEncoding(encoding);
		
		nextFilter.doFilter(request, response);
		
		//System.out.println("after filter....");
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}






















