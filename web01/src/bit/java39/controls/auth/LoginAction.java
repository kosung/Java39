package bit.java39.controls.auth;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bit.java39.annotations.Component;
import bit.java39.dao.CustomerDao;
import bit.java39.servlets.Action;
import bit.java39.vo.Customer;

@Component("/auth/login.do")
public class LoginAction implements Action {
	CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			Cookie[] cookieList = request.getCookies();
			if (cookieList != null) {
				for (Cookie cookie : cookieList) {
					if (cookie.getName().equals("id")) {
						request.setAttribute("id", cookie.getValue());
					}
				}
			}
			return "/auth/loginForm.jsp";
		}
		
		String id = request.getParameter("id");
		String password = request.getParameter("pwd");
		
		
		if (customerDao.isExist(id, password)) {
			Customer customer = customerDao.selectOne(id);
			
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			
			Cookie cookie = null;
			if (request.getParameter("save") != null) {
				cookie = new Cookie("id", id);
				/* 쿠키의 리턴 범위
				 * - path를 설정하지 않는 경우 => 현재 서블릿의 폴더 경로와 일치할 때 만.
				 * - path를 설정하면 => 그 경로의 하위 서블릿 모두에 대해서 쿠키 전송.
				 */
				//cookie.setPath("/web01");
				cookie.setMaxAge(10);
			} else {
				cookie = new Cookie("id", "");
			}
			response.addCookie(cookie);
			
			return "redirect:../main.do";
		} else {
			return "redirect:login.do";
		}
	}
}











