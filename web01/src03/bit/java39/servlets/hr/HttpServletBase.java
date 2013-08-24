package bit.java39.servlets.hr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
abstract public class HttpServletBase extends HttpServlet {
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String viewUrl = execute(request, response);
			
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
	
	abstract protected String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception;
	
}











