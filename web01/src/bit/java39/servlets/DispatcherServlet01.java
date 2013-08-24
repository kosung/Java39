package bit.java39.servlets;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
			@SuppressWarnings("unchecked")
			Hashtable<String,Object> beanContainer = 
					(Hashtable<String,Object>)this.getServletContext().getAttribute("beanContainer");
			
			Action action = (Action) beanContainer.get(servletPath); 
			
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
