package bit.java39.controls.auth;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bit.java39.annotations.Component;
import bit.java39.servlets.Action;

@Component("/auth/logout.do")
public class LogoutAction implements Action {
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		/* 기존의 세션 객체 무효화 
		 * - 기존에 생성된 세션 객체무효화 시킨다.
		 * - 다음에 세션을 요청하면 새로 만들어서 리턴한다.
		 */
		session.invalidate();
		
		
		return "redirect:login.do";
	}
}











