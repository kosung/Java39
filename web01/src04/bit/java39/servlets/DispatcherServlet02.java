package bit.java39.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DispatcherServlet02 extends HttpServlet {
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			/*
			System.out.println(request.getRequestURI());
			System.out.println(request.getContextPath());
			System.out.println(request.getServletPath());
			*/
			
			String servletPath = request.getServletPath();
			String viewUrl = null;
			
			Action action = 
				(Action)this.getServletContext().getAttribute(servletPath);
			
			if(action != null) {
				viewUrl = action.execute(request, response);
				
			} else {
				throw new Exception("해당 URL을 위한 서비스를 찾을 수 없습니다.");
			}
			
			if (viewUrl.indexOf("redirect:") == -1) {
				RequestDispatcher rd = 
						request.getRequestDispatcher(viewUrl);
				rd.forward(request, response);
			} else {
				response.sendRedirect(viewUrl.substring(9));
			}
			
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}

}
