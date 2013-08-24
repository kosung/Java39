package bit.java39.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 
 * Front Controller가 Page Controller를 호출할 때 규칙 
 */
public interface Action {
	String execute(
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}
