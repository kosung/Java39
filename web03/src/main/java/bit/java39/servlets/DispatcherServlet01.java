package bit.java39.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

//@WebServlet(urlPatterns="*.do")
@SuppressWarnings("serial")
public class DispatcherServlet01 extends HttpServlet {
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String servletPath = request.getServletPath();
			String viewUrl = null;
			
			ApplicationContext appContext = 
					(ApplicationContext)this.getServletContext()
											.getAttribute("appContext");
			try {
				Action action = (Action)appContext.getBean(servletPath);
				viewUrl = action.execute(request, response);
				if (viewUrl.indexOf("redirect:") == -1) {
					RequestDispatcher rd = 
							request.getRequestDispatcher(viewUrl);
					rd.forward(request, response);
				} else {
					response.sendRedirect(viewUrl.substring(9));
				}
				
			} catch (Throwable e) {
				throw new Exception("해당 URL을 위한 서비스를 찾을 수 없습니다.");
			}
			
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}

}
