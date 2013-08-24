package bit.java39.controls.auth;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bit.java39.annotations.Component;
import bit.java39.dao.CustomerDao;
import bit.java39.servlets.Action;
import bit.java39.vo.Customer;

//@Component("/auth/login.do")
public class LoginAction01 implements Action {
	CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("pwd");
		
		/* 같은 이름을 가진 여러개의 파라미터가 넘어 온다면, getParameterValues()로 꺼낸다.
		String[] idList = request.getParameterValues("id");
		for(String s : idList) {
			System.out.println(s);
		}*/
		
		if (customerDao.isExist(id, password)) {
			/* 1) 요청 헤더에 세션 아이디가 있다면,
			 * - 세션의 timeout 시간을 넘지 않았다면, 
			 * -----> 기존에 생성된 세션 객체를 찾아서 리턴.
			 * - 타임아웃 시간을 지났다면 
			 * =====> 새로 세션 객체를 생성하여 리턴.
			 * 2) 요청 헤더에 세션 아이디가 없다면 
			 * =====> 새로 HttpSession객체를 만들어서 리턴.
			 * $$$
			 * 새로 세션 객체가 만들어진 경우!
			 * - 응답 헤더에 세션 아이디를 쿠기로 넘긴다.
			 * ex) Set-Cookie: JSESSIONID=XXXXXXXXXXX
			 * $$$
			 * 브라우저는 서버에 요청할 때 항상 서버로부터 받은 쿠키 정보를 모두 넘긴다.
			 * ex) Cookie: JSESSIONID=XXXXXXXXX
			 *     Cookie: ...=....
			 */
			HttpSession session = request.getSession();
			
			Customer customer = customerDao.selectOne(id);
			session.setAttribute("customer", customer);
			
			/* checkbox 파라미터
			 * - 체크하지 않으면 파라미터 값이 넘어 오지 않는다.
			 * - 체크하면,
			 * 		=> 기본 값 'on'이 넘어 온다.
			 *		=> value 속성이 있다면, 해당 값이 넘어 온다.
			 */
			if (request.getParameter("save") != null) {
				Cookie cookie = new Cookie("id", id);
				//cookie.setPath("/web01");
				cookie.setMaxAge(100);
				response.addCookie(cookie);
				
				// 직접 쿠기를 설정하는 헤더를 추가할 수 있다.
				//response.addHeader("Set-Cookie", "id=" + id);
			}
			
			return "login.jsp";
		} else {
			return "redirect:loginForm.html";
		}
	}
}











