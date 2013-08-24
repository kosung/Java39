package bit.java39.controls;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bit.java39.annotations.Component;
import bit.java39.dao.CustomerDao;
import bit.java39.servlets.Action;

@Component("/main.do")
public class MainAction implements Action {
	CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return "/main.jsp";
		/*
		 * 로그인 여부는 AuthFilter에서 검사한다.
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		if (customer == null) {
			return "redirect:auth/loginForm.html";
		} else {
			return "/main.jsp";
		}
		*/
	}
}











